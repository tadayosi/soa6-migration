package org.switchyard.quickstarts.helloworld;

import javax.inject.Named;

@Named
public class MyJMSListenerAction {
    
    public String displayMessage(String message) throws Exception {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("Body: " + message);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return message;
    }
    
}
