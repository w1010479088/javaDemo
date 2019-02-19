package test.map;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import test.util.LogUtil;

public class MapForEach {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "大俊子1");
        map.put("2", "大俊子2");
        map.put("3", "大俊子3");
        map.put("4", "大俊子4");
        Set<String> keys = map.keySet();
        Collection<String> values = map.values();


        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
        }

        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
        }

        for (String key : map.keySet()) {
            String value = map.get(key);
        }

    }

    private static void log(String content) {
        LogUtil.log(content);
    }
}

class ListIteratorTest {
    private void test() {
        List<String> list = new ArrayList<>();
        list.add("大俊子来了!");
        ListIterator<String> listIterator = list.listIterator();
        listIterator.hasNext();
        listIterator.next();
        listIterator.nextIndex();

        listIterator.hasPrevious();
        listIterator.previous();
        listIterator.previousIndex();
    }
}

class SortedMapTest {

    private void test() {
    }
}