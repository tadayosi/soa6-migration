package org.switchyard.quickstarts.udp_gateway;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.nio.charset.Charset;

public class UdpGatewayClient {

    public static void main(String args[]) throws Exception {
        String TEST_MESSAGE = args[0];
        String HOST_NAME = args[1];
        Integer PORT_NUM = Integer.parseInt(args[2]);

        MulticastSocket clientSocket = new MulticastSocket();
        InetAddress group = InetAddress.getByName(HOST_NAME);
        byte[] datagramBody = TEST_MESSAGE.getBytes(Charset.defaultCharset());
        DatagramPacket packet = new DatagramPacket(datagramBody, 0,
                datagramBody.length, group, PORT_NUM);
        clientSocket.send(packet);
        clientSocket.close();

    }
}
