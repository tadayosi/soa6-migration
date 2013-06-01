package org.switchyard.quickstarts.helloworld.topic.notifier;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.io.FileUtils;
import org.hornetq.jms.client.HornetQTopic;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.component.camel.model.RouteScanner;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(
        config = SwitchYardTestCaseConfig.SWITCHYARD_XML,
        mixins = HornetQMixIn.class,
        scanners = RouteScanner.class)
public class HelloWorldTopicNotifierTest {
    
    private static final String QUEUE_NAME = "quickstart_helloworld_topic_notifier_Request";
    private static final String TEST_MESSAGE = "Hello World";
    private static final String OUTPUT_FILE = "target/output/results.log";
    private static final String TOPIC_NAME = "helloworldtopic";
    
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
            MessageConsumer consumer1 = session.createConsumer(new HornetQTopic(TOPIC_NAME));
            MessageConsumer consumer2 = session.createConsumer(new HornetQTopic(TOPIC_NAME));
            
            MessageProducer producer = session.createProducer(HornetQMixIn.getJMSQueue(QUEUE_NAME));
            Message message = hornetQMixIn.createJMSMessage(TEST_MESSAGE);
            producer.send(message);
            
            Message receivedMessage1 = consumer1.receive(3000);
            assertThat(receivedMessage1, is(instanceOf(TextMessage.class)));
            assertThat(((TextMessage) receivedMessage1).getText(), is(equalTo(TEST_MESSAGE)));
            
            Message receivedMessage2 = consumer2.receive(3000);
            assertThat(receivedMessage2, is(instanceOf(TextMessage.class)));
            assertThat(((TextMessage) receivedMessage2).getText(), is(equalTo(TEST_MESSAGE)));
        } finally {
            HornetQMixIn.closeJMSSession(session);
        }
        
        File outputFile = new File(OUTPUT_FILE);
        assertThat(outputFile.exists(), is(true));
        assertThat(FileUtils.readFileToString(outputFile), is(equalTo(TEST_MESSAGE)));
    }
    
}
