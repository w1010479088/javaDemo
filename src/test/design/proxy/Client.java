package test.design.proxy;

public class Client implements ISubject {

    public static void main(String[] args) {
        Target target = new Target();
        Proxy proxy = new Proxy(target);
        Client client = new Client(proxy);
        client.doSomething();
    }

    private ISubject subject;

    public Client(ISubject subject) {
        this.subject = subject;
    }

    @Override
    public void doSomething() {
        this.subject.doSomething();
    }
}
