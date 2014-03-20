package com.redhat.gss.ws;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class StringList {
  private List<String> stringList = new ArrayList<String>();
  
  @XmlElement(name="value")
  public List<String> getStringList() {
    return this.stringList;
  }
  
  public void setStringList(List<String> stringList) {
    this.stringList = stringList;
  }
}
