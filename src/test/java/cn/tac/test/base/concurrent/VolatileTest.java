package cn.tac.test.base.concurrent;

import org.junit.Test;

/**
 * This example is for showing usage of the modifier 'volatile'
 *
 * @author tac
 * @since 02/12/2017
 */
public class VolatileTest {
    /**
     * [See here]!!! Try to run test case below with either volatile or non-volatile field 'flag'
     */
//    private static boolean flag = false;        // With non-volatile flag, the while loop could not be stopped
    private static volatile boolean flag = false;     // With volatile flag, the while loop will be stopped around 3s passing

    @Test
    public void testWhileLoop() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (!flag) {
                // Spin forever if flag is not being true
                if (flag) {
                    System.out.println("[t1] flag: " + flag + ". Break the while loop.");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
                System.out.println("[t2] Setup flag value as true.");
                flag = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
