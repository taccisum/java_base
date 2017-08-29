package cn.tac.test.base.io;

import org.junit.Test;

import java.io.*;

/**
 * @author : tac
 * @since : 23/08/2017
 */
public class StreamTest extends BaseIOTest {

    @Test
    public void testReadResource() throws IOException {
        InputStream is = Class.class.getResourceAsStream("/assets/hello.txt");
        print(is);
    }
}
