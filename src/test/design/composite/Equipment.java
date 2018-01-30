package test.design.composite;


public abstract class Equipment implements IEquipment {
    private String name;

    @Override
    public String name() {
        return name;
    }

    public Equipment(String name) {
        this.name = name;
    }

    public abstract double price();

    public abstract double discounted_price();

    public void add(IEquipment equipment) {
    }

    public void remove(IEquipment equipment) {
    }
}
