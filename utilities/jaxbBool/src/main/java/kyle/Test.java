package kyle;

import java.net.URL;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Writer;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jboss.logging.Logger;

public class Test
{
  public static Logger log = Logger.getLogger(Test.class);

  public static void main(String[] args) throws Exception
  {
    if(args.length == 1 && args[0].equals("servlet"))
    {
      Test.invokeServlet();
    }
    else
    {
      Test.test();
    }
  }

  public static void invokeServlet() throws IOException, MalformedURLException
  {
    URL url = new URL("http://localhost:8080/jaxbBool/TestEndpoint");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    String xml = readXml();
    Writer writer = new OutputStreamWriter(conn.getOutputStream());
    writer.write(xml);
    writer.close();
    log.info("HTTP " + conn.getResponseCode() + " response");
  }

  private static String readXml() throws IOException
  {
    StringBuffer buffer = new StringBuffer();
    InputStream is = Thread.currentThread().getContextClassLoader().getResource("request.xml").openStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    String line = null;
    while((line = reader.readLine()) != null)
    {
      buffer.append(line + "\n");
    }
    return buffer.toString();
  }

  public static void test() throws JAXBException, IOException
  {
    JAXBContext jc = JAXBContext.newInstance(BooleanContainer.class);
    log.info("-------------------------------------------");
    log.info("JAXB classloader: " + jc.getClass().getClassLoader());
    log.info("Note:  If the classloader is 'null', then that means it's the boot/JDK classloader");
    log.info("-------------------------------------------");
    Unmarshaller u = jc.createUnmarshaller();
    URL xml = Thread.currentThread().getContextClassLoader().getResource("test.xml");
    BooleanContainer bools = (BooleanContainer)u.unmarshal(xml.openStream());
    printValues(bools);
  }

  public static void printValues(BooleanContainer bools)
  {
    log.info("bool1:  " + bools.bool1);
    log.info("bool2:  " + bools.bool2);
    log.info("bool3:  " + bools.bool3);
    log.info("bool4:  " + bools.bool4);
    log.info("bool5:  " + bools.bool5);
    log.info("bool6:  " + bools.bool6);
    log.info("bool7:  " + bools.bool7);
    log.info("bool8:  " + bools.bool8);
    log.info("bool9:  " + bools.bool9);
    log.info("bool10: " + bools.bool10);
    log.info("bool11: " + bools.bool11);
    log.info("bool12: " + bools.bool12);
    log.info("-----");
  }
}
