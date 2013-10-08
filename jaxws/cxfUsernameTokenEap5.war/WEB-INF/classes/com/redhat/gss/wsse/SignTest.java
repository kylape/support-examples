package com.redhat.gss.wsse;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.binding.soap.saaj.SAAJOutInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;

public class SignTest
{
  private final String serviceURL = "http://localhost:8080/cxfUsernameTokenEap5/ServiceImpl";

  public static void main(String[] args) throws Exception
  {
    new SignTest().test();
  }

  public void test() throws Exception
  {
    QName serviceName = new QName("http://www.jboss.org/jbossws/ws-extensions/wssecurity", "SecurityService");
    URL wsdlURL = new URL(serviceURL + "?wsdl");
    Service service = Service.create(wsdlURL, serviceName);
    ServiceIface proxy = (ServiceIface)service.getPort(ServiceIface.class);
    setupWsse(proxy);
    proxy.sayHello();
    proxy.greetMe();
  }

  private void setupWsse(ServiceIface proxy)
  {
    Client client = ClientProxy.getClient(proxy);
    Endpoint cxfEndpoint = client.getEndpoint();

    Map<String,Object> outProps = new HashMap<String,Object>();
    outProps.put("action", "UsernameToken");
    outProps.put("user", "kermit");
    outProps.put("passwordType", "PasswordText");
    outProps.put("passwordCallbackClass", "com.redhat.gss.wsse.KeystorePasswordCallback");
    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps); //request
    cxfEndpoint.getOutInterceptors().add(wssOut);
    cxfEndpoint.getOutInterceptors().add(new SAAJOutInterceptor());

    cxfEndpoint.getInInterceptors().add(new SAAJInInterceptor());
  }
}
