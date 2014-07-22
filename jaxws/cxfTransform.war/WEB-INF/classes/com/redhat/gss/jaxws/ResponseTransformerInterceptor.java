package com.redhat.gss.jaxws;

import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.interceptor.transform.TransformOutInterceptor;
import org.jboss.logging.Logger;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxb.JAXBDataBinding;

public class ResponseTransformerInterceptor extends AbstractPhaseInterceptor<Message> {
  private static Logger log = Logger.getLogger(ResponseTransformerInterceptor.class);

  public ResponseTransformerInterceptor() {
    super(Phase.SETUP);
  }

  public void handleMessage(Message message) throws Fault {
    log.warn("Configuring interceptor");
    Map<String, String> appendElements = new HashMap<String, String>();
    appendElements.put("{http://schemas.xmlsoap.org/soap/envelope/}Body", "{http://schemas.xmlsoap.org/soap/envelope/}Header=");
    TransformOutInterceptor interceptor = new TransformOutInterceptor();
    interceptor.setOutAppendElements(appendElements);
    message.getInterceptorChain().add(interceptor);

    Map<String, String> envMap = new HashMap<String, String>();
    envMap.put( "env" , "http://schemas.xmlsoap.org/soap/envelope/");
    Map<String, String> nsMap = new HashMap<String, String>();
    nsMap.put( "https://www.ccr.gov" , "m" );
    JAXBDataBinding dataBinding = (JAXBDataBinding)message.getExchange().getEndpoint().getService().getDataBinding();
    dataBinding.setNamespaceMap(nsMap);
    message.put("soap.env.ns.map", envMap);
  }
}
