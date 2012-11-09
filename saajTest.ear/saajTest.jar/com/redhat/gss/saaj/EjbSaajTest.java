/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */
package com.redhat.gss.saaj;

import javax.xml.soap.MessageFactory;
import org.jboss.modules.ModuleClassLoader;
import org.jboss.modules.Module;
import org.jboss.logging.Logger;

@javax.ejb.Singleton
@javax.ejb.Startup
public class EjbSaajTest
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  @javax.annotation.PostConstruct
  public void test() throws Exception
  {
    Thread.sleep(2000);

    MessageFactory mf = MessageFactory.newInstance();
    log.info(mf.getClass().getName());

    ModuleClassLoader cl = (ModuleClassLoader)Thread.currentThread().getContextClassLoader();
    Module module = cl.getModule();
  }
}
