package com.lkeren.test;

import com.lkeren.jdbc.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/25/17:59
 * @Description :
 */
public class JDBCUtilsTest {
    @Test
    public void jdbcConnectionTest() throws Exception{
        Connection conn = JDBCUtils.getConnection();
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM `user`");
        while (result.next()){
            System.out.print(result.getString("signTime") + " ");
            System.out.print(result.getInt("accountCard") + " ");
            System.out.print(result.getString("accountName") + " ");
            System.out.print(result.getString("mobile") + " ");
            System.out.print(result.getString("IDCard") + " ");
            System.out.print(result.getString("password") + " ");
            System.out.print(result.getInt("balance") + " ");
            System.out.println(result.getInt("type"));
        }
    }
}
