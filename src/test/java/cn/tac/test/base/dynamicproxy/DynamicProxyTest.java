package cn.tac.test.base.dynamicproxy;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * @author tac
 * @since 11/12/2017
 */
public class DynamicProxyTest {
    @Test
    public void testSimply() {
        Calculator origin = new CalculatorImpl();
        Calculator proxyInstance = (Calculator) Proxy.newProxyInstance(origin.getClass().getClassLoader(), origin.getClass().getInterfaces(), new LogHandler(origin));
        Assert.assertEquals(3, proxyInstance.add(1, 2));
    }

    static class LogHandler implements InvocationHandler {
        private Object origin;

        public LogHandler(Object origin) {
            this.origin = origin;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName() + " has been invoked!");
            return method.invoke(origin, args);
        }
    }
}
