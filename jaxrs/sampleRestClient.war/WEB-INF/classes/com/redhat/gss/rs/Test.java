package com.redhat.gss.rs;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.ProxyFactory;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"/*"})
public class Test extends HttpServlet
{
  public void doGet(HttpServletRequest htRequest, HttpServletResponse htResponse) throws ServletException
  {
    try
    {
      String input = "{\"type\":\"one\",\"name\":\"Kyle\"}";

      TransferObject obj = new TransferObject();
      obj.setType("one\"");
      obj.setName("Kyle\"");

      //Simple ClientRequest invocation
      ClientRequest request = new ClientRequest("http://localhost:8080/sampleRest");
      request.accept("application/json");
      request.body("application/json", obj);
      ClientResponse<String> response = request.post(String.class);
      String t = response.getEntity();
      System.out.println("testRestClientRequestSendString: " + t);

      //Proxy example
      EscapedCharTest proxy = ProxyFactory.create(
        EscapedCharTest.class, "http://localhost:8080/sampleRest"
      );

      TransferObject ret = null;

      ret = proxy.helloPost(obj);
      System.out.println("Proxy response: " + ret);
      htResponse.setStatus(200);
    } 
    catch(Exception e)
    {
      //die
    }

  }
}
