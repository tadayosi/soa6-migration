package org.switchyard.quickstarts.helloworld;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.camel.model.RouteScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(
        config = SwitchYardTestCaseConfig.SWITCHYARD_XML,
        mixins = { CDIMixIn.class, HornetQMixIn.class },
        scanners = RouteScanner.class)
public class HelloWorldTest {
    
    private static final String QUEUE_NAME = "quickstart_helloworld_action_Request";
    private static final String TEST_MESSAGE = "Hello World";
    
    private HornetQMixIn hornetQMixIn;
    
    @Test
    public void sendJMS() throws Exception {
        Session session = null;
        try {
            session = hornetQMixIn.createJMSSession();
            MessageProducer producer = session.createProducer(HornetQMixIn.getJMSQueue(QUEUE_NAME));
            Message message = hornetQMixIn.createJMSMessage(TEST_MESSAGE);
            producer.send(message);
            Thread.sleep(500);
        } finally {
            HornetQMixIn.closeJMSSession(session);
        }
    }
    
}
