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
    // 注册用户
    int sign(User user);

    // 验证登录的方法
    int login( User user );

    //  修改密码
    int changePassword(User user);

    // 取款业务
    int drawMoney(User user);

    // 存款业务
    int saveMoney(User user);

    // 转账业务
    int transderMoney(User user);

    // 余额变动记录
    int balanceChange(User user);

    // 用户日志logs记录
    int userLogRecord(User user);
}
