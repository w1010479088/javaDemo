package test.server_client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;

class ServerUtil {
    static final int PORT_NUM = 5066;
    static final String NET_ADDRESS = "172.40.121.181";
    private static final int MAX_LENGTH = 1024 * 10;

    static void print(String message) {
        System.out.println(message);
    }

    static void send(String preTag, String message, DatagramSocket socket) {
        try {
            byte[] buf = message.getBytes();
            InetAddress address = InetAddress.getByName(NET_ADDRESS);
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
            socket.send(sendPacket);
            print(preTag + "Send Success!");
        } catch (IOException e) {
            print(preTag + "Send Error:" + e.getMessage());
        }
    }

    static void send(String preTag, String message, DatagramSocket socket, DatagramPacket receiveMsg) {
        try {
            byte[] buf = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, receiveMsg.getAddress(), receiveMsg.getPort());
            socket.send(sendPacket);
            print(preTag + "Send Success!");
        } catch (IOException e) {
            print(preTag + "Send Error:" + e.getMessage());
        }
    }

    static void receive(String preTag, DatagramSocket socket, OnMsgReceiveListener listener) {
        try {
            byte[] msg = new byte[MAX_LENGTH];
            DatagramPacket receivePacket = new DatagramPacket(msg, msg.length);
            socket.receive(receivePacket);
            String receiveMsg = new String(receivePacket.getData(), 0, receivePacket.getLength());
            print(preTag + "Receive Success:" + receiveMsg);
            listener.onReceived(receivePacket);
        } catch (IOException e) {
            print(preTag + "Receive Error:" + e.getMessage());
            listener.onReceived(null);
        }
    }

    static void closed(DatagramSocket socket, ExecutorService executor) {
        if (executor != null) {
            executor.shutdownNow();
        }
        if (socket != null) {
            socket.close();
        }
    }

    interface OnMsgReceiveListener {

        void onReceived(DatagramPacket receivePacket);
    }
}
