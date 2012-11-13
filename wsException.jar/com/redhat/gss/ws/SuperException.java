package com.redhat.gss.ws;

public class SuperException extends Exception
{
  private int id;

  public SuperException(String message)
  {
    super(message);
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }
}
