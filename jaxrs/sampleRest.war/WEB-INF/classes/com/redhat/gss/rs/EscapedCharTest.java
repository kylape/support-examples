package com.redhat.gss.rs;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import org.jboss.logging.Logger;

@Path("/")
@javax.ejb.Stateless
public class EscapedCharTest
{
  private static Logger log = Logger.getLogger(EscapedCharTest.class);

  @GET
  @Produces("application/json")
  public TransferObject hello()
  {
    TransferObject to = new TransferObject();
    to.setName("Look, a quote! \"");
    to.setType("Hey, it's a backslash: \\");
    return to;
  }

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public TransferObject helloPost(String string)
  {
    log.info(string);
    return hello();
  }
}

