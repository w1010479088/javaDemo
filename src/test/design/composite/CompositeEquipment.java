package test.design.composite;

import java.util.*;

public abstract class CompositeEquipment extends Equipment {

    public CompositeEquipment(String name) {
        super(name);
    }

    private List<IEquipment> equipments = new ArrayList<>();

    @Override
    public double price() {
        double price = 0;
        for (IEquipment equipment : equipments) {
            price += equipment.price();
        }
        return price;
    }

    @Override
    public double discounted_price() {
        double discounted_price = 0;
        for (IEquipment equipment : equipments) {
            discounted_price += equipment.discounted_price();
        }
        return discounted_price;
    }

    @Override
    public void add(IEquipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public void remove(IEquipment equipment) {
        equipments.remove(equipment);
    }
}
