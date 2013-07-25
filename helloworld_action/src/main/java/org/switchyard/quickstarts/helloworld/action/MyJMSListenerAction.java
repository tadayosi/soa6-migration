package org.switchyard.quickstarts.helloworld.action;

import javax.inject.Named;

@Named
public class MyJMSListenerAction {
    
    public String noOperation(String message) {
        return message;
    }
    
    public String displayMessage(String message) throws Exception {
        logHeader();
        System.out.println("Body: " + message);
        logFooter();
        return message;
    }
    
    public String playWithMessage(String message) throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("\nBEFORE**\n");
        sb.append(message);
        sb.append("\nAFTER**\n");
        return sb.toString();
    }
    
    public void exceptionHandler(String message, Throwable exception) {
        logHeader();
        System.out.println("!ERROR!");
        System.out.println(exception.getMessage());
        System.out.println("For Message: ");
        System.out.println(message);
        logFooter();
    }
    
    // This makes it easier to read on the console
    private void logHeader() {
        System.out.println("\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }
    private void logFooter() {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
    }
    
}
