package org.switchyard.quickstarts.webservice.consumer2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

import org.switchyard.annotations.Transformer;
import org.switchyard.quickstarts.webservice.consumer2.ws.Order;
import org.switchyard.quickstarts.webservice.consumer2.ws.OrderStatus;
import org.switchyard.quickstarts.webservice.consumer2.ws.ProcessOrderRequest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Transformers {
    
    private static final QName NS_PROCESS_ORDER = new QName("http://webservice_consumer2/orderProcessor",
            "processOrder");
    
    private JAXBContext _jaxbContext;
    
    public Transformers() throws JAXBException {
        _jaxbContext = JAXBContext.newInstance(ProcessOrderRequest.class, OrderStatus.class);
    }
    
    @Transformer(to = "{http://webservice_consumer2/orderProcessor}processOrder")
    public Element transform(Order order) throws Exception {
        Marshaller marshaller = _jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        ProcessOrderRequest requestObject = new ProcessOrderRequest();
        requestObject.setOrder(order);
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        marshaller.marshal(new JAXBElement<ProcessOrderRequest>(NS_PROCESS_ORDER, ProcessOrderRequest.class,
                requestObject), document);
        return document.getDocumentElement();
    }
    
    @Transformer(from = "{http://webservice_consumer2/orderProcessor}processOrderResponse")
    public OrderStatus transform(Element soap) throws Exception {
        if (soap.getElementsByTagName("return").getLength() == 0) { return null; }
        Unmarshaller unmarshaller = _jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(soap.getElementsByTagName("return").item(0), OrderStatus.class).getValue();
    }
    
}
