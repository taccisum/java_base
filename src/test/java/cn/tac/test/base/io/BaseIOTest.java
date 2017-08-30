package cn.tac.test.base.io;

import cn.tac.test.base.BaseTest;

import java.io.*;

/**
 * @author : tac
 * @since : 23/08/2017
 */
public class BaseIOTest extends BaseTest {
    protected void print(File file) throws IOException {
        System.out.println(read(file));
    }
    protected void print(File file, String charsetName) throws IOException {
        System.out.println(read(file, charsetName));
    }

    protected void print(InputStream is) throws IOException {
        System.out.println(read(is));
    }

    protected void print(InputStream is, String charsetName) throws IOException {
        System.out.println(read(is, charsetName));
    }

    protected String read(File file) throws IOException {
        return read(file, "utf-8");
    }
    protected String read(File file, String charsetName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
        return doRead(br);
    }

    protected String read(InputStream is) throws IOException {
       return read(is, "utf-8");
    }

    protected String read(InputStream is, String charsetName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, charsetName));
        return doRead(br);
    }

    private String doRead(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (br.ready()) {
            sb.append((char) br.read());
        }
        return sb.toString();
    }
}
