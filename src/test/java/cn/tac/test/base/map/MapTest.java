package cn.tac.test.base.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : tac
 * @since : 04/08/2017
 */
public class MapTest {

    @Test
    public void testSimply() {
        Map<String, String> map = new HashMap<>();
        fill(map);
        System.out.println(map);
        System.out.println(map.getOrDefault("4", "d"));
    }

    @Test
    public void testIterator() {
        Map<String, String> map = new HashMap<>();
        fill(map);
        for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private void fill(Map<String, String> map) {
        map.put("2", "b");
        map.put("1", "a");
        map.put("3", "c");
    }
}
