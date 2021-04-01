package com.qq.qywx.api.addressbook.user;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.annotation.GET;
import com.qq.qywx.common.annotation.Param;
import com.qq.qywx.common.annotation.SERVER;

/**
 * Created by 72088532 on 2021/2/20.
 */

@SERVER(GlobalVar.QYAPI_SERVER)
public interface GetUser {

    @GET(path = "/cgi-bin/user/get", description = "创建成员")
    Response getUser(@Param String param);
}
