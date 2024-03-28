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
 * @Description : 普通用户只允许对自己的账户进行增删改查操作，对自己名下的账户进行存、取款及转账。
 */
public class UserDao_imp implements UserDao {
    private static final String SQL_USER_EXIST = "SELECT 1 FROM user WHERE accountCard = ? LIMIT 1";

    private static final String SQL_USER_SIGN = "INSERT INTO user VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_USER_LOGIN = "select type from user where accountCard=? and `password`=?";

    private static final String SQL_USER_DELETE = "DELETE FROM `user` WHERE accountCard = ?";

    private static final String SQL_USER_UPDATE_PASSWORD = "UPDATE `user` SET `password` = ? WHERE accountCard=?";
    private static final String SQL_USER_UPDATE_ACCOUNT_NAME = "UPDATE `user` SET `accountName` = ? WHERE accountCard=?";
    private static final String SQL_USER_UPDATE_MOBILE = "UPDATE `user` SET `mobile` = ? WHERE accountCard=?";

    private static final String SQL_USER_DRAW_MONEY = "UPDATE `user` SET `balance` = ? WHERE accountCard=?";
    private static final String SQL_USER_TRANSFER_MONEY = "UPDATE `user` SET `balance` = ? WHERE accountCard=?";

    private static final String SQL_USER_SELECT = "SELECT * FROM `user` WHERE accountCard = ?";

    private static final String SQL_USER_LOGS_SELECT = "select * from user_logs WHERE accountCard=?";


    public int sign(User user) {
        Connection conn = JDBCUtils.getConnection();
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PreparedStatement preparedStatementADD = null;
        int resultADD;
        try {
            preparedStatementADD = conn.prepareStatement(SQL_USER_SIGN);
            preparedStatementADD.setString(1, dateFormat.format(date));
            preparedStatementADD.setInt(2, user.getAccountCard());
            preparedStatementADD.setString(3, user.getAccountName());
            preparedStatementADD.setString(4, user.getMobile());
            preparedStatementADD.setString(5, user.getPassword());
            preparedStatementADD.setDouble(6, 0.0);  // 新增用户余额默认为0.0
            preparedStatementADD.setInt(7, 1);  // 新增用户权限默认为  1
            preparedStatementADD.setString(8, user.getIDcard());
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

    @Override
    public int login(User user) {
        /**
         * 0-管理员登录成功
         * 1-用户登录成功
         * 2-转到注册界面
         * -1 -操作都失败
         */
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
                    while (resultLogin.next()) {
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
                System.out.println("数据库中不存在该用户账号");
                return 2;
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
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_DELETE);
            preparedStatement.setInt(1, accountCard);
            int deleteFlag = preparedStatement.executeUpdate();
            if (deleteFlag > 0) {
                System.out.println("删除用户成功");
                return 1;
            } else {
                System.out.println("删除用户失败");
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int updateAccountName(User user) {
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_UPDATE_ACCOUNT_NAME);
            preparedStatement.setString(1, user.getAccountName());
            preparedStatement.setInt(2, user.getAccountCard());
            int changePasswordFlag = preparedStatement.executeUpdate();
            if (changePasswordFlag > 0) {
                System.out.println("修改账户姓名成功");
                return 1;
            } else {
                System.out.println("修改账户姓名失败");
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateMobile(User user){
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_UPDATE_MOBILE);
            preparedStatement.setString(1, user.getMobile());
            preparedStatement.setInt(2, user.getAccountCard());
            int changePasswordFlag = preparedStatement.executeUpdate();
            if (changePasswordFlag > 0) {
                System.out.println("修改电话号码成功");
                return 1;
            } else {
                System.out.println("修改电话号码失败");
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int changePassword(User user) {
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_UPDATE_PASSWORD);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setInt(2, user.getAccountCard());
            int changePasswordFlag = preparedStatement.executeUpdate();
            if (changePasswordFlag > 0) {
                System.out.println("修改密码成功");
                return 1;
            } else {
                System.out.println("修改密码失败");
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User selectUser(User user) {
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_SELECT);
            preparedStatement.setInt(1,user.getAccountCard());
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                String signTime = String.valueOf(result.getDate("signTime"));
                int accountCard = result.getInt("accountCard");
                String accountName = result.getString("accountName");
                String mobile = result.getString("mobile");
                String idCard = result.getString("idCard");
                String password = result.getString("password");
                int balance = result.getInt("balance");
                int type = result.getInt("type");
                return new User(signTime, accountCard, accountName, mobile, idCard, password, balance, type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public int drawMoney(int accountCard, double money) {
        /**
         * 1  --  取款成功
         * -1 --  取款失败
         * 2  --  余额不够
         */
        Connection conn = JDBCUtils.getConnection();
        try {
            // 先查询钱是否足够取出，然后再执行金额的修改
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_SELECT);
            preparedStatement.setDouble(1, accountCard);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                double balance = result.getDouble("balance");
                if(balance > 0  && balance >= money){
                    PreparedStatement preparedStatement1 = conn.prepareStatement(SQL_USER_DRAW_MONEY);
                    preparedStatement1.setDouble(1,(balance-money));
                    preparedStatement1.setInt(2,accountCard);
                    int drawFlag = preparedStatement1.executeUpdate();
                    if(drawFlag > 0){
                        System.out.println("取款成功");
                        return 1;
                    }else {
                        System.out.println("取款失败");
                        return -1;
                    }
                }else {
                    System.out.println("您的余额不够");
                    return 2;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int saveMoney(int accountCard, double money) {
        /**
         * 1  --  存款成功
         * -1 --  存款失败
         */
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_SELECT);
            preparedStatement.setDouble(1, accountCard);
            ResultSet result = preparedStatement.executeQuery();
            while(result.next()){
                double balance = result.getDouble("balance");
                if(balance >= 0){
                    PreparedStatement preparedStatement1 = conn.prepareStatement(SQL_USER_DRAW_MONEY);
                    preparedStatement1.setDouble(1,(balance+money));
                    preparedStatement1.setInt(2,accountCard);
                    int drawFlag = preparedStatement1.executeUpdate();
                    if(drawFlag > 0){
                        System.out.println("存款成功");
                        return 1;
                    }else {
                        System.out.println("存款失败");
                        return -1;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public int transderMoney(int oldAccountCard,int newAccountCard, double money) {
        /**
         * 1  --  转账成功
         * -1 --  转账失败
         * 2  --  余额不够转账
         * 3  -- 转账账户不存在
         */
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement preparedStatement2 = null;
        try {
            preparedStatement2 = conn.prepareStatement(SQL_USER_SELECT);
            preparedStatement2.setDouble(1, newAccountCard);
            ResultSet result2 = preparedStatement2.executeQuery();
            while(result2.next()){
                double newBalance = result2.getDouble("balance");
                System.out.println("转账账户存在");
                try {
                    // 先查询钱是否足够取出，然后再执行金额的修改
                    PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_SELECT);
                    preparedStatement.setDouble(1, oldAccountCard);
                    ResultSet result = preparedStatement.executeQuery();
                    while(result.next()){
                        double OldBalance = result.getDouble("balance");
                        if(OldBalance > 0 && newBalance >= 0  && OldBalance >= money){
                            //  老账户减
                            PreparedStatement preparedStatement1 = conn.prepareStatement(SQL_USER_DRAW_MONEY);
                            preparedStatement1.setDouble(1,(OldBalance-money));
                            preparedStatement1.setInt(2,oldAccountCard);

                            // 新账户加
                            PreparedStatement preparedStatement3 = conn.prepareStatement(SQL_USER_DRAW_MONEY);
                            preparedStatement3.setDouble(1,(newBalance+money));
                            preparedStatement3.setInt(2,newAccountCard);

                            int drawFlag = preparedStatement1.executeUpdate();
                            int saveFlag = preparedStatement3.executeUpdate();
                            if(drawFlag > 0 && saveFlag > 0){
                                System.out.println("转账成功");
                                return 1;
                            }else {
                                System.out.println("转账失败");
                                return -1;
                            }
                        }else {
                            System.out.println("您的余额不够转账");
                            return 2;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("转账账户不存在");
        return 3;
    }

    // 调用的时候，给登录者的id就行了吧；不需要user一个，admin一个了。
    @Override
    public int userLogRecord(int accountCard) {
        Connection conn = JDBCUtils.getConnection();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_USER_LOGS_SELECT);
            preparedStatement.setInt(1,accountCard);
            ResultSet result = preparedStatement.executeQuery();
            System.out.println("operateTime\t\t\taccountCard\t\taccountName\t\toper");
            while(result.next()){
                String operateTime = result.getString("operateTime");
                int accountCard1 = result.getInt("accountCard");
                String accountName = result.getString("accountName");
                String oper = result.getString("oper");
                System.out.println(operateTime + "\t\t" + accountCard1 + "\t\t\t\t" + accountName + "\t\t\t" + oper);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


    // 用户查看自己余额变化
    @Override
    public int balanceChange(int accountCard) {
        System.out.println("operateTime\t\t\taccountCard\t\toper\t\tbalance\t\tmoney");
        return 0;
    }

}
