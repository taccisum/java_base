package cn.tac.test.base.collection.stack;

import cn.tac.test.base.BaseTest;
import org.junit.Test;

import java.util.Stack;

/**
 * @author : tac
 * @since : 04/08/2017
 */
public class StackTest extends BaseTest {

    @Test
    public void testSimply() {
        Stack<String> stack = new Stack<>();
        for (int i : range(10)){
            stack.push(Integer.toString(i));
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }

}
