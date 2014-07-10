package kyle;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.interceptor.Fault;

public class MyValidationEventHandlerInjector extends AbstractPhaseInterceptor<Message>
{
  public MyValidationEventHandlerInjector()
  {
    super(Phase.PRE_PROTOCOL);
  }

  public void handleMessage(Message message) throws Fault
  {
    message.put("jaxb-validation-event-handler", new MyValidationEventHandler());
  }
}
