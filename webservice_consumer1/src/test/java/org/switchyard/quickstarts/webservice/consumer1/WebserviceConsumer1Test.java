package org.switchyard.quickstarts.webservice.consumer1;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.xml.ws.Endpoint;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.bean.config.model.BeanSwitchYardScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.component.test.mixins.http.HTTPMixIn;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.transform.config.model.TransformSwitchYardScanner;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(
        config = SwitchYardTestCaseConfig.SWITCHYARD_XML,
        mixins = { HTTPMixIn.class, CDIMixIn.class, HornetQMixIn.class },
        scanners = { BeanSwitchYardScanner.class, TransformSwitchYardScanner.class })
public class WebserviceConsumer1Test {
    
    private static final String WEB_SERVICE = "http://localhost:8080/Quickstart_webservice_consumer1/HelloWorldWS";
    private static final String QUEUE_NAME = "quickstart_webservice_consumer1_Request";
    private static final String TEST_MESSAGE = "Jimbo";
    
    private static Endpoint _endpoint;
    
    private HornetQMixIn _hornetQMixIn;
    private HTTPMixIn _httpMixIn;
    
    @BeforeClass
    public static void startWebService() throws Exception {
        _endpoint = Endpoint.publish(WEB_SERVICE, new HelloWorldWS());
    }
    
    @AfterClass
    public static void stopWebService() throws Exception {
        _endpoint.stop();
    }
    
    @Test
    public void testWebService() throws Exception {
        _httpMixIn.postResourceAndTestXML(WEB_SERVICE, "/xml/soap-request.xml", "/xml/soap-response.xml");
    }
    
    @Test
    public void sendJMS() throws Exception {
        // TODO add test store
        Session session = null;
        try {
            session = _hornetQMixIn.createJMSSession();
            MessageProducer producer = session.createProducer(HornetQMixIn.getJMSQueue(QUEUE_NAME));
            Message message = _hornetQMixIn.createJMSMessage(TEST_MESSAGE);
            producer.send(message);
            Thread.sleep(500);
        } finally {
            HornetQMixIn.closeJMSSession(session);
        }
    }
    
}
