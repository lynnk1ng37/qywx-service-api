package com.qq.qywx.common.utils;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * Created by lynnking.
 */

public class DBUtils {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    static {
        //读取文件，获取值
        try {
            //1.创建Properties集合类
            Properties pro = new Properties();
            //获取src路径下的文件--->ClassLoader类加载器
            ClassLoader classLoader = DBUtils.class.getClassLoader();
            URL resource = classLoader.getResource("jdbc.properties");;
            String path = resource.getPath();
            //2.加载文件
            pro.load(new FileReader(path));
            //3获取数据
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //4.注册驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("jdbc配置文件没有找到！"));
        }
    }
    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 释放资源
     * @param rs
     * @param st
     */
    public static void close(ResultSet rs, Statement st){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     * @param rs
     * @param st
     * @param conn
     */
    public static void close(ResultSet rs, Statement st,Connection conn){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(st != null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
