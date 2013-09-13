package com.redhat.gss.jaxws;

import java.net.URL;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;
import org.jboss.logging.Logger;

@javax.jws.WebService
public class ClientEndpoint
{
  private static Logger log = Logger.getLogger(ClientEndpoint.class);

  public String invokeHelloWorld(String test, int count)
  {
    Long time = test(test, count);

    if(time < 0L)
      return "Specify a test: 'slow', 'medium', or 'fast'";

    log.info("'" + test + "' performance: " + time + "ms");
    return time.toString() + "ms";
  }

  public String runTest(int innerCount, long outerCount)
  {
    final String[] TYPES = {"slow", "medium", "fast"};
    final StringBuilder builder = new StringBuilder("\n");

    for(String type : TYPES)
    {
      Long average = 0L;
      for(long i=0; i<outerCount; i++)
      {
        average += test(type, innerCount);
      }
      builder.append(type + ": " + Long.toString(average/outerCount) + "ms\n");
    }
    String ret = builder.toString();
    log.info(ret);
    return ret;
  }

  private Long test(String test, int count)
  {
    URL wsdl = null;
    try{wsdl = new URL("http://localhost:8080/performance/hello?wsdl");}catch(Exception e){}
    final String name = "Kyle";
    final QName serviceName = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
    final long start = System.currentTimeMillis();

    if(test.equals("slow"))
    {
      for(int i=0; i<count; i++)
      {
        Service service = Service.create(wsdl, serviceName);
        HelloWS port = service.getPort(HelloWS.class);
        port.hello(name);
      }
    }
    else if(test.equals("medium"))
    {
      Service service = Service.create(wsdl, serviceName);
      for(int i=0; i<count; i++)
      {
        HelloWS port = service.getPort(HelloWS.class);
        port.hello(name);
      }
    }
    else if(test.equals("fast"))
    {
      Service service = Service.create(wsdl, serviceName);
      HelloWS port = service.getPort(HelloWS.class);
      for(int i=0; i<count; i++)
      {
        port.hello(name);
      }
    }
    else
    {
      return -1L;
    }

    final long stop = System.currentTimeMillis();
    return stop - start;
  }
}
