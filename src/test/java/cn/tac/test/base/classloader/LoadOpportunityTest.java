package cn.tac.test.base.classloader;

import cn.tac.test.base.BaseTest;
import org.junit.After;
import org.junit.Test;

/**
 * @author : tac
 * @since : 09/08/2017
 */
public class LoadOpportunityTest extends BaseTest {

    @After
    public void tearDown(){
        divider();
    }

    /**
     * 以下三种情况会导致类初始化：
     * <ul>
     *     <li>实例化类(new)</li>
     *     <li>调用类的静态方法(invokestatic)</li>
     *     <li>获取或赋值类的静态变量(getstatic || putstatic)</li>
     * </ul>
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testNewOrStaticReference() {
        new New();
        divider();
        InvokeStatic.nothing();
        divider();
        String field = GetStatic.field;
        divider();
        PutStatic.field = "";
    }

    /**
     * 通过反射对类进行调用时会导致初始化
     */
    @Test
    public void testReflectReference() throws ClassNotFoundException {
        Class.forName("cn.tac.test.base.classloader.Reflect");
    }

    /**
     * 加载子类时，若父类未进行初始化，则会先初始化父类
     */
    @Test
    public void testLoadParentByChild() {
        new Child();
    }

    /**
     * 非直接引用时，不会触发初始化（例子中的Chile4IndirectRef类）
     * 可通过VM Options -XX:+TraceClassLoading使子类在该情况下被初始化
     */
    @Test
    public void testIndirectReference() {
        String s = Child4IndirectRef.field1;
    }

    /**
     * 数组的new并不会导致类的初始化，
     * 原因是虚拟机中会生成并初始化一个名为Lcn.tac.test.base.classloader.ArrayRef的类，
     * 其直接继承自Object，创建动作由字节码指令newarray触发。这个类是一个代表元素类型为
     * cn.tac.test.base.classloader.ArrayRef的一维数组
     */
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void testArrayRef() {
        ArrayRef[] array = new ArrayRef[10];
    }

    /**
     * 对常量的引用不会导致类的初始化
     */
    @Test
    public void testConstRef() {
        String s = ConstRef.FIELD;
    }
}

class New {
    static {
        System.out.println("loading class New");
    }
}
class InvokeStatic {
    static {
        System.out.println("loading class InvokeStatic");
    }

    public static void nothing(){}
}
class GetStatic {
    public static String field = "";
    static {
        System.out.println("loading class GetStatic");
    }
}
class PutStatic {
    public static String field = "";
    static {
        System.out.println("loading class PutStatic");
    }
}

class Reflect{
    static {
        System.out.println("loading class Reflect");
    }
}

class Parent {
    static {
        System.out.println("loading class Parent");
    }
}

class Child extends Parent {
    static {
        System.out.println("loading class Child");
    }
}

class Parent4IndirectRef {
    public static String field1 = "10";

    static {
        System.out.println("loading class Parent4IndirectRef");
    }
}
class Child4IndirectRef extends Parent4IndirectRef {
    static {
        System.out.println("loading class Child4IndirectRef");
        field1 = "20";
    }
}

class ArrayRef{
    static {
        System.out.println("loading class ArrayRef");
    }
}

class ConstRef{
    public static final String FIELD = "";
    static {
        System.out.println("loading class ConstRef");
    }
}