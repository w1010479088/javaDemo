package test.order;

public class Order {
    private long add_time;
    private String pay_sn;

    long add_time() {
        return add_time;
    }

    Order(String pay_sn, long add_time) {
        this.pay_sn = pay_sn;
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return pay_sn + "--" + add_time;
    }
}
