package com.lkeren.dao;

import com.lkeren.bean.User;
import com.lkeren.jdbc.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/27/10:21
 * @Description :管理员可以增删改查任何账户信息，对于存、取款以及转账，在用户本人授权的行为下，也可以进行操作
 */
public class AdminDao_imp implements UserDao{
    private static final String SQL_USER_DELETE = "DELETE FROM `user` WHERE accountCard = ?";

    @Override
    public int login(User user) {
        return 0;
    }

    @Override
    public int deleteUser(int accountCard) {
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_DELETE);
            preparedStatement.setString(1, String.valueOf(accountCard));
            int result = preparedStatement.executeUpdate();
            if(result > 0){
                System.out.println("删除成功");
                return 1;
            }else {
                System.out.println("不存在该用户，删除失败");
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int changePassword(User user) {
        return 0;
    }

    @Override
    public User selectUser(User user) {
        return null;
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
