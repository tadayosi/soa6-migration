package org.switchyard.quickstarts.webservice.consumer2.ws;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProcessOrderRequest implements Serializable {
    
    private Order order;
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
}
