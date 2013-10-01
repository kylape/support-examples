package com.redhat.gss.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import org.jboss.resteasy.plugins.providers.multipart.MultipartReader;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInputImpl;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;

/**
 * @author <a href="mailto:bill@burkecentral.com">Bill Burke</a>
 * @version $Revision: 1 $
 */
@Provider
@Consumes("multipart/*")
public class MyMultipartReader extends MultipartReader
{
   protected
   @Context
   Providers workers;


   public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType)
   {
      return type.equals(MultipartInput.class);
   }

   public MultipartInput readFrom(Class<MultipartInput> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException
   {
      String boundary = mediaType.getParameters().get("boundary");
      if (boundary == null) throw new IOException("Unable to get boundary for multipart");
      MultipartInputImpl input = new MyMultipartInputImpl(mediaType, workers);
      input.parse(entityStream);
      return input;
   }
}

