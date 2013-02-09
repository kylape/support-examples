package com.redhat.gss.jaxws;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.net.URL;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.Service;
import javax.xml.namespace.QName;

import org.jboss.ws.core.StubExt;

import com.sun.xml.ws.transport.http.client.HttpCookie;

public class TestClient
{
  public static void main(String[] args) throws Exception
  {
    URL wsdl = new URL("http://localhost:8080/cookieWS/hello?wsdl");
    QName qname = new QName("http://jaxws.gss.redhat.com/", "HelloWS");
    Service service = Service.create(wsdl, qname);
    HelloWS port = service.getPort(HelloWS.class);
    //sessionMaintainTest(port);
    //setRequestCookieTest(port);
    getCookieAndUseIt(port);
  }

  public static void setRequestCookieTest(HelloWS port)
  {
    String sessionId = "blah:blah:1234";
    System.out.println("Session id: " + sessionId);
    final HttpCookie jSessionId = new HttpCookie("JSESSIONID=" + sessionId);
    System.out.println("Cookie name: " + jSessionId.getName());
    System.out.println("JSessionId: " + jSessionId.toString());

    Map<String, List<String>> headers = (Map<String, List<String>>) ((BindingProvider) port)
        .getRequestContext().get( MessageContext.HTTP_RESPONSE_HEADERS);

    if (null == headers) {
      System.out.println("Headers was null");
      headers = Collections.singletonMap("Cookie", Collections
          .singletonList(jSessionId.toString()));
      ((BindingProvider) port).getRequestContext().put(
          MessageContext.HTTP_REQUEST_HEADERS, headers);
    } else {
      System.out.println("Headers was not null");
      List<String> cookies = headers.get("Cookie");
      if (null == cookies) {
        System.out.println("Cookies was null");
        cookies = new ArrayList<String>();
        headers.put("Cookie", cookies);
      } else {
        System.out.println("Cookies was not null");
        cookies.add(jSessionId.toString());
      }
    }

    port.hello("Kyle");
  }

  public static void sessionMaintainTest(HelloWS port)
  {
    ((BindingProvider) port).getRequestContext().put("javax.xml.ws.session.maintain", true);

    System.out.println(port.hello("Kyle"));
    System.out.println(port.hello("Kyle"));
    System.out.println(port.hello("Kyle"));
    System.out.println(port.hello("Kyle"));
  }

  public static void getCookieAndUseIt(HelloWS port)
  {
    String cookie = port.getCookie();

    if(cookie == null)
      throw new IllegalStateException("Cookie is null from web service!");

    Map<String, Object> requestMap  = ((BindingProvider)port).getRequestContext();
    Map<String, List<String>> headers = (Map<String, List<String>>)requestMap.get(MessageContext.HTTP_REQUEST_HEADERS);
    if(headers == null)
    {
      headers = new HashMap<String, List<String>>();
      requestMap.put(MessageContext.HTTP_REQUEST_HEADERS, headers);
    }
    headers.put("Cookie", Collections.singletonList(cookie));
    port.testCookie();
  }
}
