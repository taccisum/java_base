package cn.tac.test.base.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tac
 * @since : 06/08/2017
 */
public class OOMTest {

    /**
     * VM options: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
     * 最小堆和最大堆均为20m，避免自动扩展
     * 在出现OOM异常时dump出当前的内存堆转储快照,将会在工程目录下生成一个.hprof文件
     */
    public static void main(String[] args) {
        List<HeapOOM> ls = new ArrayList<>();
        while (true){
            ls.add(new HeapOOM());
        }
    }

    private static class HeapOOM {
    }
}
