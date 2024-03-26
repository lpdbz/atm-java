package com.lkeren.dao;

import com.lkeren.bean.User;
import com.lkeren.jdbc.JDBCUtils;
import com.lkeren.view.View;

import javax.xml.crypto.Data;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/25/18:07
 * @Description :
 */
public class UserDao_imp implements UserDao{
    private static final String SQL_USER_EXIST = "SELECT 1 FROM user WHERE accountCard = ? LIMIT 1";
    private static final String SQL_USER_LOGIN = "select type from user where accountCard=? and `password`=?";

    private static final String SQL_USER_ADD = "INSERT INTO user(signTime,accountCard,`password`) VALUES(?,?,?)";
    @Override
    public int login(User user) {
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatementExist = conn.prepareStatement(SQL_USER_EXIST);
            preparedStatementExist.setInt(1, user.getAccountCard());
            ResultSet resultExist = preparedStatementExist.executeQuery();
            if (resultExist.equals(1)){
                PreparedStatement preparedStatementLogin = conn.prepareStatement(SQL_USER_LOGIN);
                preparedStatementLogin.setInt(1, user.getAccountCard());
                preparedStatementLogin.setString(2, user.getPassword());
                ResultSet resultLogin = preparedStatementLogin.executeQuery();
                if(resultLogin.equals(0)){
                    System.out.println("恭喜管理员登录成功！");
                }else{
                    System.out.println("恭喜用户登录成功");
                }
            }else{
                //通过util下的Date包实现获取时间
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                PreparedStatement preparedStatementLogin = conn.prepareStatement(SQL_USER_ADD);

                preparedStatementLogin.setString(1,dateFormat.format(date));
                preparedStatementLogin.setInt(2, user.getAccountCard());
                preparedStatementLogin.setString(3, user.getPassword());
                ResultSet resultLogin = preparedStatementLogin.executeQuery();
                if (resultLogin != null){
                    System.out.println("注册成功！");
                }else {
                    System.out.println("登录失败！");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }

    @Override
    public int changePassword(User user) {
        return 0;
    }

    @Override
    public int drawMoney(User user) {
        return 0;
    }

    @Override
    public int saveMoney(User user) {
        return 0;
    }

    @Override
    public int transderMoney(User user) {
        return 0;
    }

    @Override
    public int balanceChange(User user) {
        return 0;
    }

    @Override
    public int userLogRecord(User user) {
        return 0;
    }
}
