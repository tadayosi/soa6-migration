package org.switchyard.quickstarts.webservice.consumer1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name = "HelloWorld", targetNamespace = "http://webservice_consumer1/helloworld")
public class HelloWorldWS {
    
    @WebMethod
    public String sayHello(@WebParam(name = "toWhom") String toWhom) {
        String greeting = "Hello World Greeting for '" + toWhom + "'";// TODO how to test timestamp in SOAP: "' on " + new java.util.Date();
        return greeting;
    }
    
}
