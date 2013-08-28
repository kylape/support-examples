package com.redhat.gss.valve;

import java.io.IOException;

import static org.jboss.web.CatalinaMessages.MESSAGES;
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

public class SoapErrorValve extends ErrorReportValve
{
  private static Logger log = Logger.getLogger(SoapErrorValve.class);

  private static final String SOAP = "http://schemas.xmlsoap.org/soap/envelope/";
  private static final QName serverFault = new QName(SOAP, "Server");
  private static final QName clientFault = new QName(SOAP, "Client");
  private TransformerFactory transformerFactory = TransformerFactory.newInstance();

  @Override
  public void report(Request request, Response response, Throwable exception)
  {
    // Do nothing on non-HTTP responses
    int statusCode = response.getStatus();

    // Do nothing on a 1xx, 2xx and 3xx status
    // Do nothing if anything has been written already
    if ((statusCode < 400) || (response.getContentCount() > 0))
        return;

    String message = RequestUtil.filter(response.getMessage());
    if (message == null)
        message = "";

    StringWriter result = new StringWriter();

    try
    {
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
      log.debug("Error creating SOAP fault", e);
      //Delegate to the other ErrorReportValve
      return;
    }
    
    try 
    {
      try 
      {
        response.setContentType("application/soap+xml");
        response.setCharacterEncoding("utf-8");
      } 
      catch (Throwable t) 
      {
        if (container.getLogger().isDebugEnabled())
          container.getLogger().debug("status.setContentType", t);
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
    } 
    catch (IllegalStateException e) 
    {
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
    switch (statusCode) {
      case 404: report = MESSAGES.http404(); break;
      case 500: report = MESSAGES.http500(); break;
      case 400: report = MESSAGES.http400(); break;
      case 403: report = MESSAGES.http403(); break;
      case 401: report = MESSAGES.http401(); break;
      case 402: report = MESSAGES.http402(); break;
      case 405: report = MESSAGES.http405(); break;
      case 406: report = MESSAGES.http406(); break;
      case 407: report = MESSAGES.http407(); break;
      case 408: report = MESSAGES.http408(); break;
      case 409: report = MESSAGES.http409(); break;
      case 410: report = MESSAGES.http410(); break;
      case 411: report = MESSAGES.http411(); break;
      case 412: report = MESSAGES.http412(); break;
      case 413: report = MESSAGES.http413(); break;
      case 414: report = MESSAGES.http414(); break;
      case 415: report = MESSAGES.http415(); break;
      case 416: report = MESSAGES.http416(); break;
      case 417: report = MESSAGES.http417(); break;
      case 422: report = MESSAGES.http422(); break;
      case 423: report = MESSAGES.http423(); break;
      case 424: report = MESSAGES.http424(); break;
      case 426: report = MESSAGES.http426(); break;
      case 428: report = MESSAGES.http428(); break;
      case 429: report = MESSAGES.http429(); break;
      case 431: report = MESSAGES.http431(); break;
      case 501: report = MESSAGES.http501(); break;
      case 502: report = MESSAGES.http502(); break;
      case 503: report = MESSAGES.http503(); break;
      case 504: report = MESSAGES.http504(); break;
      case 505: report = MESSAGES.http505(); break;
      case 506: report = MESSAGES.http506(); break;
      case 507: report = MESSAGES.http507(); break;
      case 508: report = MESSAGES.http508(); break;
      case 510: report = MESSAGES.http510(); break;
      case 511: report = MESSAGES.http511(); break;
    }

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
