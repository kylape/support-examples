package com.redhat.gss.ws;

import java.util.List;
import java.util.Collection;
import java.util.Map;

import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface Hello {
  public String sayHello(List<String> names);
  public Collection<String> parseToList(String list);
  public Map<String, String> parseToMap(String mapData);
  public @WebResult(name="map") Map<String, StringList> parseListMap(String data);
}
