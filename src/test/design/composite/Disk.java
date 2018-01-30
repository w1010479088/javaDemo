package test.design.composite;

public class Disk extends Equipment {

    public Disk(String name) {
        super(name);
    }

    @Override
    public double price() {
        return 5;
    }

    @Override
    public double discounted_price() {
        return 2.5;
    }
}
