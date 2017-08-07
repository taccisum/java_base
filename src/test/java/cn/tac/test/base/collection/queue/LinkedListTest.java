package cn.tac.test.base.collection.queue;

import cn.tac.test.base.BaseTest;
import cn.tac.test.base.annotation.Checked;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : tac
 * @since : 04/08/2017
 */

@Checked(src = true)
public class LinkedListTest extends BaseTest {
    @Test
    public void testSimply() {
        Queue<String> lls = new LinkedList<>();
        for (int i : range(10)) {
            lls.offer(Integer.toString(i));
        }

        while (!lls.isEmpty()){
            System.out.println(lls.poll());
        }
    }
}
