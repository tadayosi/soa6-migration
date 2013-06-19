package org.switchyard.quickstarts.publish.as.webservice;

import org.switchyard.annotations.OperationTypes;

public interface HelloWorldPubService {
    
    @OperationTypes(in = "{http://www.jboss.org/sayHi}sayHi", out = "{http://www.jboss.org/sayHi}sayHiResponse")
    String displayMessage(String message);
    
}
