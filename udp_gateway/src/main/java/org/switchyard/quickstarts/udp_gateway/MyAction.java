package org.switchyard.quickstarts.udp_gateway;

public class MyAction {
    
    public String displayMessage(String message) throws Exception {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("Body: " + message);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return message;
    }
    
}