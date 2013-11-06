package com.redhat.gss.jaxws;

import java.util.Date;

public class MyObject
{
  private int count;
  private Date date = null;
  
  public MyObject()
  {
  }

  public MyObject(Date date, int count)
  {
    this.date = date;
    this.count = count;
  }

  public Date getDate()
  {
    return this.date;
  }
  
  public void setDate(Date date)
  {
    this.date = date;
  }
  
  public int getCount()
  {
    return this.count;
  }
  
  public void setCount(int count)
  {
    this.count = count;
  }
}
