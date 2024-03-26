package com.lkeren.dao;

import com.lkeren.bean.User;
import com.lkeren.jdbc.JDBCUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
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

    private static final String SQL_USER_ADD = "INSERT INTO user(signTime,accountCard,`password`,`balance`,`type`) VALUES(?,?,?,?,?)";
    @Override
    public int login(User user) {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatementExist = null;
        ResultSet resultExist = null;
        try {
            preparedStatementExist = conn.prepareStatement(SQL_USER_EXIST);
            preparedStatementExist.setInt(1, user.getAccountCard());
            resultExist = preparedStatementExist.executeQuery(); // 不存在为什么返回不是空

            if (resultExist.next()) {
                PreparedStatement preparedStatementLogin = null;
                ResultSet resultLogin = null;
                try {
                    preparedStatementLogin = conn.prepareStatement(SQL_USER_LOGIN);

                    preparedStatementLogin.setInt(1, user.getAccountCard());
                    preparedStatementLogin.setString(2, user.getPassword());
                    resultLogin = preparedStatementLogin.executeQuery();
                    while(resultLogin.next()){
                        if (resultLogin.getInt("type") == 0) {
                            System.out.println("恭喜管理员登录成功！");
                            return 0;
                        } else {
                            System.out.println("恭喜用户登录成功");
                            return 1;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBCUtils.close(conn, preparedStatementLogin, resultLogin);
                }
            } else {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                PreparedStatement preparedStatementADD = null;
                int resultADD;
                try {
                    preparedStatementADD = conn.prepareStatement(SQL_USER_ADD);
                    preparedStatementADD.setString(1, dateFormat.format(date));
                    preparedStatementADD.setInt(2, user.getAccountCard());
                    preparedStatementADD.setString(3, user.getPassword());
                    preparedStatementADD.setDouble(4, 0.0);
                    preparedStatementADD.setInt(5, 1);
                    resultADD = preparedStatementADD.executeUpdate();
                    if (resultADD != 0) {
                        System.out.println("注册成功！");
                        return 2;
                    } else {
                        System.out.println("注册失败！");
                        return -1;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBCUtils.close(conn, preparedStatementADD, null);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(conn, preparedStatementExist, resultExist);
        }
        return -1;
    }

    @Override
    public int deleteUser(int accountCard) {
        return 0;
    }

    @Override
    public int uodateUser(User user) {
        return 0;
    }

    @Override
    public int changePassword(User user) {
        return 0;
    }

    @Override
    public int selectUser(User user) {
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
