package org.switchyard.quickstarts.webservice.consumer2.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name = "OrderProcessor", targetNamespace = "http://webservice_consumer2/orderProcessor")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class OrderProcessorWS {
    
    @WebMethod
    public OrderStatus processOrder(@WebParam(name = "order") Order order) {
        OrderStatus status = new OrderStatus();
        System.out.println("Order is: " + order.toString());
        
        status.setComment("order processed");
        status.setId(order.getId());
        status.setReturnCode(OrderStatus.SUCCESS);
        
        return status;
    }
    
}
