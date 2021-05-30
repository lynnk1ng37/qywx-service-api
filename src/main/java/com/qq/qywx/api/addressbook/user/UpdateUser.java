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
public interface UpdateUser {

    @POST(path = "/cgi-bin/user/update", description = "更新成员")
    Response updateUser(@Param String param,
                        @Body String body);
}
