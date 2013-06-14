package org.switchyard.quickstarts.udp.gateway;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.Exchange;
import org.switchyard.component.camel.model.RouteScanner;
import org.switchyard.test.MockHandler;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, scanners = RouteScanner.class)
public class UdpGatewayTest {
    
    private SwitchYardTestKit _testKit;
    private static final String SERVICE = "Service1";
    private static final String PAYLOAD = "UDP example payload";
    private static final String HOST = "localhost";
    private static final int PORT = 9999;
    
    @Test
    public void sendTextMessage() throws Exception {
        _testKit.removeService(SERVICE);
        final MockHandler service = _testKit.registerInOnlyService(SERVICE);
        
        UdpClient.sendUdpString(PAYLOAD, HOST, PORT);
        
        service.waitForOKMessage();
        final LinkedBlockingQueue<Exchange> recievedMessages = service.getMessages();
        assertThat(recievedMessages, is(notNullValue()));
        final Exchange recievedExchange = recievedMessages.iterator().next();
        String content = recievedExchange.getMessage().getContent(String.class);
        // the receive content is trimmed because extra bytes appended to frame by receiver
        assertThat(content.trim(), is(equalTo(PAYLOAD)));
    }
    
    @Test
    public void runTest() throws Exception {
        UdpClient.sendUdpString(PAYLOAD, HOST, PORT);
        Thread.sleep(500);
    }
    
}
