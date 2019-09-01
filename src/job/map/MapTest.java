package job.map;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

    private void a(Map<String, String> map) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> item : entries) {
            log(item.getKey(), item.getValue());
        }
    }

    private void b(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            log(item.getKey(), item.getValue());
        }
    }

    private void c(Map<String, String> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            log(key, "");
        }
        Collection<String> values = map.values();
        for (String value : values) {
            log("", value);
        }
    }

    private void d(Map<String, String> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            log(key, value);
        }
    }

    private void log(String key, String value) {

    }

    private void a1(Map<String, String> map) {
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> item : entries) {
            log(item.getKey(), item.getValue());
        }
    }

    private void b1(Map<String, String> map) {
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> item = iterator.next();
            log(item.getKey(), item.getValue());
        }
    }

    private void c1(Map<String, String> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            log(key, null);
        }
        Collection<String> values = map.values();
        for (String value : values) {
            log(null, value);
        }
    }

    private void d1(Map<String, String> map) {
        Set<String> keys = map.keySet();
        for (String key : keys) {
            String value = map.get(key);
            log(key, value);
        }
    }
}
