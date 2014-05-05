package kyle;

import org.apache.cxf.annotations.EndpointProperties;
import org.apache.cxf.annotations.EndpointProperty;

@javax.jws.WebService
@EndpointProperties({
  @EndpointProperty(key="set-jaxb-validation-event-handler",value="false")
})
public class TestEndpoint
{
  public void test(BooleanContainer bools)
  {
    Test.printValues(bools);
  }
}
