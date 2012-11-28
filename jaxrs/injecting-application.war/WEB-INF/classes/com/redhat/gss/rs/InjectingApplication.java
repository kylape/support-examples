package com.redhat.gss.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@Path("/")
public class InjectingApplication {

  @Context 
  Application application;

	@GET
	public String hello() {
		return "Saying Hello, application is " + application + "\n";
	}

}
