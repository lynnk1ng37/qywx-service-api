package com.qq.qywx.api.get_access_token;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.annotation.GET;
import com.qq.qywx.common.annotation.Param;
import com.qq.qywx.common.annotation.SERVER;

/**
 * Created by lynnking.
 */

@SERVER(GlobalVar.QYAPI_SERVER)
public interface GetAccessToken {

    @GET(path = "/cgi-bin/gettoken", description = "获取access_token")
    Response getAccessToken(@Param String param);
}
