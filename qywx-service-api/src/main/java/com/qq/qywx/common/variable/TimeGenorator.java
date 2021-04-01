package com.qq.qywx.common.variable;

import java.text.SimpleDateFormat;

/**
 * Created by 72088532 on 2021/2/9.
 */

public class TimeGenorator {

    /**
     * 生成拟制时间
     * @return yyyy-MM-dd HH:mm
     */
    public static String artificialtimeGenorator() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String artificialtime = format.format(System.currentTimeMillis());
        return artificialtime;
    }
}
