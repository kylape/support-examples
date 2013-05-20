/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import javax.annotation.PostConstruct;
import javax.xml.ws.spi.Provider;
import org.jboss.logging.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.beans.factory.annotation.Autowired;

@javax.jws.WebService
public class GoodbyeWS
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  @Autowired
  private Test test = null;

  public String goodbye(String name) throws Exception
  {
    log.info("This instance: " + this);
    log.info("Test instance: " + test);
    return "Goodbye, " + name;
  }
  
  public Test getTest()
  {
    return this.test;
  }
  
  public void setTest(Test test)
  {
    log.info("Setting test in " + this);
    this.test = test;
  }

	@PostConstruct
	public void init() {
		log.info("PostConstruct - init");
    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
}
