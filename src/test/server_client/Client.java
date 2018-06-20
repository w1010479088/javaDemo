package test.server_client;


public class Client extends BaseServer {

    public static void main(String[] args) {
        new Client().init();
    }

    @Override
    int getPort() {
        return 0;
    }

    @Override
    String preTag() {
        return "Client端--->";
    }

    public void send(String msg) {
        ServerUtil.send(preTag(), msg, getSocket());
    }
}
