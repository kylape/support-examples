package com.redhat.gss.wsrm;

import java.net.URL;
import java.util.Map;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.ws.addressing.Names;
import org.apache.cxf.ws.addressing.WSAddressingFeature.WSAddressingFeatureApplier;
import org.apache.cxf.ws.addressing.WSAddressingFeature;
import org.apache.cxf.ws.rm.RM11Constants;
import org.apache.cxf.ws.rm.RMInInterceptor;
import org.apache.cxf.ws.rm.RMManager;
import org.apache.cxf.ws.rm.RMOutInterceptor;
import org.apache.cxf.ws.rm.soap.RMSoapInterceptor;

import org.jboss.logging.Logger;

@WebService
public class TestClient
{
  private static Logger log = Logger.getLogger(TestClient.class);
  private Hello port = null;

  private void init() throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/wsrm/HelloWS?wsdl");
    QName ns = new QName("http://wsrm.gss.redhat.com/", "HelloWSService");
    Service service = Service.create(wsdl, ns);
    port = service.getPort(Hello.class);
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
      log.info(port.hello("Kyle"));
    }
    port = null;
  }
}
