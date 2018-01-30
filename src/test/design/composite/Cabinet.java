package test.design.composite;

public class Cabinet extends CompositeEquipment {

    public Cabinet(String name) {
        super(name);
    }

    @Override
    public double price() {
        return 4 + super.price();
    }

    @Override
    public double discounted_price() {
        return 3 + super.discounted_price();
    }
}
