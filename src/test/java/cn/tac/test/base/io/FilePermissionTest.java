package cn.tac.test.base.io;

import org.junit.Test;

import java.io.FilePermission;
import java.security.PermissionCollection;


/**
 * @author : tac
 * @since : 17/09/2017
 */

public class FilePermissionTest {

    @Test
    public void testSimply() {
        try {
            String path = "/Users/tac/Documents/studyspace/src/java/java_base/src/test/resources/assets/hello.txt";
            FilePermission fp1 = new FilePermission("/Users/tac/Documents/studyspace/src/java/java_base/src/test/resources/assets/-", "read");
            PermissionCollection pc = fp1.newPermissionCollection();
            pc.add(fp1);
            FilePermission fp2 = new FilePermission(path, "write");
            pc.add(fp2);
            if (pc.implies(new FilePermission(path, "read,write"))) {
                System.out.println("Permission for " + path + " is read and write.");
            } else {
                System.out.println("No read, write permission for " + path);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
