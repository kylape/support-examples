/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ws;

import javax.xml.bind.annotation.*;
//import sample.jaxws.MyExceptionBean;

@javax.xml.ws.WebFault
public class MyException extends SuperException
{
  //private MyExceptionBean faultInfo;
  private String summary;
  private String from;
  //private int id;

  public MyException(String message)
  {
    super(message);
  }

  /*
  public MyException(String summary, int id, String message, String from)
  {
    super(message);
    this.summary = summary;
    this.from = from;
    this.id = id;
  }

  public MyException(MyExceptionBean faultInfo)
  {
    this.faultInfo = faultInfo;
  }

  public MyExceptionBean getFaultInfo()
  {
    return faultInfo;
  }

  public void setFaultInfo(MyExceptionBean faultInfo)
  {
    this.faultInfo = faultInfo;
  }

  */

  public void setSummary(String summary)
  {
    this.summary = summary;
  }

  public void setFrom(String from)
  {
    this.from = from;
  }

  /*
  public void setId(int id)
  {
    this.id = id;
  }
  */

  public String getSummary()
  {
    return summary;
  }

  public String getFrom()
  {
    return from;
  }

  /*
  public int getId()
  {
    return id;
  }
  */
}
