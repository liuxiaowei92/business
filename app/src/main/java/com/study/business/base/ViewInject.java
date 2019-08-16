package com.study.business.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by Administrator on 2019/8/15
 */

@Retention(RetentionPolicy.RUNTIME) //运行时注解
@Target(ElementType.TYPE)//类接口 注解
public @interface ViewInject {
    int mainLayoutId() default -1;

}
