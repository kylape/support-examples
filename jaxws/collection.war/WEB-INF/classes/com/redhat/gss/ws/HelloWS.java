package com.redhat.gss.ws;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

@javax.jws.WebService(endpointInterface="com.redhat.gss.ws.Hello")
public class HelloWS implements Hello {
  public String sayHello(List<String> names) {
    StringBuilder builder = new StringBuilder("Hello");

    String newName = "";
    for(String name : names) {
      if(!newName.equals("")) {
        builder.append(", ");
        builder.append(newName);
      }
      newName = name;
    }
    builder.append(", and ");
    builder.append(newName);
    builder.append("!");

    return builder.toString();
  }

  public Collection<String> parseToList(String list) {
    List<String> parsed = new ArrayList<String>();
    for(String value : list.split(" ")) {
      parsed.add(value);
    }
    return parsed;
  }

  public Map<String, String> parseToMap(String mapData) {
    Map<String, String> map = new HashMap<String, String>();
    for(String item : mapData.split(",")) {
      String[] keyVal = item.split(":");
      map.put(keyVal[0], keyVal[1]);
    }
    return map;
  }

  public Map<String, StringList> parseListMap(String data) {
    Map<String, StringList> mapOfLists = new HashMap<String, StringList>();
    for(String keyValData : data.split(";")) {
      String[] keyVal = keyValData.split(":");
      String key = keyVal[0];
      String value = keyVal[1];
      StringList sl = new StringList();
      List<String> list = sl.getStringList();
      mapOfLists.put(key, sl);
      for(String item : value.split(",")) {
        list.add(item);
      }
    }
    return mapOfLists;
  }
}
