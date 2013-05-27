package org.switchyard.quickstarts.webservice.consumer1;

import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.switchyard.component.test.mixins.hornetq.HornetQMixIn;

public final class HornetQClient {
    
    private static final String QUEUE_NAME = "quickstart_webservice_consumer1_Request";
    private static final String MESSAGE_PAYLOAD = "Jimbo";
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
        } finally {
            hornetQMixIn.uninitialize();
        }
    }
}
