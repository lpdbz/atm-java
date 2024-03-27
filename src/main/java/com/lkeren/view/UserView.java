package com.lkeren.view;


import com.lkeren.bean.User;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/25/18:06
 * @Description :
 */
public class UserView implements View {
    private static final Scanner input = new Scanner(System.in);

    public int IndexAccountCard;

    // 用户登录界面，要是用户存在则登录，要是不存在就注册，之后用户自行修改个人信息即可
    public User loginView() {
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎来到登录界面\t\t*******");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入账号：\t\t*******");
        IndexAccountCard = Integer.parseInt(input.nextLine());
        System.out.println("*******\t\t请输入密码：\t\t*******");
        String upass = input.nextLine();
        System.out.println("************************************");
        return new User(IndexAccountCard, upass);
    }

    // 注册新用户
    @Override
    public User signView() {
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎来到注册界面\t\t*******");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入身份证号：\t\t*******");
        String uIDcard = input.nextLine();
        int uaccountCard = uIDcard.trim().hashCode();  // 账户ID是通过身份证哈希而来
        System.out.println("*******\t\t请输入账户名称：\t\t*******");
        String uaccountName = input.nextLine();
        System.out.println("*******\t\t请输入密码：\t\t*******");
        String upassword = input.nextLine();
        System.out.println("*******\t\t请输入电话号码：\t\t*******");
        String umobile = input.nextLine();
        System.out.println("************************************");
        return new User(uaccountCard, uaccountName, umobile, uIDcard,upassword);
    }

    // 用户索引视图
    public static int UserMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎您回家\t\t*******");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t0.退出：\t\t*******");
        System.out.println("*******\t\t1.存款\t\t*******");
        System.out.println("*******\t\t2.取款\t\t*******");
        System.out.println("*******\t\t3.转账\t\t*******");
        System.out.println("*******\t\t4.查询余额\t\t*******");
        System.out.println("*******\t\t5.修改密码\t\t*******");
        System.out.println("*******\t\t6.修改用户信息\t\t*******");
        System.out.println("*******\t\t7.查看余额变动记录\t\t*******");

        int item = Integer.parseInt(input.nextLine());
        if( item<0 || item>7){
            System.out.println("输入错误，请重新输入");
            return UserMenuView();
        }
        System.out.println("************************************");
        return item;
    }

    // 用户注销自己的账号
    public int deleteMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎来到注销用户界面\t\t*******");
        System.out.println("************************************");
        return IndexAccountCard;
    }

    public int indexUpdateView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎您来到更新界面\t\t*******");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t0.退出：\t\t*******");
        System.out.println("*******\t\t1.密码\t\t*******");
        System.out.println("*******\t\t2.修改账户名称\t\t*******");
        System.out.println("*******\t\t3.手机号码\t\t*******");

        int item = Integer.parseInt(input.nextLine());
        if (item < 0 || item > 3){
            System.out.println("您的输入有误，请重新输入：");
            return indexUpdateView();
        }
        System.out.println("************************************");
        return item;
    }

    public User updatePasswordMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入新密码：\t\t*******");
        String upass = input.nextLine();
        System.out.println("************************************");
        User user = new User();
        user.setPassword(upass);
        user.setAccountCard(IndexAccountCard);
        return user;
    }

    public User updateAccountNameMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入新账号名称：\t\t*******");
        String uaccountName = input.nextLine();
        System.out.println("************************************");
        User user = new User();
        user.setAccountName(uaccountName);
        user.setAccountCard(IndexAccountCard);
        return user;
    }

    public User updateMobileMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入新手机号码：\t\t*******");
        String umobile = input.nextLine();
        System.out.println("************************************");
        User user = new User();
        user.setMobile(umobile);
        user.setAccountCard(IndexAccountCard);
        return user;
    }

    public void printUser(User user){
        System.out.println("用户的开户时间:" + user.getSignTime());
        System.out.println("用户的账户卡号:" + user.getAccountCard());
        System.out.println("用户的账号名称:" + user.getAccountName());
        System.out.println("用户的手机号码:" + user.getMobile());
        System.out.println("用户的身份证:" + user.getIDcard());
        System.out.println("用户的密码:" + user.getPassword());
        System.out.println("用户的余额:" + user.getBalance());
        if( user.getType() == 0){
            System.out.println("用户的权限: 管理员" );
        }else {
            System.out.println("用户的权限: 用户" );
        }
    }
}
