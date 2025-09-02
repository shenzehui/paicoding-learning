package com.szh.whitelist.annoation;

import java.lang.annotation.*;

/**
 * Title:
 * Description:
 * Company: wondersgroup.com
 *
 * @author 沈泽辉
 * @version 1.0
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface DoWhiteList {

    String key() default "";

    String returnJson() default "";
}
