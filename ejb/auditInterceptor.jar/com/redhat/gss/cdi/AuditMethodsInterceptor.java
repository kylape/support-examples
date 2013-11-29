package com.redhat.gss.cdi;

import org.jboss.logging.Logger;
import javax.interceptor.InvocationContext;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import javax.annotation.PostConstruct;
import javax.interceptor.Interceptor;

@AuditMethods @Interceptor
public class AuditMethodsInterceptor
{
  private static Logger log = Logger.getLogger(AuditMethodsInterceptor.class);

  @PostConstruct
  public void printMethods(InvocationContext ctx) throws Exception
  {
    Class<?> clazz = ctx.getTarget().getClass();
    log.debug("Annotations of class: " + clazz.getName());
    for(Method method : clazz.getDeclaredMethods())
    {
      log.debug(method.getName() + ":");
      for(Annotation a : method.getAnnotations())
      {
        log.debug("  " + a.getClass().getName() + ", " + a.annotationType());
      }
    }
    ctx.proceed();
  }
}
