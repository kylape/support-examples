package com.redhat.gss.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import java.util.logging.Logger;
import java.util.logging.LogManager;

public class TestAgent implements java.lang.instrument.ClassFileTransformer
{
  private static Logger log = null;

  public
  byte[]
  transform(  ClassLoader         loader,
              String              className,
              Class<?>            classBeingRedefined,
              ProtectionDomain    protectionDomain,
              byte[]              classfileBuffer)
    throws IllegalClassFormatException
  {
    return null;
  }

  public static void premain(String arg, Instrumentation inst) throws Exception
  {
    System.out.println("TestAgent's classloader: " + TestAgent.class.getClassLoader());
    System.out.println("System classloader:      " + ClassLoader.getSystemClassLoader());
    System.out.println("Loaded class:  " + Class.forName("org.jboss.logmanager.LogManager").getName());
    System.out.println("j.u.l.manager: " + System.getProperty("java.util.logging.manager"));
    log = Logger.getLogger(TestAgent.class.getName());
    log.info("GSS IS HERE: " + arg);
    log.info("Installed logmanager: " + LogManager.getLogManager().getClass().getName());
  }
}
