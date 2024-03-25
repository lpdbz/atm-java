package com.lkeren.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/25/17:15
 * @Description :
 */

// jdbc的工具类
public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 静态语句块
    static {
        // 1
        InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");// 用流的形式读取jdbc的配置文件
        // 创建properties类型的对象  // 2
        Properties p = new Properties();
        try {
            // 加载流文件  // 3
            p.load(is);
            driver = p.getProperty("driver");
            url = p.getProperty("url");
            username = p.getProperty("username");
            password = p.getProperty("password");

            // 1
            Class.forName(driver);
            System.out.println("驱动加载成功");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获得连接对象的方法
    public static Connection getConnection() {
        try {
            System.out.println("数据库连接成功");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            throw new RuntimeException(e);
        }
    }

    // 释放资源的方法
    public static void close(Connection conn, Statement statement, ResultSet result){
        try {
            if(result != null) {
                result.close();
                result = null;
            }
            if(statement != null){
                statement.close();
                statement = null;
            }
            if(conn != null){
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
