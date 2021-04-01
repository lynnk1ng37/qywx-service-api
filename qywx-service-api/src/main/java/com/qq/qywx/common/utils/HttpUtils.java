package com.qq.qywx.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.entity.TestStep;
import com.qq.qywx.common.enums.HttpType;

import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Hong Li on 2021/1/1.
 */

public class HttpUtils {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RestAssuredConfig restAssuredConfig;
    private Response response;
    private String baseURL;

    /**
     * 构造方法
     */
    HttpUtils(String url) {
        baseURL = url;

        // 根据需要进行设置
//        restAssuredConfig = config().jsonConfig(jsonConfig().numberReturnType(DOUBLE));
    }

    /**
     * 获取本次请求的URL
     *
     * @param path
     */
    private String getRequestInfo(String path) {
        return RestAssured.baseURI + path;
    }

    /**
     * 获取本次请求的URL、body
     *
     * @param path
     * @param body
     */
    private String getRequestInfo(String path, String body) {

        return getRequestInfo(path) + "?" + body;
    }

    /**
     * 获取本次请求的URL、参数
     *
     * @param path
     * @param params
     */
    private String getRequestInfo(String path, Map<String, Object> params) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : params.keySet()) {
            stringBuilder.append(key).append("=").append(params.get(key)).append("&");
        }

        if (stringBuilder.length() >= 1 && stringBuilder.toString().endsWith("&")) {
            stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
        }

        return getRequestInfo(path) + "?" + stringBuilder;
    }

    /**
     * 获取本次请求的URL、参数、body
     *
     * @param path
     * @param params
     */
    private String getRequestInfo(String path, Map<String, Object> params, String body) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : params.keySet()) {
            stringBuilder.append(key).append("=").append(params.get(key)).append("&");
        }

        if (stringBuilder.length() >= 1 && stringBuilder.toString().endsWith("&")) {
            stringBuilder = new StringBuilder(stringBuilder.substring(0, stringBuilder.length() - 1));
        }

        return getRequestInfo(path) + "?" + stringBuilder + "&body=" + body;
    }

    /**
     * 获取本次请求的响应信息
     *
     * @param response
     */
    private String getResponseInfo(Response response) {

        // TODO - 此处容易抛异常
        if (response.contentType().contains("json")) {
            return "[" + response.statusCode() + "]" + response.jsonPath().get();
        } else {
            return "[" + response.statusCode() + "]" + response.htmlPath().get();
        }
    }

    /**
     * 装载此次请求配置
     *
     * @param path
     */
    private RequestSpecification getRequestSpecification(String path) {
        // 判断是否指定了Content-Type
        if (null == GlobalVar.CONTENT_TYPE) {
            return given().headers(GlobalVar.HEADERS).cookies(GlobalVar.COOKIES).config(restAssuredConfig).basePath(path);
        } else {
            return given().headers(GlobalVar.HEADERS).cookies(GlobalVar.COOKIES).config(restAssuredConfig).basePath(path).contentType(GlobalVar.CONTENT_TYPE);
        }
    }

    /**
     * 有参数无body
     *
     * @param httpType
     * @param path
     * @param params
     */
    private Response request(HttpType httpType, String path, Map<String, Object> params) {
        logger.info("[" + httpType.getValue() + "]" + getRequestInfo(path, params));

        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).log().all().params(params).get();
                break;
            case POST:
                response = getRequestSpecification(path).log().all().params(params).post();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }

    /**
     * 无参数有body
     *
     * @param httpType
     * @param path
     * @param body
     */
    private Response request(HttpType httpType, String path, String body) {
        logger.info("[" + httpType.getValue() + "]" + getRequestInfo(path, body));

        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).log().all().body(body).get();
                break;
            case POST:
                response = getRequestSpecification(path).log().all().body(body).post();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }

    /**
     * 有参数和有body的请求
     *
     * @param httpType
     * @param path
     * @param params
     */
    private Response request(HttpType httpType, String path, Map<String, Object> params, String body) {
        logger.info("[" + httpType.getValue() + "]" + getRequestInfo(path, params, body));

        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).log().all().queryParams(params).body(body).get();
                break;
            case POST:
                response = getRequestSpecification(path).log().all().queryParams(params).body(body).post();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }

    /**
     * 无参数无body
     *
     * @param httpType
     * @param path
     */
    private Response request(HttpType httpType, String path) {
        logger.info("[" + httpType.getValue() + "]" + getRequestInfo(path));

        switch (httpType) {
            case GET:
                response = getRequestSpecification(path).log().all().get();
                break;
            case POST:
                response = getRequestSpecification(path).log().all().post();
                break;
            default:
                throw new RuntimeException(String.format("暂不支持%s请求类型", httpType));
        }

        return response;
    }

    /**
     * 请求
     *
     * @param testStep
     */
    public Response request(TestStep testStep) {
        RestAssured.baseURI = baseURL;
        if (!testStep.getParams().isEmpty() && testStep.getBody().isEmpty()) { // 有参数无body
            // 将josn格式请求参数转成map集合方便调用queryParams()
            Map mapParams = (Map) JSONObject.parse(testStep.getParams());
            response = request(testStep.getType(), testStep.getPath(), mapParams);
        } else if (testStep.getParams().isEmpty() && !testStep.getBody().isEmpty()) { // 无参数有body
            response = request(testStep.getType(), testStep.getPath(), testStep.getBody());
        } else if (!testStep.getParams().isEmpty() && !testStep.getBody().isEmpty()) { // 有参数有body
            Map mapParams = (Map) JSONObject.parse(testStep.getParams());
            // 将josn格式请求参数转成map集合方便调用queryParams()
            response = request(testStep.getType(), testStep.getPath(), mapParams, testStep.getBody());
        } else { // 无参数无body
            response = request(testStep.getType(), testStep.getPath());
        }

        logger.info(getResponseInfo(response));
        return response;
    }
}

