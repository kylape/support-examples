package com.redhat.gss.rs;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;

@Path("/")
public interface EscapedCharTest
{
  @GET
  @Produces("application/json")
  public TransferObject hello();

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public TransferObject helloPost(String string);

  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public TransferObject helloPost(TransferObject string);
}

