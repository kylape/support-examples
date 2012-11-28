package com.redhat.gss.rs;

import javax.ws.rs.core.Application;
import javax.ws.rs.ApplicationPath;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class MyApplication extends Application
{
  private Set<Class<?>> classes = new HashSet<Class<?>>();

  public MyApplication()
  {
    classes.add(InjectingApplication.class);
  }

  @Override
  public Set<Class<?>> getClasses()
  {
    return classes;
  }
}
