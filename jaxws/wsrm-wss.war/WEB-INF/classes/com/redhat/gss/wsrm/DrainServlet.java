package com.redhat.gss.wsrm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.w3c.dom.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.jboss.logging.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.namespace.QName;

@WebServlet("/drain")
public class DrainServlet extends HttpServlet
{
  private static final QName wsrmIdentifier = new QName("http://schemas.xmlsoap.org/ws/2005/02/rm", "Identifier");
  private static Logger log = Logger.getLogger(DrainServlet.class);

  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException
  {
    if(req.getContentType().contains("text/xml"))
    {
      try
      {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(req.getInputStream());
        printDocument(doc);
        // sendAck(doc, resp, builder);
      }
      catch(Exception e)
      {
        log.warn("Could not print XML document", e);
      }
    }
    else
    {
      log.warn("Received non-XML message");
    }
  }

  // public static void sendAck(Document doc, HttpServletResponse resp, DocumentBuilder)
  // {
  //   Element identifierElement = findFirstDescendantElement(doc.getDocumentElement());
  //   String identifier = identifierElement.getNodeValue();
  // }

  // public static void findFirstDescendantElement(Element e, QName qname)
  // {
  //   Element result = null;
  //   NodeList nl = e.getChildNodes();
  //   for(int i=0; i < nl.getLength(); i++)
  //   {
  //     Node n = nl.item(i);
  //     if(n instanceof Element)
  //     {
  //       Element child = (Element)n;
  //       if(qname.getNamespaceURI().equals(child.getNamespaceURI()) &&
  //          qname.getLocalName().equals(child.getLocalName()))
  //       {
  //         result = child;
  //         break;
  //       }
  //       else
  //       {
  //         result = findFirstDescendantElement(child, qname);
  //         if(result == null)
  //         {
  //           break;
  //         }
  //       }
  //     }
  //   }
  // }

  public static void printDocument(Document doc) throws Exception
  {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer transformer = tf.newTransformer();
    transformer.setOutputProperty(OutputKeys.METHOD, "xml");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
    transformer.transform(new DOMSource(doc), new StreamResult(baos));
    log.info(baos.toString());
  }
}
