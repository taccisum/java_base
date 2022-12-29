package cn.tac.test.base.thread;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/12/29
 */
public class ThreadPoolExecutorTest {
    @Test
    public void index() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                10,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()
        );

        for (int i = 0; i < 10; i++) {
            executor.submit(() -> {
                System.out.println("task completed!");
            });
        }

        executor.shutdown();        // shutdown 后已提交的任务会被等待执行完，但新任务不再被接受（会报错或丢弃，取决于 Reject 策略）
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {      // 阻塞并等待任务完成（shutdown 后）
            System.out.println("Timeout");
        }
    }

    @Test
    public void coreSize() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4,
                3,      // 存活时间为 3s
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()     // 使用了无界队列，意味着任务永远不会溢出，因此 maximum pool size 不会生效
        );

        System.out.println("Allow core thread timeout: " + executor.allowsCoreThreadTimeOut());

        System.out.println("New pool, worker size: " + getWorkers(executor).size());       // 刚创建时 worker 数量为 0

        for (int i = 0; i < 20; i++) {
            // 随着 task 被提交，worker 被创建，并且数量固定在 core size 值
            executor.submit(() -> {
                Collection<Object> workers = getWorkers(executor);
                System.out.println("Task completed. count: " + executor.getCompletedTaskCount() + ". worker size: " + workers.size());
            });
            Thread.sleep(50);
        }

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("Now worker size: " + getWorkers(executor).size());
        }

        executor.allowCoreThreadTimeOut(true);
        System.out.println("Allow core thread timeout: " + executor.allowsCoreThreadTimeOut());
        for (int i = 0; i < 5; i++) {
            // 由于设置了 Allow Core Thread Timeout，3s core size 会变为 0
            Thread.sleep(1000);
            System.out.println("Now worker size: " + getWorkers(executor).size());
        }
    }

    @Test
    public void maximumSize() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                5,
                3,      // 存活时间为 3s
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(5),       // 使用有界队列

                new ThreadPoolExecutor.DiscardPolicy()      // 防止任务提交速率过快导致 Abort，这里使用 Discard 策略
        );

        System.out.println("Allow core thread timeout: " + executor.allowsCoreThreadTimeOut());
        System.out.println("New pool, worker size: " + getWorkers(executor).size());       // 刚创建时 worker 数量为 0

        for (int i = 0; i < 20; i++) {
            executor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Task completed. count: " + executor.getCompletedTaskCount()
                        + ". Worker size: " + executor.getPoolSize()
                        + ". Queue size: " + executor.getQueue().size()
                        + ". Largest Pool size ever: " + executor.getLargestPoolSize()
                );
            });
            Thread.sleep(200);      // 每秒提交 5 个任务，只有 2 个线程是处理不过来的，很快任务队列就会被撑满，然后触发创建新线程（不超过 MaximumPoolSize）
        }

        while (executor.getCompletedTaskCount() < 20) {
            // 等待所有任务被完成
            Thread.yield();
        }

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println("Now worker size: " + executor.getPoolSize());       // 临时增加来处理的 Thread 会被超时回收掉，但 Core Thread 会被保留
        }
    }

    private Collection<Object> getWorkers(ThreadPoolExecutor executor) {
        try {
            Field f = executor.getClass().getDeclaredField("workers");
            f.setAccessible(true);
            Collection<Object> workers = (Collection<Object>) f.get(executor);
            f.setAccessible(false);
            return workers;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }
}
