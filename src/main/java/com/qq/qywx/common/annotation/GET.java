package com.qq.qywx.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GET注解：发送get请求
 * path：接口路径
 * description：接口描述
 * Created by lynnking.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GET {
    String path();
    String description();
}
