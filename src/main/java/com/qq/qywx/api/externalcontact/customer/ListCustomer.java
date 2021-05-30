package com.qq.qywx.api.externalcontact.customer;

import com.jayway.restassured.response.Response;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.annotation.GET;
import com.qq.qywx.common.annotation.Param;
import com.qq.qywx.common.annotation.SERVER;

/**
 * Created by lynnking.
 */

@SERVER(GlobalVar.QYAPI_SERVER)
public interface ListCustomer {

    @GET(path = "/cgi-bin/externalcontact/list", description = "获取客户列表")
    Response listCustomer(@Param String param);
}
