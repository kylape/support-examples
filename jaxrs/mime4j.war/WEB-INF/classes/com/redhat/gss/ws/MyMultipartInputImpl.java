package com.redhat.gss.ws;

import javax.ws.rs.ext.Providers;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInputImpl;

public class MyMultipartInputImpl extends MultipartInputImpl
{
  public MyMultipartInputImpl(MediaType contentType, Providers workers)
  {
    super(contentType, workers);
  }

  public MyMultipartInputImpl(MediaType contentType, Providers workers,
                            MediaType defaultPartContentType)
  {
    super(contentType, workers, defaultPartContentType);
  }

  public void close()
  {
    if (mimeMessage != null)
    {
      try
      {
        mimeMessage.dispose();
      }
      catch (Exception e)
      {

      }
    }
  }
}
