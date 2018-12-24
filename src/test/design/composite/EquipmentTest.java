package test.design.composite;

import test.util.LogUtil;

public class EquipmentTest {

    public static void test() {
        Cabinet cabinet = new Cabinet("Tower");
        Chassis chassis = new Chassis("PC Chassis");

        cabinet.add(chassis);
        chassis.add(new Disk("10 GB"));
        double price = cabinet.price();
        double discounted_price = cabinet.discounted_price();

        LogUtil.log("price--->" + price);
        LogUtil.log("discounted_price--->" + discounted_price);
    }
}
