package test.order;

import java.util.ArrayList;
import java.util.List;

class SQLiteData {
    public static final int PAGE_MAX = 3;
    public static final int PAGE_SIZE = 10;
    private static final int COUNT = PAGE_MAX * PAGE_SIZE / 2;
    private List<Order> mOrders = new ArrayList<>();
    private List<Order> mVOrders = new ArrayList<>();

    SQLiteData() {
        initData();
    }

    List<Order> order(int page) {
        return get(mOrders, page);
    }

    List<Order> vorder(int page) {
        return get(mVOrders, page);
    }

    private void initData() {
        for (int i = 0; i < COUNT; i++) {
            mOrders.add(newItem("AOrder", 2 * i));
            mVOrders.add(newItem("VOrder", 2 * i + 1));
        }
        sort(mOrders);
        sort(mVOrders);
    }

    private void sort(List<Order> list) {
        list.sort((first, second) -> -(int) (first.add_time() - second.add_time()));
    }

    private Order newItem(String tag, long time) {
        return new Order(tag, time);
    }

    private List<Order> get(List<Order> list, int page) {
        int start = 0;
        int end = page * 10 - 1;
        int size = list.size();
        end = end < size ? end : size;
        return list.subList(start, end);
    }
}
