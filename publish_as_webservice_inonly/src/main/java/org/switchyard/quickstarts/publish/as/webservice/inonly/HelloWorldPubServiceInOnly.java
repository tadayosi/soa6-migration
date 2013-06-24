package org.switchyard.quickstarts.publish.as.webservice.inonly;

import org.switchyard.annotations.OperationTypes;

public interface HelloWorldPubServiceInOnly {
    
    @OperationTypes(in = "{http://www.jboss.org/sayHi}sayHi", out = "{http://www.jboss.org/sayHi}sayHiResponse")
    String displayMessage(String message);
    
}
