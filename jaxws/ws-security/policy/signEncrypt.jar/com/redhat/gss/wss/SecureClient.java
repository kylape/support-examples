package com.redhat.gss.wss;

import java.net.URL;
import java.util.Map;
import java.util.HashMap;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.BindingProvider;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.ws.security.SecurityConstants;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.jboss.logging.Logger;

public class SecureClient
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public static void main(String[] args) throws Exception
  {
    SecureClient client = new SecureClient();
    client.invoke();
  }

  public void invoke() throws Exception
  {
    //Create JAX-WS client
    URL wsdl = new URL("http://localhost:8080/signEncrypt/SecureService?wsdl");
    QName serviceNS = new QName("http://wss.gss.redhat.com/", "SecureServiceService");
    QName portNS = new QName("http://wss.gss.redhat.com/", "SecureServicePort");
    Service service = Service.create(wsdl, serviceNS);
    WsIntfc port = service.getPort(portNS, WsIntfc.class);

    Map<String, Object> ctx = ((BindingProvider)port).getRequestContext();

    ctx.put(SecurityConstants.CALLBACK_HANDLER, new KeystorePasswordCallback());
    
    //Signature/encrypt properties file defines the keystore to use for incoming and outgoing messages
    ClassLoader tccl = Thread.currentThread().getContextClassLoader();
    ctx.put(SecurityConstants.SIGNATURE_PROPERTIES, tccl.getResource("/META-INF/security-client.properties"));
    ctx.put(SecurityConstants.ENCRYPT_PROPERTIES, tccl.getResource("/META-INF/security-client.properties"));
    
    //Signautre/encrypt username defines which keystore alias to use on outgoing messages
    ctx.put(SecurityConstants.SIGNATURE_USERNAME, "client");
    ctx.put(SecurityConstants.ENCRYPT_USERNAME, "server");

    //Invoke client
    log.info("Output of sayHello operation: " + port.sayHello("Kyle"));
  }
}
