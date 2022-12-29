package cn.tac.test.base.thread;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author taccisum - liaojinfeng6938@dingtalk.com
 * @since 2022/12/29
 */
public class ForkJoinPoolTest {
    @Test
    public void calTest() {
        ForkJoinPool pool = new ForkJoinPool(8);
        StopWatch sw = new StopWatch();
        int end = 1000000000;

        sw.start();
        Long sum = pool.invoke(new CalTask(0, end, end / 8));
        sw.stop();
        System.out.println(String.format("[forkjoin] sum: %d. take time: %sms", sum, sw.getLastTaskTimeMillis()));

        sw.start();
        long s = 0;
        for (int i = 0; i < end; i++) {
            s += i;
        }
        sw.stop();
        System.out.println(String.format("[single thread] sum: %d. take time: %sms", s, sw.getLastTaskTimeMillis()));

        sw.start();
        long s1 = LongStream.range(0, end).parallel().sum();
        sw.stop();
        // JDK stream 倒是优化得不错
        System.out.println(String.format("[stream parallel] sum: %d. take time: %sms", s1, sw.getLastTaskTimeMillis()));
    }

    @Test
    public void ioTest() throws InterruptedException {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        StopWatch sw = new StopWatch();
        int end = 20;

        sw.start();
        Long sum = pool.invoke(new IoTask(0, end, end / 8));
        sw.stop();
        System.out.println(String.format("[forkjoin] sum: %d. take time: %sms", sum, sw.getLastTaskTimeMillis()));

        int s = 0;
        sw.start();
        for (int i = 0; i < end; i++) {
            s += i;
            Thread.sleep(50);       // 模拟 io 等待
        }
        sw.stop();
        System.out.println(String.format("[single thread] sum: %d. take time: %sms", s, sw.getLastTaskTimeMillis()));
    }

    class CalTask extends RecursiveTask<Long> {
        private int start = 0;
        private int end = 0;
        private int threshold;

        public CalTask(int start, int end, int threshold) {
            this.start = start;
            this.end = end;
            this.threshold = threshold;
        }

        @Override
        protected Long compute() {
            if ((this.end - this.start) > threshold) {
                int middle = (this.end + this.start) / 2;
                CalTask left = new CalTask(this.start, middle, this.threshold);
                CalTask right = new CalTask(middle, this.end, this.threshold);
                ForkJoinTask<Long> lfork = left.fork();
                ForkJoinTask<Long> rfork = right.fork();
                // 这里注意一定要把 left & right 都先 fork 了再 join，否则会导致性能问题（无法充分发挥多线程优势了）
                return lfork.join() + rfork.join();
            }

            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += i;
            }
            return sum;
        }
    }

    class IoTask extends RecursiveTask<Long> {
        private int start = 0;
        private int end = 0;
        private int threshold;

        public IoTask(int start, int end, int threshold) {
            this.start = start;
            this.end = end;
            this.threshold = threshold;
        }

        @Override
        protected Long compute() {
            if ((this.end - this.start) > threshold) {
                int middle = (this.end + this.start) / 2;
                IoTask left = new IoTask(this.start, middle, this.threshold);
                IoTask right = new IoTask(middle, this.end, this.threshold);
                ForkJoinTask<Long> lfork = left.fork();
                ForkJoinTask<Long> rfork = right.fork();
                // 这里注意一定要把 left & right 都先 fork 了再 join，否则会导致性能问题（无法充分发挥多线程优势了）
                return lfork.join() + rfork.join();
            }

            long sum = 0;
            for (int i = start; i < end; i++) {
                try {
                    Thread.sleep(50);       // 模拟 io 等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sum += i;
            }
            return sum;
        }
    }
}
