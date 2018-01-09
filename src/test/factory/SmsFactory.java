package test.factory;

public class SmsFactory implements ISenderProvider {

    @Override
    public ISender provide() {
        return new SmsSender();
    }
}
