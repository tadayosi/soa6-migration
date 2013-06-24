package org.switchyard.quickstarts.publish.as.webservice.inonly;

import org.switchyard.component.test.mixins.http.HTTPMixIn;

/**
 * Simple client to send a SOAP message.
 */
public final class WebServiceClient {
    
    private static final String URL = "http://localhost:8080/Quickstart_publish_as_webservice_inonly/HelloWorldPubServiceInOnly?wsdl";
    private static final String XML = "src/test/resources/xml/soap-userpass-message.xml";
    
    /**
     * Private no-args constructor.
     */
    private WebServiceClient() {}
    
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
