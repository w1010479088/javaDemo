package test.server_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class BaseServer {
    private ExecutorService mExecutor;
    private DatagramSocket mSocket;
    private DatagramPacket mRecPacket;
    private boolean mStop;

    abstract int getPort();

    abstract String preTag();

    abstract void send(String msg);

    DatagramSocket getSocket() {
        return mSocket;
    }

    DatagramPacket getPacket() {
        return mRecPacket;
    }

    void init() {
        try {
            mSocket = new DatagramSocket(getPort());
            mExecutor = Executors.newFixedThreadPool(2);
            initReceiveThread();
            initInputThread();
        } catch (IOException e) {
            ServerUtil.print(preTag() + e.getMessage());
        }
    }

    private void initReceiveThread() {
        mExecutor.submit(() -> {
            while (!mStop) {
                ServerUtil.receive(preTag(), mSocket, receivePacket -> {
                    mRecPacket = receivePacket;
                    response();
                });
            }
        });
    }

    private void initInputThread() {
        mExecutor.submit(() -> {
            while (!mStop) {
                checkInput();
            }
        });
    }

    private void checkInput() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while (!mStop && (line = reader.readLine()) != null) {
                if ("exit".equals(line)) {
                    closed();
                } else {
                    byte[] buf = line.getBytes();
                    String input = new String(buf);
                    send(input);
                }
            }
        } catch (IOException e) {
            ServerUtil.print(preTag() + e.getMessage());
        }
    }

    private void response() {

    }

    private void closed() {
        mStop = true;
        ServerUtil.closed(mSocket, mExecutor);
    }
}
