package com.redhat.gss.jaxws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.net.URL;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;

import org.jboss.ws.core.StubExt;
import java.util.Enumeration;

public class TestClient
{
  public static void main(String[] args) throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/cxfTransform/hello?wsdl");
    QName qname = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
    Service service = Service.create(wsdl, qname);
    HelloWS port = service.getPort(HelloWS.class);
    System.out.println(port.hello("Kyle"));
  }
}
