package com.qq.qywx.api.addressbook.user;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.annotation.Body;
import com.qq.qywx.common.annotation.POST;
import com.qq.qywx.common.annotation.Param;
import com.qq.qywx.common.annotation.SERVER;

/**
 * Created by 72088532 on 2021/2/20.
 */

@SERVER(GlobalVar.QYAPI_SERVER)
public interface CreateUser {

    @POST(path = "/cgi-bin/user/create", description = "创建成员")
    Response createUser(@Param String param,
                        @Body String body);
}
