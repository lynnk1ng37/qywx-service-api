package com.qq.qywx.common.entity;

/**
 * 测试用例数据实体类
 * Created by lynnking.
 */

/*
CREATE TABLE `testdata` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引id',
  `case_id` varchar(100) NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint DEFAULT '1' COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT '0' COMMENT '是否是参与业务流程',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8
 */

public class TestCase {
    private Integer id;
    private String case_id;
    private String api_id;
    private String query_param;
    private String query_body;
    private Integer expected_code;
    private String expected_message;
    private String expected_targeted;
    private String description;
    private Integer flag;
    private Integer execute;
    private Integer process;

    public TestCase() {
    }

    public TestCase(Integer id, String case_id, String api_id, String query_param, String query_body, Integer expected_code, String expected_message, String expected_targeted, String description, Integer flag, Integer execute, Integer process) {
        this.id = id;
        this.case_id = case_id;
        this.api_id = api_id;
        this.query_param = query_param;
        this.query_body = query_body;
        this.expected_code = expected_code;
        this.expected_message = expected_message;
        this.expected_targeted = expected_targeted;
        this.description = description;
        this.flag = flag;
        this.execute = execute;
        this.process = process;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "id=" + id +
                ", case_id='" + case_id + '\'' +
                ", api_id='" + api_id + '\'' +
                ", query_param='" + query_param + '\'' +
                ", query_body='" + query_body + '\'' +
                ", expected_code=" + expected_code +
                ", expected_message='" + expected_message + '\'' +
                ", expected_targeted='" + expected_targeted + '\'' +
                ", description='" + description + '\'' +
                ", flag=" + flag +
                ", execute=" + execute +
                ", process=" + process +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCase_id() {
        return case_id;
    }

    public void setCase_id(String case_id) {
        this.case_id = case_id;
    }

    public String getApi_id() {
        return api_id;
    }

    public void setApi_id(String api_id) {
        this.api_id = api_id;
    }

    public String getQuery_param() {
        return query_param;
    }

    public void setQuery_param(String query_param) {
        this.query_param = query_param;
    }

    public String getQuery_body() {
        return query_body;
    }

    public void setQuery_body(String query_body) {
        this.query_body = query_body;
    }

    public Integer getExpected_code() {
        return expected_code;
    }

    public void setExpected_code(Integer expected_code) {
        this.expected_code = expected_code;
    }

    public String getExpected_message() {
        return expected_message;
    }

    public void setExpected_message(String expected_message) {
        this.expected_message = expected_message;
    }


    public String getExpected_targeted() {
        return expected_targeted;
    }

    public void setExpected_targeted(String expected_targeted) {
        this.expected_targeted = expected_targeted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getExecute() {
        return execute;
    }

    public void setExecute(Integer execute) {
        this.execute = execute;
    }

    public Integer getProcess() {
        return process;
    }

    public void setProcess(Integer process) {
        this.process = process;
    }
}
