package cn.tac.test.base.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tac
 * @since : 06/08/2017
 */
public class StackOverFlowErrorTest {

    private static int stackDepth = 1;

    /**
     * VM options: -Xss160k
     * 设置栈容量为160k
     */
    public static void main(String[] args) {
        try {
            stackLeak();
        } catch (StackOverflowError e) {
//            e.printStackTrace();
            System.out.println("stack over flow!\r\ncurrent stack depth: " + stackDepth);
        }
    }

    private static void stackLeak() throws StackOverflowError {
        stackDepth++;
        stackLeak();
    }
}
