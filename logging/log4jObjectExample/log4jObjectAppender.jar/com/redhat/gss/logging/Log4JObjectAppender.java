package com.redhat.gss.logging;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class Log4JObjectAppender extends AppenderSkeleton
{
  private ThreadLocal<Boolean> invoked = new ThreadLocal<Boolean>();

  @Override
  public void append(final LoggingEvent event) 
  {
    if(Boolean.TRUE.equals(invoked.get()))
    {
      invoked.set(Boolean.FALSE);
      return;
    }
    else
      invoked.set(Boolean.TRUE);

    if(event.getMessage() instanceof String)
    {
      System.out.println("Message was a string: " + event.getMessage());
    }
    else
    {
      System.out.println("!!! Message was an object!");
    }
  }

  public boolean requiresLayout()
  {
    return false;
  }

  public void close()
  {
  }
}
