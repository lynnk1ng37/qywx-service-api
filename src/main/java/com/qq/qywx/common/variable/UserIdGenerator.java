package com.qq.qywx.common.variable;

import com.qq.qywx.common.GlobalVar;

import java.util.UUID;

/**
 * Created by lynnking.
 */

public class UserIdGenerator {

    /**
     * 生成唯一的userid
     *
     * @return
     */
    public static String generateUserId() {
        String userid = "test_" + UUID.randomUUID().toString().substring(24);
        // 将userid添加到userid集合中
        GlobalVar.useridlist.add(userid);
        return userid;
    }

}
