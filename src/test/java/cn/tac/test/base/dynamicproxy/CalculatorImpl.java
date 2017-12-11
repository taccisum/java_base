package cn.tac.test.base.dynamicproxy;

/**
 * @author tac
 * @since 11/12/2017
 */
public class CalculatorImpl implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
