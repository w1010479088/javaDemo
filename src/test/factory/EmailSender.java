package test.factory;

public class EmailSender implements ISender {
    @Override
    public String send() {
        return "EmailSender sent!";
    }
}
