package com.qq.qywx.api.addressbook.user;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.annotation.Body;
import com.qq.qywx.common.annotation.POST;
import com.qq.qywx.common.annotation.Param;
import com.qq.qywx.common.annotation.SERVER;

/**
 * Created by lynnking.
 */

@SERVER(GlobalVar.QYAPI_SERVER)
public interface CreateUser {

    @POST(path = "/cgi-bin/user/create", description = "εε»Ίζε")
    Response createUser(@Param String param,
                        @Body String body);
}
