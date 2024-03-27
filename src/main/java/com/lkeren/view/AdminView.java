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
    private static Scanner input = new Scanner(System.in);
    public static int IndexAccountCard;
    // 管理员视图
    public User loginView() {
        return null;
    }

    @Override
    public User signView() {
        return null;
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
