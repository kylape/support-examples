package com.redhat.gss.mdb.client;

import javax.annotation.Resource;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import com.redhat.gss.mdb.MyObject;

@javax.ejb.Stateless
@javax.jws.WebService
public class MdbClient
{
  @Resource(mappedName="ConnectionFactory")
  private static ConnectionFactory connectionFactory;

  @Resource(mappedName="jms/queue/TestQueue")
  private static Queue queue;

  public void callMdb() throws Exception
  {
    Connection connection = connectionFactory.createConnection();
    Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    MessageProducer messageProducer = session.createProducer(queue);
    ObjectMessage objMessage = session.createObjectMessage();
    objMessage.setObject(new MyObject("Kyle"));
    messageProducer.send(objMessage);
  }
}
