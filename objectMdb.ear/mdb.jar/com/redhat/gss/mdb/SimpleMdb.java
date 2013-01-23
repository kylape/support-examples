package com.redhat.gss.mdb;

import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

@MessageDriven(activationConfig = {
  @ActivationConfigProperty(propertyName="messagingType", propertyValue="javax.jms.MessageListener"),
  @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
  @ActivationConfigProperty(propertyName="Destination", propertyValue="jms/queue/TestQueue")
})
public class SimpleMdb implements MessageListener
{
  Logger log = Logger.getLogger(getClass());

  public void onMessage(Message message)
  {
    try
    {
      if(message instanceof ObjectMessage)
      {
        log.info("Received ObjectMessage: " + ((ObjectMessage)message).getObject());
      }
      else
      {
        log.info("Didn't receive an ObjectMessage");
      }
    }
    catch(Exception e)
    {
      log.error("Error reading message", e);
    }
  }
}
