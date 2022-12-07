package cn.tac.test.base.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * This example is for showing simple usage of the ReadWriteLock
 *
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/12/7
 */
public class ReadWriteLockSimpleTest {
    /**
     * t1: obtain read lock
     * t2: try to obtain write lock but blocked by read lock t1 holding
     * t1: sleep 1s
     * t1: release read lock
     * t2: obtain write lock
     * t2: release write lock
     */
    @Test
    public void index() throws InterruptedException {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.readLock().lock();
                System.out.println("1. [t1] Have occupied read lock.");
                Thread.sleep(1000);
                lock.readLock().unlock();
                System.out.println("4. [t1] Have released read lock.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            lock.writeLock().lock();        // Will be blocked because of read lock t1 holding
            System.out.println("5. [t2] Have occupied write lock.");
            lock.writeLock().unlock();
            System.out.println("6. [t2] Have released write lock.");
        });
        Thread t3 = new Thread(() -> {
            lock.readLock().lock();        // Will succeed because the read lock t1 holding doesn't block another thread to obtain read lock
            System.out.println("2. [t3] Have occupied read lock.");
            lock.readLock().unlock();
            System.out.println("3. [t3] Have released read lock.");
        });

        t1.start();
        Thread.sleep(100);
        t3.start();     // t3 will be blocked by write lock once t2 try to obtain it so that t3 should start before t2
        Thread.sleep(100);
        t2.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
