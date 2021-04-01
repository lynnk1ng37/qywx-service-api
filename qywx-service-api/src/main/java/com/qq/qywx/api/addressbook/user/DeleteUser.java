package com.qq.qywx.api.addressbook.user;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.annotation.GET;
import com.qq.qywx.common.annotation.Param;
import com.qq.qywx.common.annotation.SERVER;

/**
 * Created by 72088532 on 2021/2/25.
 */

@SERVER(GlobalVar.QYAPI_SERVER)
public interface DeleteUser {

    @GET(path = "/cgi-bin/user/delete", description = "删除成员")
    Response deleteUser(@Param String param);
}