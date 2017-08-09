package cn.tac.test.base.vm;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : tac
 * @since : 08/08/2017
 */
public class Finalize {

    private static A a = new A();

    @Test
    public void testSimply() throws InterruptedException {
        a = null;
        System.gc();
        Thread.sleep(500L);
        Assert.assertNotNull(a);

        a = null;
        System.gc();
        Assert.assertNull(a);
    }


    private static class A {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("do finalize");
            a = this;
        }
    }
}
