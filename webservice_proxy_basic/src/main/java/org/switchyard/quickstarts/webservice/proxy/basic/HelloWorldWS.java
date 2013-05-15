package org.switchyard.quickstarts.webservice.proxy.basic;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Simple rpc-style webservice that returns a hello greeting (using the passed-in name) and the current date.
 * 
 * @author dward at jboss.org
 */
@WebService(name = "HelloWorld", targetNamespace = "http://webservice_proxy_basic/helloworld")
public class HelloWorldWS {
    
    @WebMethod
    public String sayHello(@WebParam(name = "toWhom") String toWhom) {
        return "Hello '" + toWhom + "'"; // TODO test timestamp in SOAP: "' on " + new java.util.Date();
    }
    
}
