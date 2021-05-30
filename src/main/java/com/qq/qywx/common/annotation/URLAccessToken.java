package com.qq.qywx.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * URLAccessToken：根据qywx服务端api特性，每个接口url上都要带access_token，直接自定义成接口，在每个接口定义上加一个此注解，ProxyUtils中解析
 * 但是还需要修改HttpUtils中的带param请求的参数装配问题，url自带?access_token=ACCESS_TOKEN的话，后面再有参数的话只需要添加&key=value
 * 这里提供一个思路，没有具体实现，有兴趣的可以试试
 * Created by lynnking.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface URLAccessToken {

}
