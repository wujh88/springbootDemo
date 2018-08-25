package com.sinotech.settle.config.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerHelper {

    //不能为空的字段
    String[] required() default {};

    //必须是数字
    String[] numeric() default {};

    //方法的描述
    String description();
}
