package com.qq.qywx.common.utils;

import com.qq.qywx.api.BaseTest;
import com.qq.qywx.common.entity.Variable;

import java.lang.reflect.Method;

/**
 * Created by lynnking.
 */

public class VariableUtils {

    /**
     * 处理可变参数
     * @param variableStr
     * @return
     */
    public static String handleVariable(String variableStr) {
        for (Variable variable : BaseTest.variables) {
            String variable_name = variable.getVariable_name();
            while (variableStr.contains(variable_name)) {
                // 获取反射类名和反射类方法
                String reflect_class = variable.getReflect_class();
                String reflect_method = variable.getReflect_method();
                try {
                    Class clazz = Class.forName(reflect_class);
                    Object obj = clazz.newInstance();
                    Method method = clazz.getMethod(reflect_method);
                    String result = (String) method.invoke(obj);
                    variableStr = variableStr.replace(variable_name, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return variableStr;
    }
}
