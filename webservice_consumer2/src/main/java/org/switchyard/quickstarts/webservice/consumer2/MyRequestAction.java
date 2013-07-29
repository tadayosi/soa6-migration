package org.switchyard.quickstarts.webservice.consumer2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.switchyard.quickstarts.webservice.consumer2.ws.LineItem;
import org.switchyard.quickstarts.webservice.consumer2.ws.Order;

@Named
public class MyRequestAction {
    
    /**
     * Convert the message into a webservice request map.
     */
    public Order option1(String message) throws Exception {
        logHeader();
        System.out.println("Webservice Option 1 Request Action\n");
        
        Order order = new Order();
        order.setId((long) 1);
        order.setShipTo("1234 my way");
        
        LineItem lineItem1 = new LineItem();
        lineItem1.setId((long) 1);
        lineItem1.setName("aname");
        lineItem1.setPrice((float) 10.00);
        
        LineItem lineItem2 = new LineItem();
        lineItem2.setId((long) 2);
        lineItem2.setName("aname2");
        lineItem2.setPrice((float) 20.00);
        
        ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(lineItem1);
        lineItems.add(lineItem2);
        
        order.setLineItems(lineItems);
        
        System.out.println("Request is: " + order);
        
        logFooter();
        return order;
    }
    
    /**
     * Convert the message into a webservice request map.
     */
    public Map<String, String> option2(String message) throws Exception {
        logHeader();
        System.out.println("Webservice Option 2 Request Action\n");
        
        Map<String, String> requestMap = new HashMap<String, String>();
        
        // add paramaters to the web service request map
        requestMap.put("processOrder.order.id", "1");
        requestMap.put("processOrder.order.shipTo", "1234 my way");
        
        requestMap.put("processOrder.order.lineItems[0].id", "1");
        requestMap.put("processOrder.order.lineItems[0].name", "item name b");
        requestMap.put("processOrder.order.lineItems[0].price", "20.00");
        requestMap.put("processOrder.order.lineItems[1].id", "2");
        requestMap.put("processOrder.order.lineItems[1].name", "item name c");
        requestMap.put("processOrder.order.lineItems[1].price", "30.00");
        
        System.out.println("Request map is: " + requestMap.toString());
        
        logFooter();
        return requestMap;
    }
    
    // This makes it easier to read on the console
    private void logHeader() {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
    }
    
    private void logFooter() {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
    }
    
}
