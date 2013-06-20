package com.redhat.gss.sts;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.picketlink.identity.federation.api.wstrust.WSTrustClient;
import org.picketlink.identity.federation.api.wstrust.WSTrustClient.SecurityInfo;
import org.picketlink.identity.federation.core.wstrust.WSTrustException;
import org.picketlink.identity.federation.core.wstrust.plugins.saml.SAMLUtil;
import org.w3c.dom.Element;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.jboss.logging.Logger;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import java.net.URL;
import javax.xml.ws.BindingProvider;
import java.util.List;
import javax.xml.ws.handler.Handler;
import org.picketlink.trust.jbossws.SAML2Constants;
import org.picketlink.trust.jbossws.handler.SAML2Handler;

public class StsClient extends HttpServlet
{
  private Logger log = Logger.getLogger(getClass());

  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    String name = request.getParameter("name");
    if(name == null || name.equals(""))
    {
      name = "Default Name";
    }
    
    OutputStream os = response.getOutputStream();
    try
    {
      Element assertion = testSTS();
      String greeting = sayHello(assertion, name) + "\n";
      os.write(greeting.getBytes());
    }
    catch(Exception e)
    {
      log.error("Problem", e);
      os.write("Problem\n".getBytes());
    }
    finally
    {
      os.close();
    }
  }

  public Element testSTS() throws Exception
  {
    // create a WSTrustClient instance.
    WSTrustClient client = new WSTrustClient("PicketLinkSTS", "PicketLinkSTSPort", 
      "http://localhost:8080/picketlink-sts/PicketLinkSTS", 
      new SecurityInfo("admin", "admin"));
    
    // issue a SAML assertion using the client API.
    Element assertion = null;
    try 
    {
      assertion = client.issueToken(SAMLUtil.SAML2_TOKEN_TYPE);
    }
    catch (WSTrustException wse)
    {
      log.error("Unable to issue assertion: ", wse);
    }
    
    // print the assertion for demonstration purposes.
    log.info("Successfully issued a standard SAMLV2.0 Assertion!");
    printAssertion(assertion);

    /*
    // validate the received SAML assertion.
    try
    {
      log.info("Is assertion valid? " + client.validateToken(assertion));
    }
    catch (WSTrustException wse)
    {
      log.error("Failed to validate SAMLV2.0 Assertion: ", wse);
    }
    */
    return assertion;
  }

  public String sayHello(Element assertion, String name) throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/sts-client/test?wsdl");
    QName qname = new QName("http://sts.gss.redhat.com/", "TestEndpointImplService");
    QName portQname = new QName("http://sts.gss.redhat.com/", "TestEndpointImplPort");
    Service service = Service.create(wsdl, qname);
    TestEndpoint port = service.getPort(portQname, TestEndpoint.class);
    BindingProvider bp = (BindingProvider)port;
    bp.getRequestContext().put(SAML2Constants.SAML2_ASSERTION_PROPERTY, assertion);
    List<Handler> handlers = bp.getBinding().getHandlerChain();
    handlers.add(new SAML2Handler());
    bp.getBinding().setHandlerChain(handlers);
    return port.hello(name);
  }

  private void printAssertion(Element assertion) throws Exception
  {
    TransformerFactory tranFactory = TransformerFactory.newInstance();
    Transformer aTransformer = tranFactory.newTransformer();
    Source src = new DOMSource(assertion);
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    Result dest = new StreamResult(baos);
    aTransformer.transform(src, dest);
    log.info(new String(baos.toByteArray()));
  }
}
