package com.qq.qywx.common.variable;

import java.util.Random;

/**
 * Created by lynnking.
 */

public class MobileGenerator {
    //手机号前三位
    public static String[] telPre = {
            "134", "135", "136", "137", "138", "139", "147", "150", "151", "152",
            "157", "158", "159", "178", "182", "183", "184", "187", "188", "198",
            "130", "131", "132", "145", "155", "156", "185", "186", "176", "175",
            "133", "149", "153", "180", "181", "189", "177"};

    //随机获取数组中一个前三位
    public static String getPreNumber() {
        return telPre[(new Random()).nextInt(telPre.length)];
    }

    //随机生成一个指定位数的数
    public static String getAfterNumber(int len) {
        String source = "0123456789";
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int j = 0; j < len; j++) {
            rs.append(source.charAt(r.nextInt(10)));
        }
        return rs.toString();
    }

    //随机生成一个手机号后八位
    public static String generateMobile() {
        String preNumber = getPreNumber();
        String afterNumber = getAfterNumber(8);
        return preNumber + afterNumber;
    }
}
