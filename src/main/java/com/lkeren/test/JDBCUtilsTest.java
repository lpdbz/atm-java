package com.lkeren.test;

import com.lkeren.bean.User;
import com.lkeren.dao.UserDao_imp;
import com.lkeren.jdbc.JDBCUtils;
import com.lkeren.view.View;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
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

    @Test
    public void loginTest(){
        UserDao_imp userDaoImp = new UserDao_imp();
        User userLogin = View.loginView();
        int login = userDaoImp.login(userLogin);
        System.out.println(login);  // -1:登录注册失败  0:管理员登录成功  1:用户登录成功  2:用户注册成功
    }

    @Test
    public void deleteUserTest(){
        UserDao_imp userDaoImp = new UserDao_imp();
        int accountCard = View.deleteMenuView();
        int deleteFlag = userDaoImp.deleteUser(accountCard);
        System.out.println(deleteFlag);
    }
}
