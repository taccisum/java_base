package cn.tac.test.base.clone;

import cn.tac.test.base.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author : tac
 * @since : 05/08/2017
 */
public class CloneTest extends BaseTest {

    @Test
    public void testSimply() throws CloneNotSupportedException {
        B b = new B("ba");
        A a = new A("a", "b", "c", b);
        System.out.println(a);
        A clone = a.clone();
        System.out.println(clone);
        b.setField1("ba`");     //因为是浅复制，所以修改b的值，a中指向b的值也跟着得到了改变
        System.out.println(clone);
        Assert.assertFalse(a.equals(clone));
    }


    private class  A implements Cloneable{
        public A(String filed1, String filed2, String filed3, B b) {
            this.filed1 = filed1;
            this.filed2 = filed2;
            this.filed3 = filed3;
            this.b = b;
        }

        private String filed1;
        private String filed2;
        private String filed3;
        private B b;

        public String getFiled1() {
            return filed1;
        }

        public void setFiled1(String filed1) {
            this.filed1 = filed1;
        }

        public String getFiled2() {
            return filed2;
        }

        public void setFiled2(String filed2) {
            this.filed2 = filed2;
        }

        public String getFiled3() {
            return filed3;
        }

        public void setFiled3(String filed3) {
            this.filed3 = filed3;
        }

        public A clone() throws CloneNotSupportedException {
            return (A)super.clone();        //只执行浅复制
        }

        @Override
        public String toString() {
            return "A{" +
                    "filed1='" + filed1 + '\'' +
                    ", filed2='" + filed2 + '\'' +
                    ", filed3='" + filed3 + '\'' +
                    ", b=" + b +
                    '}';
        }

        public B getB() {
            return b;
        }

        public void setB(B b) {
            this.b = b;
        }
    }

    private class B {
        public B(String field1) {
            this.field1 = field1;
        }

        private String field1;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        @Override
        public String toString() {
            return "B{" +
                    "field1='" + field1 + '\'' +
                    '}';
        }
    }
}
