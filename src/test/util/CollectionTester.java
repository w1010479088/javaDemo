package test.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionTester {
    public static void main(String[] args) {
        new CollectionTester();
    }

    private List<String> listA = new ArrayList<>();
    private List<String> listB = new ArrayList<>();

    private CollectionTester() {
        test1();
    }

    //集合的交集
    private void test1() {
        initCol();
        LogUtil.log(filterSame().toString());
    }

    private void initCol() {
        for (int i = 0; i < 20; i++) {
            listA.add("a" + i);
            listB.add("b" + i);
        }
        String same1 = "111";
        String same2 = "1234";
        listA.add(8, same1);
        listA.add(10, same2);
        listB.add(5, same1);
        listB.add(18, same2);
    }

    private List<String> filterSame() {
        List<String> sameList = new ArrayList<>();
        List<String> maxList = listA.size() > listB.size() ? listA : listB;
        List<String> minList = listA.size() > listB.size() ? listB : listA;
        for (String item : minList) {
            if (maxList.contains(item)) {
                sameList.add(item);
            }
        }
        return sameList;
    }
}
