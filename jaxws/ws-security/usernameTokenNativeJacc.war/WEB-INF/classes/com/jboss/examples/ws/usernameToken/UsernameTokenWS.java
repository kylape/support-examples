package com.jboss.examples.ws.usernameToken;

import javax.jws.WebService;

@WebService
public interface UsernameTokenWS
{
	public String hello(String name) throws Exception;
}
