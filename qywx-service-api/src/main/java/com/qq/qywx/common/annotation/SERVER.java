package com.qq.qywx.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SERVER注解：接口主机，拼接上接口path构成完整接口地址
 * Created by Hong Li on 2021/1/1.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SERVER {
    String value();
}
