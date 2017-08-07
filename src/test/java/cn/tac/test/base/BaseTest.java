package cn.tac.test.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tac
 * @since : 04/08/2017
 */
public class BaseTest {
    protected List<Integer> range(int start, int end) {
        List<Integer> range = new ArrayList<>();
        for (int a = start; a < end; a++) {
            range.add(a);
        }
        return range;
    }

    protected List<Integer> range(int count) {
        return range(0, count);
    }

    protected void divider() {
        System.out.println("----------------------------------------");
    }
}
