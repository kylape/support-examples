/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.jaxws;

import org.apache.log4j.Logger;
import java.util.Date;

import com.redhat.gss.asm.PermGenExploder;

@javax.jws.WebService(serviceName="HelloWS", portName="hello")
public class HelloWSImpl
{
  private Logger log = Logger.getLogger(this.getClass().getName());

  public String hello()
  {
    log.error("Killing PermGen...");
    new Thread(new Runnable() 
    {
      public void run()
      {
        PermGenExploder.explode();
      }
    }).start();

    return "Hello, permGen is going to die";
  }
}
