package cn.tac.test.base.map;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author tac
 * @since 04/08/2017
 */
public class HashMapTest {
    /**
     * Algorithm of {@link HashMap#hash(Object)}
     *
     * @since 2022-12-08
     */
    @Test
    public void hashAlgorithm() {
        Object obj = new Object();
        System.out.printf("Object hashcode: %d\n", obj.hashCode());
        System.out.printf("Object hashcode >>> 16: %d\n", obj.hashCode() >>> 16);
        int hash = obj.hashCode() ^ (obj.hashCode() >>> 16);    // higher 16 bits xor lower 16 bits
        System.out.printf("Real hash algorithm of HashMap(hashcode xor hashcode >>> 16): %d\n", hash);      // Fast and well distributed
        System.out.printf("Calculate index(hash %% table_size): %d\n", hash % 32);      // Slow
        System.out.printf("Calculate index(hash & [table_size-1]): %d\n", hash & (32 - 1));     // Fast but only available for 2^n table size
    }
}
