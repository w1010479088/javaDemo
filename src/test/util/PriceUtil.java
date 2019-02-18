package test.util;

import java.text.DecimalFormat;

public class PriceUtil {
    public static void main(String[] args) {
        double price1 = getPricePrecise(1.554);
        double price2 = getPricePrecise(1.555);
        double price3 = getPricePrecise(1.556);

        double price4 = Double.parseDouble(getUnitPriceNoDecimal(1.554));
        double price5 = Double.parseDouble(getUnitPriceNoDecimal(1.555));
        double price6 = Double.parseDouble(getUnitPriceNoDecimal(1.556));

        LogUtil.log(String.format("价格系:%f,%f,%f", price1, price2, price3));
        LogUtil.log(String.format("价格系:%f,%f,%f", price4, price5, price6));
    }

    //返回浮点数价格
    public static String getUnitPrice(String price) {
        return getUnitPrice(Double.parseDouble(price));
    }

    public static String getUnitPrice(double price) {
        return "¥" + getUnitPriceNoDecimal(price);
    }

    public static String getUnitPriceNoDecimal(double price) {
        return new DecimalFormat("#.##").format(price);
    }

    public static String getUnitPriceNoDecimal(String price) {
        return getUnitPriceNoDecimal(Double.parseDouble(price));
    }

    public static double getPricePrecise(double price) {
        return ((int) (price * 100 + 0.5)) / 100.00;
    }
}
