package com.redhat.gss.ra;

import javax.resource.spi.ActivationSpec;
import javax.resource.spi.BootstrapContext;
import javax.resource.spi.ResourceAdapter;
import javax.resource.spi.endpoint.MessageEndpointFactory;
import javax.transaction.xa.XAResource;

public class DummyResourceAdapter implements ResourceAdapter
{
  public void endpointActivation(MessageEndpointFactory factory, ActivationSpec spec)
  {
  }

  public void endpointDeactivation(MessageEndpointFactory factory, ActivationSpec spec)
  {
  }

  public XAResource[] getXAResources(ActivationSpec[] specs)
  {
    return null;
  }

  public void start(BootstrapContext ctx)
  {
  }

  public void stop()
  {
  }

  public int hashCode()
  {
    return super.hashCode();
  }

  public boolean equals(Object o)
  {
    return super.equals(o);
  }
}
