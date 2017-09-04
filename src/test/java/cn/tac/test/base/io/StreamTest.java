package cn.tac.test.base.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

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

    @Test
    public void testGBK() throws IOException {
        InputStream is = Class.class.getResourceAsStream("/assets/gbk.txt");
        print(is, "gbk");
    }

    @Test
    public void testCharSet() {
        Charset charset = Charset.forName("utf8");
        if (!charset.canEncode()) {
            throw new UnsupportedOperationException();
        }
        ByteBuffer byteBuffer = charset.encode("hello charset encoder, ^ ^ 这只是个测试");
        System.out.println("buffer capacity: " + byteBuffer.capacity());
        System.out.println("buffer remaining: " + byteBuffer.remaining());
        while (byteBuffer.hasRemaining()) {
            System.out.print(byteBuffer.get() + ", ");
        }

        System.out.println();
        divider();

        ByteBuffer byteBuffer1 = charset.encode("hello charset decoder, ^ ^ 这个是测试1");
        CharBuffer charBuffer = charset.decode(byteBuffer1);
        System.out.println("buffer capacity: " + charBuffer.capacity());
        System.out.println("buffer remaining: " + charBuffer.remaining());
        while (charBuffer.hasRemaining()) {
            System.out.print(charBuffer.get());
        }
    }
}
