package com.redhat.gss.mdb;

import java.io.Serializable;

public class MyObject implements Serializable
{
  private String name;

  public MyObject(String name)
  {
    this.name = name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return name;
  }
}
