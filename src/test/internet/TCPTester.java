package test.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import test.util.LogUtil;

public class TCPTester {

    public static void main(String[] args) {

    }

    private void inetAddr() {
        try {
            InetAddress address = InetAddress.getByName("www.baidu.com");
            log(address.toString());
            InetAddress localHost = InetAddress.getLocalHost();
            log(localHost.toString());
            InetAddress byName = InetAddress.getByName(null);
            InetAddress byName2 = InetAddress.getByName("localhost");
            InetAddress byName3 = InetAddress.getByName("127.0.0.1");
            log(byName.toString());
            log(byName2.toString());
            log(byName3.toString());
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    public static class JabberServer {
        static final int PROT = 8080;
        static final String END_TAG = "END";

        public static void main(String[] args) throws IOException {
            ServerSocket serverSocket = new ServerSocket(PROT);
            try {
                Socket dataSocket = serverSocket.accept();
                IChannel channel = new Pipe(dataSocket);
                while (true) {
                    String line = channel.read();
                    if (END_TAG.equals(line)) {
                        channel.close();
                        break;
                    } else {
                        log(line);
                        channel.write("Server sending...");
                    }
                }
            } finally {
                serverSocket.close();
            }
        }
    }

    public static class JabberClient {

        public static void main(String[] args) throws IOException {
            InetAddress addr = InetAddress.getByName("localhost");
            Socket socket = new Socket(addr, JabberServer.PROT);
            IChannel channel = new Pipe(socket);
            try {
                for (int i = 0; i < 10; i++) {
                    channel.write("Client Sending..." + i);
                    log("Client Receiving..." + channel.read());
                }
                channel.write("END");
            } finally {
                channel.close();
            }
        }
    }

    public static class Pipe implements IChannel {
        private Socket socket;
        private BufferedReader reader;
        private PrintWriter writer;

        Pipe(Socket socket) throws IOException {
            this.socket = socket;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        }

        @Override
        public String read() throws IOException {
            return reader.readLine();
        }

        @Override
        public void write(String content) {
            writer.println(content);
        }

        @Override
        public void close() throws IOException {
            if (socket != null) {
                log("Closing");
                socket.close();
            }
        }
    }

    interface IChannel {

        String read() throws IOException;

        void write(String content);

        void close() throws IOException;
    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}
