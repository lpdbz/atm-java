package com.lkeren.test;

import com.lkeren.bean.User;
import com.lkeren.dao.UserDao_imp;
import com.lkeren.jdbc.JDBCUtils;
import com.lkeren.view.UserView;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

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
    public void signTest(){
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = new UserView().signView();
        int sign = userDaoImp.sign(user);
        System.out.println(sign);
    }

    @Test
    public void loginTest(){
        UserDao_imp userDaoImp = new UserDao_imp();
        User userLogin = new UserView().loginView();
        int login = userDaoImp.login(userLogin);
        System.out.println(login);  // -1:登录注册失败  0:管理员登录成功  1:用户登录成功  2:用户注册成功
    }

    @Test
    public void deleteUserTest(){
        UserView userView = new UserView();  // 保证是同一个UserView类
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = userView.loginView();
        int login = userDaoImp.login(user);
        System.out.println(login);
        User user1 = userView.deleteMenuView();
        int deleteFlag = userDaoImp.deleteUser(user1.getAccountCard());
        System.out.println(deleteFlag);
    }

    @Test
    public void changeAccountNameTest(){
        UserView userView = new UserView();
        UserDao_imp userDaoImp = new UserDao_imp();
        User user1 = userView.loginView();
        int login = userDaoImp.login(user1);
        System.out.println(login);
        User user = userView.updateAccountNameMenuView();
        int changeAccountName = userDaoImp.updateAccountName(user);
        System.out.println(changeAccountName);
    }

    @Test
    public void changeMobileTest(){
        UserView userView = new UserView();
        UserDao_imp userDaoImp = new UserDao_imp();
        User user1 = userView.loginView();
        int login = userDaoImp.login(user1);
        System.out.println(login);
        User user =userView.updateMobileMenuView();
        int changeMobile = userDaoImp.updateMobile(user);
        System.out.println(changeMobile);
    }

    @Test
    public void changePasswordTest(){
        UserView userView = new UserView();
        UserDao_imp userDaoImp = new UserDao_imp();
        User user1 = userView.loginView();
        int login = userDaoImp.login(user1);
        System.out.println(login);
        User user = userView.updatePasswordMenuView();
        int changePassword = userDaoImp.changePassword(user);
        System.out.println(changePassword);
    }

    @Test
    public void selectUserTest(){
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = new UserView().loginView();
        User user1 = userDaoImp.selectUser(user);
        new UserView().printUser(user1);
    }

    @Test
    public void drawMoneyTest(){
        Scanner input = new Scanner(System.in);
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = new UserView().loginView();
        double money = new UserView().drawMoneyView();
        int drawFlag = userDaoImp.drawMoney(user.getAccountCard(), money);
        System.out.println(drawFlag);
    }

    @Test
    public void saveMoneyTest(){
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = new UserView().loginView();
        double money = new UserView().saveMoneyMenuView();
        int drawFlag = userDaoImp.saveMoney(user.getAccountCard(), money);
        System.out.println(drawFlag);
    }

    @Test
    public void transderMoneyTest(){
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = new UserView().loginView();
        User user1 = new UserView().transferMoneyMenuView();  // 转账账户
        int transferFlag = userDaoImp.transderMoney(user.getAccountCard(),user1.getAccountCard(),user1.getBalance());
        System.out.println(transferFlag);
    }

    @Test
    public void userLogRecordTest() {
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = new UserView().loginView();
        int log = new UserView().userLogRecordMenuView();
        int userLogRecord = userDaoImp.userLogRecord(user.getAccountCard());
        System.out.println(userLogRecord);
    }

    @Test
    public void balanceChangeTest() {
        UserDao_imp userDaoImp = new UserDao_imp();
        User user = new UserView().loginView();
        int log = new UserView().balanceChangeMenuView();
        int userLogRecord = userDaoImp.balanceChange(user.getAccountCard());
        System.out.println(userLogRecord);
    }
}
