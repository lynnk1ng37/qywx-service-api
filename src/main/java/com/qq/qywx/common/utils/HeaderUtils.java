package com.qq.qywx.common.utils;

import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.entity.Header;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lynnking.
 */

public class HeaderUtils {

    /**
     * 将header存入到数据库中
     * @param map 保存header的map集合
     * @return 插入数据库中的header条数
     */
    public static Integer storeHeaders(Map<String, Object> map) {
        List<Header> headers = new ArrayList<>();
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
            headers.add(new Header(key, value, format_time));
        }
        effectRows = JDBCUtils.storeHeaders(headers);
        return effectRows;
    }

    /**
     * 从数据库中查询并存储header到集合中
     */
    public static void setHeaders() {
        List<Header> headers = JDBCUtils.getHeaders();
        if (Objects.isNull(headers)) {
            return;
        }
        for (Header header : headers) {
            String key = header.getKey();
            String value = header.getValue();
            GlobalVar.HEADERS.put(key, value);
        }
    }
}
