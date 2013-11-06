package com.redhat.gss.jaxws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.net.URL;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;

import org.jboss.ws.core.StubExt;

public class TestClient
{
  public static void main(String[] args) throws Exception
  {
		System.setProperty("javax.net.ssl.trustStore", "client.keystore");
		System.setProperty("javax.net.ssl.trustStorePassword", "client");
		System.setProperty("javax.net.ssl.keyStore", "client.keystore");
		System.setProperty("javax.net.ssl.keyStorePassword", "client");
		System.setProperty("javax.net.debug", "ssl,handshake,record");

    URL wsdl = new URL("https://localhost:8443/helloWorld/hello?wsdl");
    QName qname = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
    Service service = Service.create(wsdl, qname);
    HelloWS port = service.getPort(HelloWS.class);

    // ((BindingProvider) port).getRequestContext().put("javax.xml.ws.session.maintain", true);

    System.out.println(port.hello(new MyObject(new Date(), 14)));
  }
}
