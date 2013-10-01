package com.redhat.gss.ws;

import javax.ws.rs.*;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.logging.Logger;

@Path("/rest")
public class MyResource
{
  private static Logger log = Logger.getLogger(MyResource.class);

  @POST
  @Consumes("multipart/*")
  public String readFile(MultipartInput input)
  {
    for (InputPart part : input.getParts())
    {
      log.info("Input part: " + part);
    }
    if(input instanceof MyMultipartInputImpl)
    {
      log.info("Closing file!");
      ((MyMultipartInputImpl)input).close();
    }
    return "Complete";
  }

  @GET
  public String test()
  {
    return "TEST";
  }
}
