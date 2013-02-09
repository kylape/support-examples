/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ws;

import javax.xml.bind.annotation.*;

@javax.xml.ws.WebFault
//@XmlType(
  //name="myException",
  //propOrder={"from","id","summary"}
//)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class MyException extends SuperException
{
  private String summary;
  private String from;

  public MyException(String message)
  {
    super(message);
  }

  public void setSummary(String summary)
  {
    this.summary = summary;
  }

  public void setFrom(String from)
  {
    this.from = from;
  }

  public String getSummary()
  {
    return summary;
  }

  public String getFrom()
  {
    return from;
  }
}
