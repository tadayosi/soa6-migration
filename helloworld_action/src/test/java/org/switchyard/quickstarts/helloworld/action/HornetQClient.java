package org.switchyard.quickstarts.helloworld.action;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;

public final class HornetQClient {
    
    private static final String QUEUE_NAME = "quickstart_helloworld_action_Request";
    private static final String QUEUE_NAME_RESPONSE = "quickstart_helloworld_action_Response";
    private static final String MESSAGE_PAYLOAD = "Hello World Action";
    private static final String USER = "guest";
    private static final String PASSWD = "guestp.1";
    
    private HornetQClient() {}
    
    public static void main(final String[] ignored) throws Exception {
        HornetQMixIn hornetQMixIn = new HornetQMixIn(false).setUser(USER).setPassword(PASSWD);
        hornetQMixIn.initialize();
        
        Session session = null;
        try {
            session = hornetQMixIn.createJMSSession();
            MessageProducer producer = session.createProducer(HornetQMixIn.getJMSQueue(QUEUE_NAME));
            Message message = hornetQMixIn.createJMSMessage(MESSAGE_PAYLOAD);
            producer.send(message);
            System.out.println("Message sent. Please see server console output");
            
            MessageConsumer consumer = session.createConsumer(HornetQMixIn.getJMSQueue(QUEUE_NAME_RESPONSE));
            Message receivedMessage = consumer.receive(3000);
            System.out.println("Received message:");
            System.out.println(((TextMessage) receivedMessage).getText());
        } finally {
            hornetQMixIn.uninitialize();
        }
    }
}
