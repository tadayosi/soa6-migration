package org.switchyard.quickstarts.helloworld.file.notifier;

import org.switchyard.component.bean.Service;

@Service(value = DisplayRouterListener.class, componentName = "routerToDisplay")
public class MyJMSListenerAction implements DisplayRouterListener {
    
    public String displayMessage(String message) throws Exception {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("Body: " + message);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        return message;
    }
    
}
