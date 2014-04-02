package com.redhat.gss.wsrm;

import java.net.URL;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.apache.cxf.ws.addressing.JAXWSAConstants;
import org.apache.cxf.ws.addressing.impl.AddressingPropertiesImpl;
import org.apache.cxf.ws.rm.RM11Constants;
import org.apache.cxf.ws.rm.RMInInterceptor;
import org.apache.cxf.ws.rm.RMManager;
import org.apache.cxf.ws.rm.RMOutInterceptor;
import org.apache.cxf.ws.rm.soap.RMSoapInterceptor;
import org.apache.cxf.ws.security.SecurityConstants;

import org.jboss.logging.Logger;

@WebService
public class TestClient
{
  private static Logger log = Logger.getLogger(TestClient.class);
  private Hello port = null;

  private synchronized void init() throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/wsrm-wss/HelloWS?wsdl");
    QName ns = new QName("http://wsrm.gss.redhat.com/", "HelloWSService");
    Service service = Service.create(wsdl, ns);
    port = service.getPort(Hello.class);
    // setUpAddressing((BindingProvider)port);
    setUpSecurity((BindingProvider)port);
  }

  public static void setUpSecurity(BindingProvider bp)
  {
    Map<String, Object> ctx = bp.getRequestContext();
    ctx.put(SecurityConstants.CALLBACK_HANDLER, new KeystorePasswordCallback());
    
    //Signature properties file defines the keystore to use for incoming and outgoing messages
    ctx.put(SecurityConstants.SIGNATURE_PROPERTIES, TestClient.class.getResource("/META-INF/security-client.properties"));
    // ctx.put(SecurityConstants.ENCRYPT_PROPERTIES, TestClient.class.getResource("/META-INF/security-client.properties"));
    
    //Signautre username defines which keystore alias to use on outgoing messages
    ctx.put(SecurityConstants.SIGNATURE_USERNAME, "client");
    // ctx.put(SecurityConstants.ENCRYPT_USERNAME, "server");
  }

  public static void setUpAddressing(BindingProvider bp)
  {
    AddressingProperties addrProperties = new AddressingPropertiesImpl();

    EndpointReferenceType replyTo = new EndpointReferenceType();
    AttributedURIType replyToURI = new AttributedURIType();
    replyToURI.setValue("http://localhost:8080/wsrm/drain");
    replyTo.setAddress(replyToURI);
    addrProperties.setReplyTo(replyTo);

    Map<String, Object> requestContext = bp.getRequestContext();
    requestContext.put(JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES, addrProperties);
  }

  @javax.jws.WebMethod(exclude=true)
  public static void main(String[] args) throws Exception
  {
    new TestClient().runTest();
  }

  /**
   * This is **NOT** thead-safe!
   * The port could be set to null in the middle of an invocation.
   */
  public void runTest() throws Exception
  {
    if(port == null)
      init();

    for(int i=0; i<20; i++)
    {
      if(i==19)
        ((BindingProvider)port).getRequestContext().put("org.apache.cxf.ws.rm.last-message", "true");
      else if(i==0)
        ((BindingProvider)port).getRequestContext().remove("org.apache.cxf.ws.rm.last-message");

      log.info(port.hello("Kyle"));
    }
    port = null;
  }
}
