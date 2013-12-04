package com.redhat.gss.jaxws;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import org.jboss.logging.Logger;

public class MyValidationEventHandler implements ValidationEventHandler {

  private static Logger log = Logger.getLogger(MyValidationEventHandler.class);

  @Override
  public boolean handleEvent(ValidationEvent event) {
    log.warn(event.getMessage());
    return true;
  }
}
