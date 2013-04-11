/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.ejb;

import org.jboss.modules.ModuleClassLoader;
import org.jboss.modules.Module;
import org.jboss.logging.Logger;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

@javax.jws.WebService
@XmlAccessorType(XmlAccessType.FIELD)
public class MyEjb
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  @XmlTransient
  private transient DataSource dataSource = null;
  
  @javax.jws.WebMethod(exclude=true)
  public DataSource getDataSource()
  {
    return this.dataSource;
  }
  
  @javax.jws.WebMethod(exclude=true)
  public void setDataSource(DataSource dataSource)
  {
    this.dataSource = dataSource;
  }
  
  public void test() throws Exception
  {
    log.info(dataSource);
  }
}
