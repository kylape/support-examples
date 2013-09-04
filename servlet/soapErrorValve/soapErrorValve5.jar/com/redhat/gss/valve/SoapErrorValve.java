package com.redhat.gss.valve;

import java.io.IOException;

import org.apache.catalina.deploy.LoginConfig;
import org.apache.catalina.valves.ErrorReportValve;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.util.RequestUtil;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;
import java.io.Writer;

import javax.xml.namespace.QName;
import javax.xml.soap.AttachmentPart;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPFault;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

public class SoapErrorValve5 extends ErrorReportValve
{
  private static Logger log = Logger.getLogger(SoapErrorValve5.class);

  private static final String SOAP = "http://schemas.xmlsoap.org/soap/envelope/";
  private static final QName serverFault = new QName(SOAP, "Server");
  private static final QName clientFault = new QName(SOAP, "Client");
  private TransformerFactory transformerFactory = TransformerFactory.newInstance();

  @Override
  public void report(Request request, Response response, Throwable exception)
  {
    log.info("I'm getting invoked");
    // Do nothing on non-HTTP responses
    int statusCode = response.getStatus();

    // Do nothing on a 1xx, 2xx and 3xx status
    // Do nothing if anything has been written already
    if ((statusCode < 400)) 
    {
      log.info("Returning due to <400 status code");
      return;
    }
    else if(response.getContentCount() > 0)
    {
      log.info("Returning because someone's already written a response.");
      Thread.dumpStack();
      return;
    }


    String message = RequestUtil.filter(response.getMessage());
    if (message == null)
        message = "";

    StringWriter result = new StringWriter();

    try
    {
      log.info("Creating SOAP message...");
      SOAPMessage soapMessage = createNewSoapMessage();
      
      SOAPPart requestSoapPart = soapMessage.getSOAPPart();
      SOAPEnvelope envelope = requestSoapPart.getEnvelope();
      SOAPBody body = envelope.getBody();
      SOAPFault fault = body.addFault(getFaultCode(statusCode), 
                                      getFaultString(statusCode, message, exception));
      DOMSource source = new DOMSource(envelope);
      Transformer transformer = transformerFactory.newTransformer();
      transformer.transform(source, new StreamResult(result));
    }
    catch(Exception e)
    {
      log.error("Error creating SOAP fault", e);
      //Delegate to the other ErrorReportValve
      return;
    }
    
    try 
    {
      log.info("Writing response...");
      try 
      {
        response.setContentType("application/soap+xml");
        response.setCharacterEncoding("utf-8");
      } 
      catch (Throwable th) 
      {
        if (container.getLogger().isDebugEnabled())
          container.getLogger().debug("status.setContentType", th);
      }
      Writer writer = response.getReporter();
      if (writer != null) 
      {
        // If writer is null, it's an indication that the response has
        // been hard committed already, which should never happen
        writer.write(result.toString());
      }
    } 
    catch (IOException e) 
    {
      log.error("",e);
    } 
    catch (IllegalStateException e) 
    {
      log.error("",e);
    }
  }

  private QName getFaultCode(int statusCode)
  {
    if(statusCode < 500)
      return clientFault;
    else
      return serverFault;
  }

  private String getFaultString(int statusCode, String message, Throwable t)
  {
    if(t == null && !message.equals(""))
      return message;
    else if(!message.equals(""))
      return message + t.getMessage();

    String report = null;
    try 
    {
      report = sm.getString("http." + statusCode, message);
    } 
    catch (Throwable th) {}

    if(report != null)
      return report;
    else //just make something up
      return "HTTP " + statusCode;
  }

  private SOAPMessage createNewSoapMessage() 
  {
    try 
    {
      MessageFactory factory = MessageFactory.newInstance();
      return factory.createMessage();
    } 
    catch (SOAPException e) 
    {
      throw new RuntimeException("Error composing message: " + e.getMessage(), e);
    }
  }
}
