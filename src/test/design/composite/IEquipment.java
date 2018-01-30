package test.design.composite;


public interface IEquipment {
    String name();

    double price();

    double discounted_price();

    void add(IEquipment equipment);

    void remove(IEquipment equipment);
}
