package com.redhat.gss.mdb.client;

import javax.annotation.Resource;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import javax.naming.InitialContext;

import com.redhat.gss.mdb.MyObject;

@javax.jws.WebService
public class MdbClientWar
{
  private ConnectionFactory connectionFactory = null;

  private Queue queue = null;

  public void callMdb() throws Exception
  {
    if(connectionFactory == null)
    {
      connectionFactory = (ConnectionFactory) new InitialContext().lookup("java:ConnectionFactory");
    }

    if(queue == null)
    {
      queue = (Queue) new InitialContext().lookup("jms/queue/TestQueue");
    }

    Connection connection = connectionFactory.createConnection();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    MessageProducer messageProducer = session.createProducer(queue);
    ObjectMessage objMessage = session.createObjectMessage();
    objMessage.setObject(new MyObject("Kyle"));
    messageProducer.send(objMessage);
  }
}
