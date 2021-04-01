package com.qq.qywx.common.utils;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;

import java.util.Objects;

/**
 * Created by 72088532 on 2021/2/26.
 */

public class AccessTokenUtils {

    /**
     * 添加并保存access_token
     * @param response
     */
    public static void putAndStoreAccessToken(Response response, String api_id) {
        String access_token = response.jsonPath().getString("access_token");
        if (!Objects.isNull(access_token)) {
            GlobalVar.COOKIES.put("access_token", access_token);
            // 将最新的access_token存入数据库
            CookieUtils.storeCookies(GlobalVar.COOKIES, api_id);
        }
    }
}
