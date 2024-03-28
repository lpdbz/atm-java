package com.lkeren.dao;

import com.lkeren.bean.User;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/25/18:07
 * @Description :
 */
public interface UserDao {

    // 登录的方法
    int login( User user );

    int deleteUser(int accountCard);

    //  修改密码
    int changePassword(User user);

    User selectUser(User user);

    // 取款业务
    int drawMoney(int accountCard, double money);

    // 存款业务
    int saveMoney(int accountCard, double money);

    // 转账业务
    int transderMoney(int oldAccountCard, int newAccountCard, double money);

    // 余额变动记录
    int balanceChange(int accountCard);

    // 用户日志logs记录
    int userLogRecord(int accountCard);
}
