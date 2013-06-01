package org.switchyard.quickstarts.helloworld.topic.notifier;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.hornetq.jms.client.HornetQTopic;
import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;

public final class HornetQClient {
    
    private static final String QUEUE_NAME = "quickstart_helloworld_topic_notifier_Request";
    private static final String TOPIC_NAME = "helloworldtopic";
    private static final String MESSAGE_PAYLOAD = "Hello World";
    private static final String USER = "guest";
    private static final String PASSWD = "guestp.1";
    
    private HornetQClient() {}
    
    public static void main(final String[] ignored) throws Exception {
        HornetQMixIn hornetQMixIn = new HornetQMixIn(false).setUser(USER).setPassword(PASSWD);
        hornetQMixIn.initialize();
        
        Session session = null;
        try {
            session = hornetQMixIn.createJMSSession();
            MessageConsumer consumer1 = session.createConsumer(new HornetQTopic(TOPIC_NAME));
            MessageConsumer consumer2 = session.createConsumer(new HornetQTopic(TOPIC_NAME));
            
            MessageProducer producer = session.createProducer(HornetQMixIn.getJMSQueue(QUEUE_NAME));
            Message message = hornetQMixIn.createJMSMessage(MESSAGE_PAYLOAD);
            producer.send(message);
            System.out.println("Message sent. Please see server console output");
            
            Message receivedMessage1 = consumer1.receive(3000);
            System.out.println("Received message 1:");
            System.out.println(((TextMessage) receivedMessage1).getText());
            
            Message receivedMessage2 = consumer2.receive(3000);
            System.out.println("Received message 2:");
            System.out.println(((TextMessage) receivedMessage2).getText());
        } finally {
            hornetQMixIn.uninitialize();
        }
    }
}
