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
  private final String serviceURL = "http://localhost:8080/cxfWssX509EjbEap5/ServiceImpl";

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
  }

  private void setupWsse(ServiceIface proxy)
  {
    Client client = ClientProxy.getClient(proxy);
    Endpoint cxfEndpoint = client.getEndpoint();

    Map<String,Object> outProps = new HashMap<String,Object>();
    outProps.put("action", "Timestamp Signature");
    outProps.put("user", "alice");
    outProps.put("signaturePropFile", "META-INF/alice.properties");
    outProps.put("signatureKeyIdentifier", "DirectReference");
    outProps.put("passwordCallbackClass", "com.redhat.gss.wsse.KeystorePasswordCallback");
    outProps.put("signatureParts", "{Element}{http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd}Timestamp;{Element}{http://schemas.xmlsoap.org/soap/envelope/}Body");
    WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps); //request
    cxfEndpoint.getOutInterceptors().add(wssOut);
    cxfEndpoint.getOutInterceptors().add(new SAAJOutInterceptor());

    Map<String,Object> inProps= new HashMap<String,Object>();
    inProps.put("action", "Timestamp Signature");
    inProps.put("signaturePropFile", "META-INF/alice.properties");
    inProps.put("passwordCallbackClass", "com.redhat.gss.wsse.KeystorePasswordCallback");
    WSS4JInInterceptor wssIn = new WSS4JInInterceptor(inProps); //response
    cxfEndpoint.getInInterceptors().add(wssIn);
    cxfEndpoint.getInInterceptors().add(new SAAJInInterceptor());
  }
}
