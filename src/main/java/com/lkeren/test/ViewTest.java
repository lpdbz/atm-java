package com.lkeren.test;

import com.lkeren.bean.User;
import com.lkeren.view.AdminView;
import com.lkeren.view.UserView;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/25/18:05
 * @Description :
 */
public class ViewTest {
    @Test
    public void signMenuTest(){
        User user = new UserView().signView();
        System.out.println(user);
    }

    @Test
    public void loginMenuTest(){
        User user = new UserView().loginView();
        System.out.println(user);
    }
}
