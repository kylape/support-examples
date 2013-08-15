/*
 * To the extent possible under law, Red Hat, Inc. has dedicated all copyright 
 * to this software to the public domain worldwide, pursuant to the CC0 Public 
 * Domain Dedication. This software is distributed without any warranty.  See 
 * <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package com.redhat.gss.logging;

import org.apache.log4j.varia.StringMatchFilter;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.ThrowableInformation;
import org.apache.log4j.spi.Filter;

/*
 * This filter will check exception messages and stack frames for
 * keywords in addition to the actual log message.
 */
public class ExceptionScanningPatternFilter extends StringMatchFilter
{
  @Override
  public int decide(LoggingEvent event) 
  {
    if(!event.getLoggerName().contains("gss"))
    {
      return Filter.NEUTRAL;
    }

    String msg = event.getRenderedMessage();

    ThrowableInformation throwableInfo = event.getThrowableInformation();
    String[] renderedThrowable = null;

    if(throwableInfo != null)
    {
      renderedThrowable = throwableInfo.getThrowableStrRep();
    }

    if((msg == null && renderedThrowable == null) || getStringToMatch() == null)
      return Filter.NEUTRAL;

    if(!matches(getStringToMatch(), msg, renderedThrowable)) 
    {
      return Filter.NEUTRAL;
    } 
    else 
    {
      if(getAcceptOnMatch()) 
      {
        return Filter.ACCEPT;
      } 
      else 
      {
        return Filter.DENY;
      }
    }
  }

  private boolean matches(String match, String msg, String[] renderedThrowable)
  {
    if(msg != null && msg.indexOf(match) != -1)
    {
      return true;
    }

    if(renderedThrowable == null)
      return false;

    for(String throwableLine : renderedThrowable)
    {
      if(throwableLine.indexOf(match) != -1)
      {
        return true;
      }
    }

    return false;
  }
}
