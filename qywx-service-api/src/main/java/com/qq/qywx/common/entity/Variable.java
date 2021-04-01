package com.qq.qywx.common.entity;

/**
 * Created by 72088532 on 2021/2/9.
 */

/*
CREATE TABLE `variable` (
  `vid` int NOT NULL COMMENT '索引id',
  `variable_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '可变参数名',
  `variable_value` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '可变参数值',
  `reflect_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生成可变参数对应的反射类',
  `reflect_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生成可变参数对应的反射方法',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`vid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */

public class Variable {
    private Integer vid;
    private String variable_name;
    private String variable_value;
    private String reflect_class;
    private String reflect_method;
    private String remark;

    public Variable() {

    }
    public Variable(Integer vid, String variable_name, String variable_value, String reflect_class, String reflect_method, String remark) {
        this.vid = vid;
        this.variable_name = variable_name;
        this.variable_value = variable_value;
        this.reflect_class = reflect_class;
        this.reflect_method = reflect_method;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "vid=" + vid +
                ", variable_name='" + variable_name + '\'' +
                ", variable_value='" + variable_value + '\'' +
                ", reflect_class='" + reflect_class + '\'' +
                ", reflect_method='" + reflect_method + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getVariable_name() {
        return variable_name;
    }

    public void setVariable_name(String variable_name) {
        this.variable_name = variable_name;
    }

    public String getVariable_value() {
        return variable_value;
    }

    public void setVariable_value(String variable_value) {
        this.variable_value = variable_value;
    }

    public String getReflect_class() {
        return reflect_class;
    }

    public void setReflect_class(String reflect_class) {
        this.reflect_class = reflect_class;
    }

    public String getReflect_method() {
        return reflect_method;
    }

    public void setReflect_method(String reflect_method) {
        this.reflect_method = reflect_method;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
