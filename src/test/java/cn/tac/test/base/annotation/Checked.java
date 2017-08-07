package cn.tac.test.base.annotation;

import java.lang.annotation.*;

/**
 * 标识该知识点已掌握
 *
 * @author : tac
 * @since : 05/08/2017
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Checked {
    boolean src();
}
