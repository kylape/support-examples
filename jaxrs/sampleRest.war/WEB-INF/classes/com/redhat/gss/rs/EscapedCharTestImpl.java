package com.redhat.gss.rs;

import org.jboss.logging.Logger;

@javax.ejb.Stateless
public class EscapedCharTestImpl implements EscapedCharTest
{
  private static Logger log = Logger.getLogger(EscapedCharTest.class);

  public TransferObject hello()
  {
    TransferObject to = new TransferObject();
    to.setName("Look, a quote! \"");
    to.setType("Hey, it's a backslash: \\");
    return to;
  }

  public TransferObject helloPost(String string)
  {
    log.info(string);
    return hello();
  }

  public TransferObject helloPost(TransferObject string)
  {
    log.info(string);
    return hello();
  }
}
