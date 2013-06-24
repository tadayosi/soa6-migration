package org.switchyard.quickstarts.publish.as.webservice.inonly;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

@Service(value = HelloWorldPubServiceInOnly.class, componentName = "ESBServiceSample")
public class ESBWSListenerAction implements HelloWorldPubServiceInOnly {
    
    @Inject
    @Reference
    private FaultService faultService;
    
    public String displayMessage(String message) {
        if (message.contains("Error")) {
            final String detail = "<say:sayFault xmlns:say=\"http://www.jboss.org/sayHi\"><say:code>" + "myErrorCode"
                    + "</say:code><say:faultString>" + "myDescription" + "</say:faultString></say:sayFault>";
            final Map<String, String> faultMsg = new HashMap<String, String>();
            faultMsg.put(FaultService.DETAIL_CODE_CONTENT, "myErrorCode");
            faultMsg.put(FaultService.DETAIL_DESCRIPTION_CONTENT, "myDescription");
            faultMsg.put(FaultService.DETAIL_DETAIL_CONTENT, detail);
            // TODO more proper SOAP fault handling?
            faultService.process(faultMsg);
            return null;
        }
        
        System.out.println("Received request: " + message);
        final String responseMsg = "<say:sayHiResponse xmlns:say=\"http://www.jboss.org/sayHi\"><say:arg0>"
                + "Response from ESB Service" + "</say:arg0></say:sayHiResponse>";
        return responseMsg;
    }
    
}
