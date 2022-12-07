package cn.tac.test.base.thread;

import org.junit.Test;

/**
 * This example is for showing usage of methods #join & #wait
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/12/7
 */
public class WaitAndJoinTest {
    private static final Object LOCK = new Object();

    @Test
    public void index() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                synchronized (LOCK) {
                    System.out.println("[t1]I have entered the synchronized block and meanwhile obtained the lock.");
                    System.out.println("[t1]Now i'm doing some work. It may take some time(1s)...");
                    Thread.sleep(1000);
                    System.out.println("[t1]The work has been done. Now i should release the lock for a moment and wait notification from other thread 't2'");
                    System.out.println("[t1]Zzz...");
                    LOCK.wait();
                    System.out.println("[t1]Haha, i have waked up.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread.sleep(100);       // Ensure thread t1 gain the lock first
        Thread t2 = new Thread(() -> {
            try {
                synchronized (LOCK) {
                    System.out.println("[t2]I have entered the synchronized block and meanwhile obtained the lock.");
                    System.out.println("[t2]Now i'm doing some work. It may take some time(1s)...");
                    Thread.sleep(1000);
                    System.out.println("[t2]Ok, It's done. Now i should notify thread 't1' to do his job.");
                    LOCK.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        System.out.println("[main]All threads have started. I should wait their finishing.");
        t1.join();
        System.out.println("[main]Thread 't1' has finished.");
        t2.join();
        System.out.println("[main]Thread 't2' has finished.");
        System.out.println("[main]Ok!!");
    }
}
