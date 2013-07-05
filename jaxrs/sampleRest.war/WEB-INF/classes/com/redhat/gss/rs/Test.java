package com.redhat.gss.rs;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class Test
{
	public static void main(String[] args) throws Exception 
  {
		ClientRequest request = new ClientRequest("http://localhost:8080/sampleRest");

		request.accept("application/json");

		String input = "{\"type\":\"one\",\"name\":\"Kyle\"}";

    TransferObject obj = new TransferObject();
    obj.setType("one\"");
    obj.setName("Kyle\"");

		request.body("application/json", obj);
		ClientResponse<String> response = request.post(String.class);

		String t = response.getEntity();
		System.out.println("testRestClientRequestSendString: " + t);
		// Assert.assertEquals(NOME_EXPECT, t);
	}
}
