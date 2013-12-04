package com.redhat.gss.jaxws;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="fun")
public class ReturnDate
{
  private Date date = null;
  
  @XmlElement(nillable=false, required=true)
  public Date getDate()
  {
    return this.date;
  }
  
  public void setDate(Date date)
  {
    this.date = date;
  }
}
