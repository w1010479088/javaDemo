package test.factory;

public class EmailFactory implements ISenderProvider {
    @Override
    public ISender provide() {
        return new EmailSender();
    }
}
