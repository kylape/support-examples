package com.redhat.gss.jaxws;

import java.util.Map;
import java.util.List;

import org.apache.cxf.binding.soap.SoapBindingConstants;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

public class RemoveSoapActionInterceptor<T extends Message> extends AbstractPhaseInterceptor<T> {

  public RemoveSoapActionInterceptor() {
    super(Phase.RECEIVE);
  }

  public void handleMessage(Message m) throws Fault {
    if (!(m instanceof SoapMessage)) {
        return;
    }
    SoapMessage message = (SoapMessage)m;
    Map<String, List<String>> headers = CastUtils.cast((Map<?, ?>)message.get(Message.PROTOCOL_HEADERS));
    if (headers != null) {
      headers.remove(SoapBindingConstants.SOAP_ACTION);
    }
  }
}
