package com.qq.qywx.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.annotation.*;
import com.qq.qywx.common.entity.TestStep;
import com.qq.qywx.common.enums.HttpType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lynnking.
 */

public class ProxyUtils {
    private static Logger logger = LoggerFactory.getLogger(ProxyUtils.class);

    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> clazz) {

        // 获取接口上的host
        Annotation annotation = clazz.getAnnotation(SERVER.class);
        if (annotation == null) {
            throw new RuntimeException(String.format("接口类%s未配置@SERVER注解",
                    clazz.getName()));
        }

        String host;
        // 判断是否是定义好的指定接口host
        switch (clazz.getAnnotation(SERVER.class).value()) {
            case GlobalVar.QYAPI_SERVER:
                host = GlobalVar.QYAPI_SERVER_URL;
                break;
            default:
                throw new RuntimeException(String.format("未查找到接口类%s配置的@HOST(%s)注解中的%s接口服务器地址",
                        clazz.getName(),
                        clazz.getAnnotation(SERVER.class).value(),
                        clazz.getAnnotation(SERVER.class).value()));
        }

        HttpUtils httpUtils = new HttpUtils(host);

        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        // 解析方法上的注解及对应的值
                        Annotation[] annotations = method.getAnnotations();
                        if (annotations.length == 0) {
                            throw new RuntimeException(String.format("%s方法未配置请求类型注解，如@POST、@GET等",
                                    method.getName()));
                        }

                        HttpType httpType;
                        String path;
                        String description;
                        String body = "";
                        String params = "";

                        // 当前只需要解析一个请求类型注解
                        if (annotations[0] instanceof POST) {
                            httpType = HttpType.POST;
                            path = ((POST) annotations[0]).path();
                            description = ((POST) annotations[0]).description();
                        } else if (annotations[0] instanceof GET) {
                            httpType = HttpType.GET;
                            path = ((GET) annotations[0]).path();
                            description = ((GET) annotations[0]).description();
                        } else {
                            throw new RuntimeException(String.format("暂不支持%s方法配置的请求类型注解%s",
                                    method.getName(),
                                    annotations[0].annotationType()));
                        }

                        // 解析方法里参数的注解
                        Annotation[][] parameters = method.getParameterAnnotations();
                        Integer length = parameters.length;
                        TestStep testStep = new TestStep();
                        if (length != 0) {
                            for (Integer i = 0; i < length; i++) {
                                Annotation[] annos = parameters[i];
                                if (annos.length == 0) {
                                    throw new RuntimeException(String.format("方法%s中缺少参数注解，如@Param",
                                            method.getName()));
                                }

                                // 解析注解
                                if (annos[0] instanceof Param) {
                                    params = (String) args[i];
                                } else if (annos[0] instanceof Body) {
                                    body = (String) args[i];
                                } else if (annos[0] instanceof PathVariable) {
                                    path = path.replaceFirst("\\{\\}", args[i].toString());
                                } else {
                                    throw new RuntimeException(String.format("暂不支持方法%s中配置的参数注解%s",
                                            method.getName(),
                                            annos[0].annotationType()));
                                }
                            }
                        }
                        // 设置测试执行所需数据
                        testStep.setType(httpType);
                        testStep.setPath(path);
                        testStep.setParams(params);
                        testStep.setBody(body);
                        logger.info("[" + path + "]" + description);
                        return httpUtils.request(testStep);
                    }
                }
        );
    }
}
