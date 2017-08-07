package cn.tac.test.base.map;

import cn.tac.test.base.BaseTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : tac
 * @since : 05/08/2017
 */
public class LinkHashMapTest extends BaseTest {

    @Test
    public void testSimply() {
        HashMap<String, String> hashMap = new HashMap<>();
        fill(hashMap);
        System.out.println(hashMap);

        divider();

        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        fill(linkedHashMap);
        System.out.println(linkedHashMap);
    }

    private void fill(Map<String, String> map) {
        map.put("zhutao", "dongguan");
        map.put("tac", "shenzhen");
        map.put("qiwei", "guangzhou");
        map.put("yufeng", "qingyuan");
        map.put("wencan", "jiangmen");
    }

}
