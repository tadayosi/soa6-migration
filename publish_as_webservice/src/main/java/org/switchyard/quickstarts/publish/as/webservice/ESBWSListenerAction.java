package org.switchyard.quickstarts.publish.as.webservice;

import org.switchyard.component.bean.Service;

@Service(HelloWorldPubService.class)
public class ESBWSListenerAction implements HelloWorldPubService {
    
    public String displayMessage(String message) {
        if (message.contains("Error")) {
            final String detail = "<say:sayFault xmlns:say=\"http://www.jboss.org/sayHi\"><say:code>" + "myErrorCode"
                    + "</say:code><say:faultString>" + "myDescription" + "</say:faultString></say:sayFault>";
            //@formatter:off
            return "<env:Fault xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                +    "<faultcode xmlns:say=\"http://www.jboss.org/sayHi\">say:myErrorCode</faultcode>"
                +    "<faultstring>myDescription</faultstring>"
                +    "<detail>" + detail + "</detail>"
                +  "</env:Fault>";
            //@formatter:on
        }
        
        System.out.println("Received request: " + message);
        final String responseMsg = "<say:sayHiResponse xmlns:say=\"http://www.jboss.org/sayHi\"><say:arg0>"
                + "Response from ESB Service" + "</say:arg0></say:sayHiResponse>";
        return responseMsg;
    }
}
