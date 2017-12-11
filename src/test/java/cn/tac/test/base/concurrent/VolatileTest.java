package cn.tac.test.base.concurrent;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tac
 * @since 02/12/2017
 */
public class VolatileTest {
    private static int count;
    private static int count1;

    @Before
    public void setUp() throws Exception {
        count = 0;
        count1 = 0;
    }

    @Test
    public void testSimply() throws InterruptedException {
        ThreadPoolExecutor tp = new ThreadPoolExecutor(
                10,
                10,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100));
        for (int i = 0; i < 100; i++) {
            tp.execute(() -> {
                for (int j = 0; j < 200; j++) {
                    count++;
                    synchronized (this){
                        count1++;
                    }
                }
            });
        }

        tp.shutdown();

        tp.awaitTermination(1, TimeUnit.DAYS);

        System.out.println(count);
        System.out.println(count1);
        Assert.assertNotEquals(20000, count);
        Assert.assertEquals(20000, count1);
    }
}
