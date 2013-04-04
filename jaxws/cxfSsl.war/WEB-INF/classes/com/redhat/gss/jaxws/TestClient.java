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

import org.jboss.logging.Logger;

public class TestClient
{
  private Logger log = Logger.getLogger(this.getClass());

  public static void main(String[] args) throws Exception
  {
    new TestClient().invoke();
  }

  public void invoke() throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/cxfSsl/hello?wsdl");
    QName qname = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
    Service service = Service.create(wsdl, qname);
    HelloWS port = service.getPort(HelloWS.class);

    log.info(port.hello("Kyle"));
  }
}
