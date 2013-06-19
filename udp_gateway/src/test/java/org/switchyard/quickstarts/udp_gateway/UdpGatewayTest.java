package org.switchyard.quickstarts.udp_gateway;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.switchyard.Exchange;
import org.switchyard.component.camel.model.RouteScanner;
import org.switchyard.component.test.mixins.cdi.CDIMixIn;
import org.switchyard.test.MockHandler;
import org.switchyard.test.SwitchYardRunner;
import org.switchyard.test.SwitchYardTestCaseConfig;
import org.switchyard.test.SwitchYardTestKit;

@RunWith(SwitchYardRunner.class)
@SwitchYardTestCaseConfig(config = SwitchYardTestCaseConfig.SWITCHYARD_XML, mixins = { CDIMixIn.class }, scanners = RouteScanner.class)
public class UdpGatewayTest {

    private SwitchYardTestKit _testKit;
    private static final String TEST_MESSAGE = "** UDP example payload **";
    private static final String HOST_NAME = "localhost";
    private static final int PORT_NUM = 9999;

    @Test
    public void sendUdpString() throws UnknownHostException, IOException {
        // replace existing implementation for testing purposes
        _testKit.removeService("GreetingService");
        final MockHandler greetingService = _testKit
                .registerInOnlyService("GreetingService");

        MulticastSocket clientSocket = new MulticastSocket();
        InetAddress group = InetAddress.getByName(HOST_NAME);
        byte[] datagramBody = TEST_MESSAGE.getBytes(Charset.defaultCharset());
        DatagramPacket packet = new DatagramPacket(datagramBody, 0,
                datagramBody.length, group, PORT_NUM);
        clientSocket.send(packet);

        greetingService.waitForOKMessage();

        final LinkedBlockingQueue<Exchange> recievedMessages =
        greetingService.getMessages();
        // assertThat(recievedMessages, is(notNullValue()));
    }
}
