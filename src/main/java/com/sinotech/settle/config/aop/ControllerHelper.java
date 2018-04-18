package com.sinotech.settle.config.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称：sinoTechOMS
 * 创建人：liuhe
 * 创建时间：2017/10/23
 * 修改人：
 * 修改时间：
 * 修改备注：
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
