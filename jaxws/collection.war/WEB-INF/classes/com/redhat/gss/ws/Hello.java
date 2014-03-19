package com.redhat.gss.ws;

import java.util.List;
import java.util.Collection;
import java.util.Map;

@javax.jws.WebService
public interface Hello {
  public String sayHello(List<String> names);
  public Collection<String> parseToList(String list);
  public Map<String, String> parseToMap(String mapData);
  // public Map<String, List<String>> parseListMap(String data);
}
