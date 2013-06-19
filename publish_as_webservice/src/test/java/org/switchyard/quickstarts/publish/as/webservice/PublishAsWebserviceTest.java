package org.switchyard.quickstarts.publish.as.webservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(
        config = SwitchYardTestCaseConfig.SWITCHYARD_XML,
        mixins = { CDIMixIn.class, HTTPMixIn.class },
        scanners = { BeanSwitchYardScanner.class, TransformSwitchYardScanner.class })
public class PublishAsWebserviceTest {
    
    private static final String WEB_SERVICE = "http://localhost:18001/Quickstart_publish_as_webservice/HelloWorldPubService";
    
    private HTTPMixIn _httpMixIn;
    
    @Test
    public void testWebService() throws Exception {
        _httpMixIn.postResourceAndTestXML(WEB_SERVICE, "/xml/soap-userpass-message.xml",
                "/xml/soap-userpass-message-response.xml");
    }
    
    @Test
    public void testWebService_error() throws Exception {
        _httpMixIn.postResourceAndTestXML(WEB_SERVICE, "/xml/error-soap-message.xml",
                "/xml/error-soap-message-response.xml");
    }
    
}
