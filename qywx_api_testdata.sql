/*
 Navicat Premium Data Transfer

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : qywx_api_testdata

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 19/05/2021 11:05:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api_externalcontact_list
-- ----------------------------
DROP TABLE IF EXISTS `api_externalcontact_list`;
CREATE TABLE `api_externalcontact_list`  (
  `id` int NOT NULL DEFAULT 0 COMMENT '索引id',
  `case_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint NULL DEFAULT 1 COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT 0 COMMENT '是否是参与业务流程'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_externalcontact_list
-- ----------------------------
INSERT INTO `api_externalcontact_list` VALUES (1, 'TestListCustomer_001', 'customer_list_1', '{\"userid\":\"HongLi\"}', NULL, 0, 'ok', '{\"external_userid\":\"wm1h_uCwAAk70DzCKMiBNb8zJjz5er0A\"}', '获取已存在客户信息', 1, 1, 1);

-- ----------------------------
-- Table structure for api_gettoken
-- ----------------------------
DROP TABLE IF EXISTS `api_gettoken`;
CREATE TABLE `api_gettoken`  (
  `id` int NOT NULL DEFAULT 0 COMMENT '索引id',
  `case_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint NULL DEFAULT 1 COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT 0 COMMENT '是否是参与业务流程'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_gettoken
-- ----------------------------
INSERT INTO `api_gettoken` VALUES (1, 'TestAddressbookGetAccessToken_001', 'addressbook_gettoken_1', '{\"corpid\":\"ww97d35aaa92f1422a\",\"corpsecret\":\"MA8a92IiNWa4L9UnmNNwL8ruQqM7ZaJOo8cXu9zUMl8\"}', NULL, 0, 'ok', NULL, '正确的corpid和corpsecret获取access_token', 1, 1, 1);
INSERT INTO `api_gettoken` VALUES (2, 'TestExternalcontactGetAccessToken_001', 'externalcontact_gettoken_1', '{\"corpid\":\"ww97d35aaa92f1422a\",\"corpsecret\":\"NJRrZzNF5_QTqs5ZBGmnMEnmKEh5ByX1vROdcqN7MtM\"}', NULL, 0, 'ok', NULL, '正确的corpid和corpsecret获取access_token', 1, 1, 1);

-- ----------------------------
-- Table structure for api_user_batchdelete
-- ----------------------------
DROP TABLE IF EXISTS `api_user_batchdelete`;
CREATE TABLE `api_user_batchdelete`  (
  `id` int NOT NULL DEFAULT 0 COMMENT '索引id',
  `case_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint NULL DEFAULT 1 COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT 0 COMMENT '是否是参与业务流程'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_user_batchdelete
-- ----------------------------
INSERT INTO `api_user_batchdelete` VALUES (1, 'TestBatchDeleteUser_001', 'user_batchdelete_1', '', '{\"useridlist\":\"${useridlist}\"}', 0, 'deleted', '', '批量删除已存在成员', 1, 1, 1);

-- ----------------------------
-- Table structure for api_user_create
-- ----------------------------
DROP TABLE IF EXISTS `api_user_create`;
CREATE TABLE `api_user_create`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引id',
  `case_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint NULL DEFAULT 1 COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT 0 COMMENT '是否是参与业务流程',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_user_create
-- ----------------------------
INSERT INTO `api_user_create` VALUES (1, 'TestCreateUser_001', 'user_create_1', '', '{\r\n    \"userid\": \"${userid}\",\r\n    \"name\": \"${userid}\",\r\n    \"alias\": \"${userid}\",\r\n    \"mobile\": \"${mobile}\",\r\n    \"department\": [1, 2],\r\n    \"order\":[10,40],\r\n    \"position\": \"产品经理\"\r\n}', 0, 'created', NULL, '只传必填字段创建成员', 1, 1, 1);

-- ----------------------------
-- Table structure for api_user_delete
-- ----------------------------
DROP TABLE IF EXISTS `api_user_delete`;
CREATE TABLE `api_user_delete`  (
  `id` int NOT NULL DEFAULT 0 COMMENT '索引id',
  `case_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint NULL DEFAULT 1 COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT 0 COMMENT '是否是参与业务流程'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_user_delete
-- ----------------------------
INSERT INTO `api_user_delete` VALUES (1, 'TestDeleteUser_001', 'user_delete_1', '{\"userid\":\"${userid}\"}', NULL, 0, 'deleted', '', '删除已存在成员', 1, 1, 1);

-- ----------------------------
-- Table structure for api_user_get
-- ----------------------------
DROP TABLE IF EXISTS `api_user_get`;
CREATE TABLE `api_user_get`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '索引id',
  `case_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint NULL DEFAULT 1 COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT 0 COMMENT '是否是参与业务流程',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_user_get
-- ----------------------------
INSERT INTO `api_user_get` VALUES (1, 'TestGetUser_001', 'user_get_1', '{\"userid\":\"${userid}\"}', NULL, 0, 'ok', '{\"userid\":\"${userid}\"}', '获取已存在成员信息', 1, 1, 1);

-- ----------------------------
-- Table structure for api_user_update
-- ----------------------------
DROP TABLE IF EXISTS `api_user_update`;
CREATE TABLE `api_user_update`  (
  `id` int NOT NULL DEFAULT 0 COMMENT '索引id',
  `case_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例编号',
  `api_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口编号',
  `query_param` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `query_body` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求体',
  `expected_code` int NOT NULL COMMENT '期望状态码',
  `expected_message` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '期望结果',
  `expected_targeted` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '针对性断言',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用例描述',
  `flag` tinyint NOT NULL COMMENT '用例标记：1-正向用例，0-反向用例，其他-对应场景',
  `execute` tinyint NULL DEFAULT 1 COMMENT '是否执行用例',
  `process` tinyint NOT NULL DEFAULT 0 COMMENT '是否是参与业务流程'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of api_user_update
-- ----------------------------
INSERT INTO `api_user_update` VALUES (1, 'TestUpdateUser_001', 'user_update_1', '', '{\r\n    \"userid\": \"${userid}\",\r\n    \"name\": \"测试\",\r\n    \"department\": [1, 2],\r\n    \"order\":[10,40],\r\n    \"position\": \"自动化测试工程师\"\r\n}', 0, 'updated', NULL, '更新成员name和position', 1, 1, 1);

-- ----------------------------
-- Table structure for cookies
-- ----------------------------
DROP TABLE IF EXISTS `cookies`;
CREATE TABLE `cookies`  (
  `cid` int NOT NULL AUTO_INCREMENT,
  `api_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cookies
-- ----------------------------

-- ----------------------------
-- Table structure for headers
-- ----------------------------
DROP TABLE IF EXISTS `headers`;
CREATE TABLE `headers`  (
  `hid` int NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`hid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of headers
-- ----------------------------

-- ----------------------------
-- Table structure for variable
-- ----------------------------
DROP TABLE IF EXISTS `variable`;
CREATE TABLE `variable`  (
  `vid` int NOT NULL COMMENT '索引id',
  `variable_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '可变参数名',
  `variable_value` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可变参数值',
  `reflect_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生成可变参数对应的反射类',
  `reflect_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生成可变参数对应的反射方法',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`vid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of variable
-- ----------------------------
INSERT INTO `variable` VALUES (1, '${userid}', NULL, 'com.qq.qywx.common.variable.UserIdGenerator', 'generateUserId', '生成唯一的userid');
INSERT INTO `variable` VALUES (2, '${mobile}', NULL, 'com.qq.qywx.common.variable.MobileGenerator', 'generateMobile', '生成随机11位手机号');

SET FOREIGN_KEY_CHECKS = 1;
