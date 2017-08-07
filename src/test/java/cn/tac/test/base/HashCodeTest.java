package cn.tac.test.base;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : tac
 * @since : 05/08/2017
 */
public class HashCodeTest extends BaseTest {
    @Test
    public void testSimply() {
        Assert.assertNotEquals(new Object().hashCode(), new Object().hashCode());
    }

    @Test
    public void testModify() {
        A a = new A("a", "b", "c");
        int hash1 = a.hashCode();
        Assert.assertEquals(hash1, a.hashCode());

        a.setField2("bbbb");
        Assert.assertEquals(hash1, a.hashCode());       //原生的hashCode()返回对象的地址，所以即使修改对象字段，hashCode也不会受到影响
    }

    @Test
    public void testString() {
        Assert.assertEquals(new String("abc").hashCode(), new String("abc").hashCode());
    }

    private class A{
        public A(String field1, String field2, String field3) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
        }

        private String field1;
        private String field2;
        private String field3;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }

        public String getField2() {
            return field2;
        }

        public void setField2(String field2) {
            this.field2 = field2;
        }

        public String getField3() {
            return field3;
        }

        public void setField3(String field3) {
            this.field3 = field3;
        }
    }
}

