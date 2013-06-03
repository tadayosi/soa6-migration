package com.example.switchyard.soa6_migration_helloworld_file_action;

public class SimpleListener {

    public String noOperation(String message) { return message; }

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
    
    // This makes it easier to read on the console
    private void logHeader() {
        System.out.println("\n&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }
    private void logFooter() {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n");
    }

}
