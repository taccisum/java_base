package cn.tac.test.base.map;

import cn.tac.test.base.BaseTest;
import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author : tac
 * @since : 05/08/2017
 */
public class TreeMapTest extends BaseTest {

    @Test
    public void testSimply() {
        SortedMap<Integer, String> map = new TreeMap<>((o1, o2) -> o2 - o1);
        fill(map);
        System.out.println(map);
    }

    @Test
    public void testSplit() {
        SortedMap<Integer, String> map = new TreeMap<>((o1, o2) -> o2 - o1);
        fill(map);
        System.out.println(map.tailMap(3));
//        System.out.println(map.tailMap(4));       //tip: 没有4这个key也可以
        divider();
        System.out.println(map.headMap(2));
        divider();
        System.out.println(map.subMap(4, 1));       //tip: 没有4这个key也可以
    }


    private void fill(SortedMap<Integer, String> map) {
        map.put(3, "shenzhen");
        map.put(1, "beijing");
        map.put(2, "shanghai");
        map.put(5, "guangzhou");
    }

}
