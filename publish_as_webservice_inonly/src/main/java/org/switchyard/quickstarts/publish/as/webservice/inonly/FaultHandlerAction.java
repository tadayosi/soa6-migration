package org.switchyard.quickstarts.publish.as.webservice.inonly;

import java.util.Map;

import org.switchyard.component.bean.Service;

@Service(value = FaultService.class, componentName = "ESBServiceSample_FaultService")
public class FaultHandlerAction implements FaultService {
    
    public String process(Map<String, String> message) {
        
        System.out.println("********************************************");
        System.out.println("Fault Service received message:");
        System.out.println("\t Code:    " + message.get(DETAIL_CODE_CONTENT));
        System.out.println("\t Desc:    " + message.get(DETAIL_DESCRIPTION_CONTENT));
        System.out.println("\t Detail:  " + message.get(DETAIL_DETAIL_CONTENT));
        System.out.println("********************************************");
        
        return null;
    }
    
}
