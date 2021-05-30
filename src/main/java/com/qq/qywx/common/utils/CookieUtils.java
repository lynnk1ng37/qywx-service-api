package com.qq.qywx.common.utils;

import com.qq.qywx.common.entity.Cookie;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lynnking.
 */

public class CookieUtils {

    /**
     * 将header存入到数据库中
     * @param map 保存header的map集合
     * @return 插入数据库中的header条数
     */
    public static Integer storeCookies(Map<String, Object> map, String api_id) {
        List<Cookie> cookies = new ArrayList<>();
        int effectRows = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_time = format.format(new Date());
        if (Objects.isNull(map)) {
            return effectRows;
        }
        Set<Map.Entry<String,Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            cookies.add(new Cookie(api_id, key, value, format_time));
        }
        effectRows = JDBCUtils.storeCookies(cookies);
        return effectRows;
    }
}
