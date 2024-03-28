package com.lkeren.view;

import com.lkeren.bean.User;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/27/10:35
 * @Description :
 */
public class AdminView implements View{
    private static final Scanner input = new Scanner(System.in);
    public static int IndexAccountCard;
    // 管理员视图
    public User loginView() {
        return null;
    }

    @Override
    public User signView() {
        return null;
    }

    public User drawMoneyView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎来到取款界面\t\t*******");
        System.out.println("*******\t\t请输入取款账户：\t\t*******");
        int accountCard = Integer.parseInt(input.nextLine());
        System.out.println("*******\t\t请输入您想要取款的金额：\t\t*******");
        double drawMoney = Double.parseDouble(input.nextLine());
        System.out.println("************************************");
        User user = new User();
        user.setAccountCard(accountCard);
        user.setBalance(drawMoney);
        return user;
    }

    public double saveMoneyMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎来到存款界面\t\t*******");
        System.out.println("*******\t\t请输入您想要存款的金额：\t\t*******");
        double saveMoney = Double.parseDouble(input.nextLine());
        System.out.println("************************************");
        return saveMoney;
    }

    public User transferMoneyMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入您想要转账的账户：\t\t*******");
        int newAccountCard = Integer.parseInt(input.nextLine());
        System.out.println("*******\t\t请输入您想要转账的金额：\t\t*******");
        double transferMoney = Double.parseDouble(input.nextLine());
        System.out.println("************************************");
        User user = new User();
        user.setAccountCard(newAccountCard);
        user.setBalance(transferMoney);
        return user;
    }

    public int balanceChangeMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎来到余额改变界面\t\t*******");
        System.out.println("************************************");
        return 0;
    }

    public int userLogRecordMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎来到日志界面\t\t*******");
        System.out.println("************************************");
        return 0;
    }

    // 管理员视图
    public static int AdminMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t欢迎管理员回家\t\t*******");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t0.退出：\t\t*******");
        System.out.println("*******\t\t1.查看所有用户\t\t*******");
        System.out.println("*******\t\t2.查看单个用户\t\t*******");
        System.out.println("*******\t\t3.查看银行整体金额变化\t\t*******");
        System.out.println("*******\t\t4.修改用户密码\t\t*******");

        int item = Integer.parseInt(input.nextLine());
        if( item<0 || item>4){
            System.out.println("输入错误，请重新输入");
            return AdminMenuView();
        }
        System.out.println("************************************");
        return item;
    }

    public int deleteMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入想要删除的用户账号：\t\t*******");
        int uaccountCard = Integer.parseInt(input.nextLine());
        System.out.println("************************************");
        return uaccountCard;
    }

    // 管理员才有资格查询
    public static int selectMenuView(){
        System.out.println("************************************");
        System.out.println("*******\t\t请根据提示操作\t\t*******");
        System.out.println("*******\t\t请输入想要查询的用户账号：\t\t*******");
        int uaccountCard = Integer.parseInt(input.nextLine());
        System.out.println("************************************");
        return uaccountCard;
    }
}
