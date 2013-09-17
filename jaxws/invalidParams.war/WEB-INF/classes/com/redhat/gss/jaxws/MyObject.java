package com.redhat.gss.jaxws;

import java.util.Date;

public class MyObject
{
  private int count;
  private Date date = null;
  
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
