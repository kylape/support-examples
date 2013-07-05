package com.redhat.gss.rs;

public class TransferObject
{
  private String type = null;
  private String name = null;

  public TransferObject()
  {
  }

  public TransferObject(String a, String b)
  {
    type = a;
    name = b;
  }
  
  public String getType()
  {
    return this.type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
}

