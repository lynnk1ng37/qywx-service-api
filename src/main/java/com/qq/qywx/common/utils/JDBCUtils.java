package com.qq.qywx.common.utils;

import com.qq.qywx.api.BaseTest;
import com.qq.qywx.common.GlobalVar;
import com.qq.qywx.common.entity.Cookie;
import com.qq.qywx.common.entity.Header;
import com.qq.qywx.common.entity.TestCase;
import com.qq.qywx.common.entity.Variable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by lynnking.
 */

public class JDBCUtils {

    public static Connection conn = null;

    static {
        try {
            conn = DBUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Connection对象初始化失败！"));
        }
    }

    /**
     * 加载数据库测试用例数据
     * tableName 测试用例数据表名
     * @return
     */
    public static List<TestCase> loadTestData(String tableName) {
        List<TestCase> testCases = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            // 定义sql
            String sql = "SELECT * FROM "+tableName;
            // 获取执行sql的对象
            st = conn.prepareStatement(sql);
            // 执行sql
            rs = st.executeQuery(sql);
            // 循环写入测试数据到集合中
            while (rs.next()) {
                if (1 == rs.getInt("execute")) {
                    TestCase testCase = new TestCase(rs.getInt("id"),
                            ifNullAsEmpty(rs.getString("case_id")),
                            ifNullAsEmpty(rs.getString("api_id")),
                            ifNullAsEmpty(rs.getString("query_param")),
                            ifNullAsEmpty(rs.getString("query_body")),
                            rs.getInt("expected_code"),
                            ifNullAsEmpty(rs.getString("expected_message")),
                            ifNullAsEmpty(rs.getString("expected_targeted")),
                            ifNullAsEmpty(rs.getString("description")),
                            rs.getInt("flag"),
                            rs.getInt("execute"),
                            rs.getInt("process")
                    );
                    BaseTest.testCases.add(testCase);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("加载数据库测试用例数据表【%s】失败！",tableName));
        } finally {
            DBUtils.close(rs, st);
        }
        return testCases;
    }

    /**
     * 为NULL则转成空字符串，避免NPE
     *
     * @param o 可能为空的对象
     * @return 为空：""，不为空：字符串本身
     */
    public static String ifNullAsEmpty(Object o) {
        if (Objects.isNull(o)) {
            return "";
        }
        return o.toString();
    }

    /**
     * 保存header到数据库中
     *
     * @param headers 存放header的map集合
     * @return 插入的header行数
     */
    public static Integer storeHeaders(List<Header> headers) {
        int effectRows = 0;
        if (Objects.isNull(headers)) {
            return effectRows;
        }
        PreparedStatement st = null;
        ResultSet rs = null;
        for (Header header : headers) {
            try {
                // 定义sql
                String sql = "INSERT INTO headers values(NULL, ? , ?, ?)";
                // 获取执行sql的对象
                st = conn.prepareStatement(sql);
                st.setString(1, header.getKey());
                st.setString(2, header.getValue());
                st.setString(3, header.getStore_time());
                // 执行sql
                int i = st.executeUpdate();
                effectRows += i;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtils.close(rs, st);
            }
        }
        return effectRows;
    }

    /**
     * 保存cookie到数据库中
     *
     * @param cookies 存放header的map集合
     * @return 插入的cookie行数
     */
    public static int storeCookies(List<Cookie> cookies) {
        int effectRows = 0;
        if (Objects.isNull(cookies)) {
            return effectRows;
        }
        PreparedStatement st = null;
        ResultSet rs = null;
        for (Cookie cookie : cookies) {
            try {
                // 定义sql
                String sql = "INSERT INTO cookies values(NULL, ?, ? , ?, ?)";
                // 获取执行sql的对象
                st = conn.prepareStatement(sql);
                st.setString(1, cookie.getApi_id());
                st.setString(2, cookie.getKey());
                st.setString(3, cookie.getValue());
                st.setString(4, cookie.getStore_time());
                // 执行sql
                int i = st.executeUpdate();
                effectRows += i;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtils.close(rs, st);
            }
        }
        return effectRows;
    }

    /**
     * 从数据库中查询header
     *
     * @return header集合
     */
    public static List<Header> getHeaders() {
        List<Header> headers = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            // 定义sql
            String sql = "SELECT * FROM headers ORDER BY hid DESC LIMIT 0,2;";
            // 获取执行sql的对象
            st = conn.prepareStatement(sql);
            // 执行sql
            rs = st.executeQuery(sql);
            // 循环写入测试数据到集合中
            while (rs.next()) {
                Header header = new Header(rs.getInt("hid"),
                        rs.getString("key"),
                        rs.getString("value"),
                        rs.getString("store_time"));
                headers.add(header);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, st);
        }
        return headers;
    }

    /**
     * 获取业务流程测试数据
     * @param testCases
     * @return
     */
    public static Map<String, Object> getProcessDatas(List<TestCase> testCases) {
        Map<String, Object> processDatas = new HashMap<>();
        // 遍历整个测试用例数据List
        for (TestCase tc : testCases) {
            // 如果process=1，则将该条数据存储到processDatas中
            if (1 == tc.getProcess()) {
                // 存储方式key-value：接口名&query_param-value，接口名&query_body-value
                // 获取接口编号，截取接口名，拼接key，获取value，存入map
                String case_id = tc.getCase_id();
                String case_name = case_id.split("_")[0];
                String case_query_param_key = case_name + GlobalVar.JOIN_QUERY_PARAM;
                String case_query_param_value = tc.getQuery_param();
                String case_query_body_key = case_name + GlobalVar.JOIN_QUERY_BODY;
                String case_query_body_value = tc.getQuery_body();
                processDatas.put(case_query_param_key, case_query_param_value);
                processDatas.put(case_query_body_key, case_query_body_value);
            }
        }
        return processDatas;
    }

    /**
     * 加载可变参数表的数据
     * @return
     */
    public static List<Variable> loadVariable() {
        List<Variable> variables = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            // 定义sql
            String sql = "SELECT * FROM variable";
            // 获取执行sql的对象
            st = conn.prepareStatement(sql);
            // 执行sql
            rs = st.executeQuery(sql);
            // 循环写入测试数据到集合中
            while (rs.next()) {
                Variable variable = new Variable(rs.getInt("vid"),
                        rs.getString("variable_name"),
                        ifNullAsEmpty(rs.getString("variable_value")),
                        rs.getString("reflect_class"),
                        rs.getString("reflect_method"),
                        rs.getString("remark"));
                variables.add(variable);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.close(rs, st);
        }
        return variables;
    }

}
