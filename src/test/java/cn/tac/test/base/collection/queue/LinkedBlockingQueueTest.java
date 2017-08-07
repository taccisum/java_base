package cn.tac.test.base.collection.queue;

import cn.tac.test.base.BaseTest;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : tac
 * @since : 04/08/2017
 */
public class LinkedBlockingQueueTest extends BaseTest {
    public static final int COUNT = 20;
    public static final long INTERVAL_MILLIS = 100L;
    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

    //test

    @Test
    public void testSimply() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(this::produce);
        executorService.submit(this::consume);
        while (!executorService.isTerminated()) {
        }
    }

    //

    public void produce() {
        for (int i : range(COUNT)) {
            try {
                queue.put(Integer.toString(i));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.currentThread().interrupt();
    }

    public void consume() {
        for (int i : range(COUNT)) {
            try {
                System.out.println(queue.take());
                Thread.sleep(INTERVAL_MILLIS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Thread.currentThread().interrupt();
    }
}
