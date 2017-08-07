package cn.tac.test.base.collection.queue;

import cn.tac.test.base.BaseTest;
import org.junit.Test;

import java.util.*;

/**
 * @author : tac
 * @since : 04/08/2017
 */
public class PriorityQueueTest extends BaseTest {
    @Test
    public void testASC() {
        Random r = new Random();
        Queue<String> lls = new PriorityQueue<>();
        for (int i : range(10)) {
            lls.offer(Integer.toString(r.nextInt(10)));
        }

        while (!lls.isEmpty()){
            System.out.println(lls.poll());
        }
    }

    @Test
    public void testDESC() {
        Random r = new Random();
        Queue<String> lls = new PriorityQueue<>((o1, o2) -> Integer.parseInt(o2) - Integer.parseInt(o1));
        for (int i : range(10)) {
            lls.offer(Integer.toString(r.nextInt(10)));
        }

        while (!lls.isEmpty()){
            System.out.println(lls.poll());
        }
    }
}
