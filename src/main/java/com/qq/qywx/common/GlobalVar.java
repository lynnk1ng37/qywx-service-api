package com.qq.qywx.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lynnking.
 */

public class GlobalVar {

    // 主机地址
    public static final String QYAPI_SERVER = "QYAPI_SERVER";
    public static String QYAPI_SERVER_URL = "https://qyapi.weixin.qq.com";

    // access_token
    public static String global_access_token;
    // 请求数据内容类型
    public static String CONTENT_TYPE;

    // 存放Cookies和Headers集合
    public static Map<String, Object> COOKIES = new HashMap<>();
    public static Map<String, Object> HEADERS = new HashMap<>();

    // 失败重试的次数
    public final static Integer RETRY_COUNTS = 0;

    // 拼接业务流程测试数据集合的请求参数和请求键名
    public static final String JOIN_QUERY_PARAM = "&query_param";
    public static final String JOIN_QUERY_BODY = "&query_body";

    public static List<String> useridlist = new ArrayList<>();

}
