package cn.tac.test.base.classloader;

import cn.tac.test.base.BaseTest;
import org.junit.Test;

/**
 * @author : tac
 * @since : 30/08/2017
 */
public class ClassLoaderTest extends BaseTest {

    @Test
    public void testSimply() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class cls = this.getClass().getClassLoader().loadClass("cn.tac.test.base.classloader.ClassLoaderTest$HelloClassLoader");
        System.out.println(cls);
        System.out.println(((HelloClassLoader) cls.newInstance()).getField1());      //如果不是静态类，这里newInstance会出错
    }

    @Test
    public void testClassInit() {
        ClassInit ins = ClassInit.getInstance();
        System.out.println(ClassInit.a);
        System.out.println(ClassInit.b);
    }

    @Test
    public void testMethod() {
        String set = new java.util.BitSet() {{
            set(1, 100 + 1);
        }}.toString();
        System.out.println(set);
        System.out.append(set, 1, set.length() - 1);
    }

    private static class ClassInit {
        private static ClassInit instance = new ClassInit();        //此处new instance会触发类初始化，并执行构造函数，将a和b置为1，时机早于下面两个静态变量的初始化
        public static int a;
        public static int b = 0;        //因为初始化执行时机在构造函数后面，所以值会被覆盖为0

        private ClassInit() {
            a = 1;
            b = 1;
        }

        public static ClassInit getInstance() {
            return instance;
        }
    }


    public static class HelloClassLoader {
        public HelloClassLoader() {
            field1 = "hello class loader";
        }

        private String field1;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }
    }
}
