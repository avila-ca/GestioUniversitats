import org.w3c.dom.*;

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Excepcio03 {
   public static void main(String args[]) {
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      try {
         DocumentBuilder builderCreate = builderFactory.newDocumentBuilder();
         Document docCreate = builderCreate.newDocument();
         Element arrel = docCreate.createElement("universitat");
         arrel.setAttribute("nom", "UPC");

         TransformerFactory tf = TransformerFactory.newInstance();
         Transformer transformer = tf.newTransformer();
         DOMSource source = new DOMSource(docCreate);
         File f = new File("nouDoc.xml");
         StreamResult result = new StreamResult(f);
         transformer.transform(source, result);

      } catch (Exception e) {
         System.out.println("error: " + e.getMessage());
      }
   }
} 