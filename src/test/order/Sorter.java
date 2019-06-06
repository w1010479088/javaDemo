package test.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static test.order.SQLiteData.PAGE_MAX;

public class Sorter {
    public static void main(String[] args) {
        new Sorter().start();
    }

    private final int PAGE_SZIE = SQLiteData.PAGE_SIZE;
    private SQLiteData mSQL = new SQLiteData();

    private void start() {
        try {
            for (int page = 1; page <= PAGE_MAX; page++) {
                List<Order> requested = request(page);
                log(requested.toString());
                Thread.sleep(2 * 1000);
            }
        } catch (Exception ex) {
            log(ex.getMessage());
        }
    }

    private List<Order> request(int page) {
        List<Order> orders = get(page);

        int size = orders.size();
        int start = (page - 1) * PAGE_SZIE;
        int end = page * PAGE_SZIE;

        if (start > size - 1) {
            return Collections.emptyList();
        } else if (end > size - 1) {
            return orders.subList(start, size - 1);
        } else {
            return orders.subList(start, end);
        }
    }

    private List<Order> get(int page) {
        List<Order> merged = new ArrayList<>();
        merged.addAll(mSQL.order(page));
        merged.addAll(mSQL.vorder(page));
        merged.sort((first, second) -> -(int) (first.add_time() - second.add_time()));
        return merged;
    }

    private void log(String content) {
        System.out.println(content);
    }
}
