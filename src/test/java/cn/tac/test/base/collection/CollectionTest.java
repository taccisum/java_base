package cn.tac.test.base.collection;

import cn.tac.test.base.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * @author : tac
 * @since : 04/08/2017
 */
public class CollectionTest extends BaseTest {

    @Before
    public void setUp() {
        divider();
    }

    @After
    public void tearDown() {
        divider();
    }


    @Test
    public void testSimply() {
        Collection<String> collection = new ArrayList<>();

        for (int i : range(10)) {
            collection.add(Integer.toString(i));
        }
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testConstructs() {
        Collection<String> collection1 = new ArrayList<>();
        Collections.addAll(collection1, "1", "2", "3");
        System.out.println(collection1);

        divider();

        Collection<String> collection2 = Arrays.<String>asList("a", "b", "c");      //此ArrayList非彼ArrayList
        System.out.println(collection2);

        try {
            collection2.add("d");       //由于底层表示的是数组，因此无法改变其大小
            Assert.fail();
        } catch (UnsupportedOperationException ignore) {
        }
    }

    @Test
    public void testListIterator() {
        List<String> ls = new ArrayList<>();
        ls.addAll(Arrays.asList("a", "b", "c"));
        ListIterator<String> iterator = ls.listIterator(ls.size());

        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }

        divider();

        while (iterator.hasNext()) {
            String next = iterator.next();
            iterator.add(next + "1");     //ls得到了修改，但是本次迭代并不会受到影响
            System.out.println(next);
        }

        divider();

        iterator = ls.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
