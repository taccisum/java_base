package cn.tac.test.base.i18n;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author tac
 * @since 16/11/2017
 */
public class SimpleI18NTest {
    @Test
    public void testSimply() {
        Locale en = new Locale("en", "US");
        Locale zh = new Locale("zh", "CN");
        ResourceBundle enBundle = ResourceBundle.getBundle("locale.msg", en);
        ResourceBundle zhBundle = ResourceBundle.getBundle("locale.msg", zh);
        System.out.println(enBundle.getString("hello"));
        System.out.println(zhBundle.getString("hello"));
    }
}
