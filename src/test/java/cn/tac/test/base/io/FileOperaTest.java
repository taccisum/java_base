package cn.tac.test.base.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author : tac
 * @since : 23/08/2017
 */

public class FileOperaTest extends BaseIOTest {

    public static final String TEST_CREATE_DIR = "/tmp/";
    public static final String TEST_DIR_PATH = "/Users/tac/Documents/devolops/java/src/personal/java_base/src/test/resources/assets/";
    public static final String TEST_FILE_NAME = "hello.txt";
    public static final String TEST_FILE_PATH = TEST_DIR_PATH + TEST_FILE_NAME;

    @Test
    public void testSimply() throws IOException {
        File file = new File(TEST_FILE_PATH);
        print(file);
    }

    @Test
    public void testNewWithUri() throws URISyntaxException, IOException {
        URI uri = new URI("file:///" + TEST_FILE_PATH);
        File file = new File(uri);
        print(file);
    }

    @Test
    public void testNewWithParent() throws IOException {
        File dir = new File(TEST_DIR_PATH);
        File file = new File(dir, TEST_FILE_NAME);
        print(file);
    }

    @Test
    public void testCreateFile() throws IOException {
        String filePath = TEST_CREATE_DIR + randomUUIDStr();
        File file = new File(filePath);
        Assert.assertFalse(file.exists());
        Assert.assertTrue(file.createNewFile());
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.delete());
        Assert.assertFalse(file.exists());
    }
}
