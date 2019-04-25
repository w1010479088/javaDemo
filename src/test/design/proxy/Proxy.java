package test.design.proxy;

public class Proxy implements ISubject {
    private Target target;

    public Proxy(Target target) {
        this.target = target;
    }

    @Override
    public void doSomething() {
        target.ok();
    }
}
