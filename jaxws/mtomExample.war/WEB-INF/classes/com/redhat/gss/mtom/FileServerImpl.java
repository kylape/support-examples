package com.redhat.gss.mtom;

import java.util.Random;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.xml.ws.soap.MTOM;
import javax.activation.DataHandler;
import javax.jws.WebService;

@MTOM
@WebService(endpointInterface="com.redhat.gss.mtom.FileServer")
@org.apache.cxf.feature.Features(features={"org.apache.cxf.feature.LoggingFeature"})
public class FileServerImpl implements FileServer
{
	public void downloadFile(FileDataType data) throws Exception
	{
    Random random = new Random();
    FileOutputStream out = null;
    try
    {
      DataHandler dh = data.getFileData();
      File file = new File("/Users/klape/tmp/" + random.nextInt(Integer.MAX_VALUE) + ".xml");
      file.createNewFile();

      out = new FileOutputStream(file);
      dh.writeTo(out);
      out.flush();
    }
    finally
    {
      out.close();
    }
	}
}
