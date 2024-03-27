package com.lkeren.view;

import com.lkeren.bean.User;

/**
 * Created with IntelliJ IDEA
 *
 * @Author : lkeren
 * @Date : 2024/03/27/11:59
 * @Description :
 */
public interface View {
    // 管理员界面   用户界面
    User loginView();

    // 管理员不要注册
    User signView();

}
