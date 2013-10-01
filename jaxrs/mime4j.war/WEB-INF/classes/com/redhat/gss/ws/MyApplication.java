package com.redhat.gss.ws;

import java.util.Set;
import java.util.HashSet;

public class MyApplication extends javax.ws.rs.core.Application
{
  public Set<Class<?>> getClasses()
  {
    Set<Class<?>> set = new HashSet<Class<?>>();
    set.add(MyResource.class);
    set.add(MyMultipartReader.class);
    return set;
  }
}
