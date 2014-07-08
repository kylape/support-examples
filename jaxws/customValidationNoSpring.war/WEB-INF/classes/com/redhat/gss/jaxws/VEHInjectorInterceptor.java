package com.redhat.gss.jaxws;

import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class VEHInjectorInterceptor extends AbstractPhaseInterceptor<Message> {
  public VEHInjectorInterceptor() {
    super(Phase.PRE_UNMARSHAL);
  }

  public void handleMessage(Message message) throws Fault {
    message.put("jaxb-validation-event-handler", new MyValidationEventHandler());
  }
}
