import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JAXPExample
{
  public static void main(String[] args) throws Exception
  {
    if(args.length < 1)
      throw new IllegalArgumentException("Need XML file name");
    
    File file = new File(args[0]);
    JAXPExample example = new JAXPExample();
    Document doc = example.parseXml(file);
    example.updateXml(doc);
    example.writeXml(doc, new FileOutputStream(file));
  }

  public Document parseXml(File f) throws Exception
  {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = dbf.newDocumentBuilder();
    //set options on the DocumentBuilder here
    return builder.parse(f);
  }

  public void updateXml(Document doc)
  {
    Element containerElement = doc.getDocumentElement();
    NodeList childNodes = containerElement.getChildNodes();
    for(int i=0; i < childNodes.getLength(); i++)
    {
      Node n = childNodes.item(i);
      System.out.println(n.getNodeName());
      if(n instanceof Element)
      {
        Node text = n.getFirstChild();
        int invocationCount = Integer.parseInt(text.getNodeValue());
        invocationCount++;
        text.setNodeValue("" + invocationCount);
        break;
      }
    }
  }

  public void writeXml(Document doc, OutputStream ostream) throws Exception
  {
    // Use a Transformer for output
    TransformerFactory tFactory = TransformerFactory.newInstance();
    Transformer transformer = tFactory.newTransformer();

    DOMSource source = new DOMSource(doc);
    StreamResult result = new StreamResult(ostream);
    transformer.transform(source, result);
  }
}
