package test.interfacex;

import test.util.LogUtil;

public class Months {
    private String name;

    private Months(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static final Months
            JANUARY = new Months("January"),
            FEBRUARY = new Months("February"),
            MARCH = new Months("March"),
            APRIL = new Months("April"),
            MAY = new Months("May"),
            JUNE = new Months("June"),
            JULY = new Months("July"),
            AUGUST = new Months("August"),
            SEPTEMBER = new Months("September"),
            OCTOBER = new Months("October"),
            NOVEMBER = new Months("November"),
            DECEMBER = new Months("December");

    private static final Months[] MONTHS = {JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER};

    public static Months month(int index) {
        if (index < 1 || index > 12) {
            throw new RuntimeException("Months输入的index不合法");
        } else {
            return MONTHS[index - 1];
        }
    }

    public static void main(String[] args) {
        LogUtil.log(month(8).toString());
    }
}