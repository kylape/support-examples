package com.redhat.gss.log.test;

import org.apache.log4j.Logger;

public class TestVO
{
  private Logger log = Logger.getLogger(this.getClass());

  private String name;

  public TestVO()
  {
    log.info("Creating TestVO");
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String sayHello()
  {
    String ret = "Hello, " + name + "!";
    log.info(ret);
    return ret;
  }
}
