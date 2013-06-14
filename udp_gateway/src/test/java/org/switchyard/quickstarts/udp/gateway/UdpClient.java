package org.switchyard.quickstarts.udp.gateway;

import java.io.IOException;
import java.net.UnknownHostException;
import org.apache.commons.net.echo.EchoUDPClient;
import java.net.InetAddress;

public class UdpClient {
    public static void main(String... args) {
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        final String payload = args[2];
        
        try {
            sendUdpString(payload, host, port);
        } catch (final UnknownHostException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void sendUdpString(final String payload, final String host, final int port)
            throws UnknownHostException, IOException {
        final EchoUDPClient client = new EchoUDPClient();
        client.open();
        try {
            final byte[] writeBuffer = payload.getBytes();
            client.setSoTimeout(3000);
            client.send(writeBuffer, writeBuffer.length, InetAddress.getByName(host), port);
        } finally {
            client.close();
        }
    }
}
