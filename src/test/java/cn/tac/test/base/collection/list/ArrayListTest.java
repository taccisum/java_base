package cn.tac.test.base.collection.list;

import cn.tac.test.base.BaseTest;
import cn.tac.test.base.annotation.Checked;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : tac
 * @since : 05/08/2017
 */

@Checked(src = true)
public class ArrayListTest extends BaseTest {

    @Test
    public void testSimply() {
        ArrayList<String> ls = new ArrayList<>(5);
        fill(ls, 10);

        Iterator<String> iterator = ls.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private void fill(List<String> ls, int num) {
        for (int i : range(num)){
            ls.add(Integer.toString(i));
        }
    }
}