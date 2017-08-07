package cn.tac.test.base.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tac
 * @since : 06/08/2017
 */
public class RuntimeConstantPollOOMTest {

    /**
     * VM options: -XX:PermSize=10M -XX:MaxPermSize=10M
     * 设置永久代容量为10M，不可扩展
     * 由于在jdk8中已移除永久代的概念，以上参数配置只在jdk7及之前的HotSpot中有效
     * </br>
     * VM options: -XX:PermSize=10M -XX:MaxPermSize=10M
     */
    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        int i = 0;
        while (true){
            i++;
            ls.add(Integer.toString(i).intern());
        }
    }
}
