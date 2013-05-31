package org.switchyard.quickstarts.helloworldaction;

public class MyJMSListenerAction {
    
    public String displayMessage(String message) throws Exception {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("Body: " + message);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return message;
    }

    public String playWithMessage(String message) throws Exception {
        return "\nBEFORE**\n" + message + "\nAFTER**\n";
    }

}
