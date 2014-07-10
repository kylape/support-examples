package kyle;

import org.apache.cxf.annotations.EndpointProperties;
import org.apache.cxf.annotations.EndpointProperty;
import org.apache.cxf.interceptor.InInterceptors;

@javax.jws.WebService
@EndpointProperties({
  // @EndpointProperty(key="set-jaxb-validation-event-handler",value="false")
})
@InInterceptors(classes={
  MyValidationEventHandlerInjector.class
})
public class TestEndpoint
{
  public void test(BooleanContainer bools)
  {
    Test.printValues(bools);
  }
}
