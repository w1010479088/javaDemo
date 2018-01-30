package test.design.composite;

public class Chassis extends CompositeEquipment {

    public Chassis(String name) {
        super(name);
    }

    @Override
    public double price() {
        return 10 + super.price();
    }

    @Override
    public double discounted_price() {
        return 5 + super.discounted_price();
    }
}
