package org.switchyard.quickstarts.webservice.consumer1;

import java.io.StringReader;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.switchyard.annotations.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Transformers {
    
    //@formatter:off
    private static String SOAP_TEMPLATE =
              "<ns2:sayHello xmlns:ns2=\"http://webservice_consumer1/helloworld\">"
            + "    <toWhom>%s</toWhom>"
            + "</ns2:sayHello>";
    //@formatter:on
    
    @Transformer(to = "{http://webservice_consumer1/helloworld}sayHello")
    public Element transform(String message) throws Exception {
        return toElement(String.format(SOAP_TEMPLATE, message));
    }
    
    @Transformer(from = "{http://webservice_consumer1/helloworld}sayHelloResponse")
    public String transform(Element soap) throws Exception {
        return getElementValue(soap, "return");
    }
    
    private String getElementValue(Element parent, String elementName) {
        String value = null;
        NodeList nodes = parent.getElementsByTagName(elementName);
        if (nodes.getLength() > 0) {
            value = nodes.item(0).getChildNodes().item(0).getNodeValue();
        }
        return value;
    }
    
    private Element toElement(String xml) {
        DOMResult dom = new DOMResult();
        try {
            TransformerFactory.newInstance().newTransformer().transform(new StreamSource(new StringReader(xml)), dom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ((Document) dom.getNode()).getDocumentElement();
    }
    
}