package org.switchyard.quickstarts.helloworld.file.notifier;

import static org.junit.Assert.*;

import java.io.File;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
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
public class HelloWorldFileNotifierTest {
    
    private static final String QUEUE_NAME = "quickstart_helloworld_file_notifier_Request";
    private static final String TEST_MESSAGE = "Hello World";
    private static final String OUTPUT_FILE = "target/output/results.log";
    
    private HornetQMixIn hornetQMixIn;
    
    @Before
    public void setUp() {
        new File(OUTPUT_FILE).delete();
    }
    
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
        assertTrue(new File(OUTPUT_FILE).exists());
        assertEquals(TEST_MESSAGE, FileUtils.readFileToString(new File(OUTPUT_FILE)));
    }
    
}
