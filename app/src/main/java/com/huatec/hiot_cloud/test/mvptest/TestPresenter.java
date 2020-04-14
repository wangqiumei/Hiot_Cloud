package com.huatec.hiot_cloud.test.mvptest;

import android.widget.TextView;

import com.huatec.hiot_cloud.test.mvptest.model.User;

public class TestPresenter {
    private TestView view;

    public TestPresenter(TestView view){
        this.view = view;
    }

    public  void  login(User user){
        if("zsh".equals(user.getUserName()) && "520".equals(user.getPassword())) {
            view.showMessage("登录成功");
        }else{
            view.showMessage("登录失败");
        }
    }
}
