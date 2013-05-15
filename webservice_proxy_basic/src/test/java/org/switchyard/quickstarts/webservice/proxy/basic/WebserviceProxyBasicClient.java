package org.switchyard.quickstarts.webservice.proxy.basic;

import org.switchyard.component.test.mixins.http.HTTPMixIn;

/**
 * Simple client to send a SOAP message.
 */
public final class WebserviceProxyBasicClient {
    
    private static final String URL = "http://localhost:8080/proxy/HelloWorldWSService";
    private static final String XML = "src/test/resources/xml/soap-request.xml";
    
    /**
     * Private no-args constructor.
     */
    private WebserviceProxyBasicClient() {}
    
    /**
     * Only execution point for this application.
     * @param ignored not used.
     * @throws Exception if something goes wrong.
     */
    public static void main(final String[] ignored) throws Exception {
        
        HTTPMixIn soapMixIn = new HTTPMixIn();
        soapMixIn.initialize();
        
        try {
            String result = soapMixIn.postFile(URL, XML);
            System.out.println("SOAP Reply:\n" + result);
        } finally {
            soapMixIn.uninitialize();
        }
    }
}
