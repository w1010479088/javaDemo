package test.internet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import test.util.LogUtil;

public class UDPTester {

    static DatagramPacket dataGram(String data, InetAddress dest, int port) {
        byte[] buf = new byte[data.length() + 1];
        data.getBytes(0, data.length(), buf, 0);
        return new DatagramPacket(buf, buf.length, dest, port);
    }

    static String toString(DatagramPacket packet) {
        return new String(packet.getData(), 0, packet.getLength());
    }

    public static class ChatterServer {
        static final int PORT = 1711;
        private byte[] buf = new byte[1000];
        private DatagramPacket packet = new DatagramPacket(buf, buf.length);

        public static void main(String[] args) {
            new ChatterServer();
        }

        ChatterServer() {
            DatagramSocket socket = null;
            try {
                socket = new DatagramSocket(PORT);
                log("Server started");
                while (true) {
                    socket.receive(packet);

                    String recvd = UDPTester.toString(packet) + packet.getAddress() + packet.getPort();
                    log(recvd);

                    String echoString = "Echoed: " + recvd;
                    socket.send(dataGram(echoString, packet.getAddress(), packet.getPort()));
                }
            } catch (IOException ex) {
                log(ex.getMessage());
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }

    static class ChatterClient extends Thread {
        private DatagramSocket socket;
        private InetAddress hostAddress;
        private byte[] buf = new byte[1000];
        private DatagramPacket packet = new DatagramPacket(buf, buf.length);
        private int id;

        public static void main(String[] args) {
            for (int i = 0; i < 10; i++) {
                new ChatterClient(i);
            }
        }

        ChatterClient(int id) {
            this.id = id;
            try {
                socket = new DatagramSocket();
                hostAddress = InetAddress.getByName("localhost");
            } catch (IOException ex) {
                log(ex.getMessage());
            }
            start();
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 25; i++) {
                    String msg = "Client #" + id + ",index=" + i;
                    socket.send(dataGram(msg, hostAddress, ChatterServer.PORT));
                    socket.receive(packet);
                    String rcvd = "Client #" + id + UDPTester.toString(packet);
                    log(rcvd);
                }
            } catch (IOException ex) {
                log(ex.getMessage());
            }
        }
    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}
