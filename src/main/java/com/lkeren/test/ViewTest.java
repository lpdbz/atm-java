package com.lkeren.test;

import com.lkeren.bean.User;
import com.lkeren.view.View;
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
    public void indexMenuTest(){
        User user = View.loginView();
        System.out.println(user);
    }
}
