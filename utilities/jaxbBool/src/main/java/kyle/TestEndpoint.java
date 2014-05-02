package kyle;

@javax.jws.WebService
public class TestEndpoint
{
  public void test(BooleanContainer bools)
  {
    Test.printValues(bools);
  }
}
