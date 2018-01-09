package test.factory;

public class SmsSender implements ISender {
    @Override
    public String send() {
        return "SmsSender sent!";
    }
}
