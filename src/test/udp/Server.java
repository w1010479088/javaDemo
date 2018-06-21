package test.udp;

public class Server extends BaseServer {

    public static void main(String[] args) {
        new Server().init();
    }

    @Override
    int getPort() {
        return ServerUtil.PORT_NUM;
    }

    @Override
    String preTag() {
        return "Server端--->";
    }

    @Override
    void send(String msg) {
        if (getPacket() != null) {
            ServerUtil.send(preTag(), msg, getSocket(), getPacket());
        }
    }
}
