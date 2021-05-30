package com.qq.qywx.common.utils;

import com.alibaba.fastjson.JSON;
import com.jayway.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;

/**
 * Created by lynnking.
 */

public class AssertUtils {

    /**
     * 断言
     * @param response 请求响应
     * @param expected_code 期望码
     * @param expected_message 期望信息
     * @param expected_targeted 针对性断言
     * @return 断言结果
     */
    public static boolean getAssertResult(Response response, Integer expected_code, String expected_message, String expected_targeted) {
        boolean result = true;
        // 断言响应码和响应信息
        response.then().body("errcode", equalTo(expected_code));
        response.then().body("errmsg", equalTo(expected_message));
        // 针对性断言
        if (!StringUtils.isEmpty(expected_targeted)) {
            Map<String, Object> targetedMap = (Map<String, Object>) JSON.parse(expected_targeted);
            Set<Map.Entry<String, Object>> entries = targetedMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object expected_value = entry.getValue();
                Object actual_value = response.jsonPath().getJsonObject(key);
                // 如果返回的是多个结果组成的ArrayList，则拼接成字符串通过包含来判断
                if (actual_value instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) actual_value;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < arrayList.size(); i++) {
                        sb.append(arrayList.get(i)+"");
                    }
                    result = sb.toString().contains(expected_value.toString());
                } else if (actual_value instanceof String) { // 如果是字符串直接比较是否相等
                    System.out.println(actual_value);
                    result = Objects.equals(expected_value, actual_value);
                }
            }
        }
        return result;
    }
}
