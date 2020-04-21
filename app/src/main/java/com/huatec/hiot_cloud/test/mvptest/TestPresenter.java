package com.huatec.hiot_cloud.test.mvptest;

import android.widget.TextView;

import com.huatec.hiot_cloud.base.BasePresenter;
import com.huatec.hiot_cloud.test.mvptest.model.User;

public class TestPresenter extends BasePresenter<TestView> {


    public TestPresenter(){

    }


    public  void  login(User user){
        if("zsh".equals(user.getUserName()) && "520".equals(user.getPassword())) {
            getView().showMessage("登录成功");
        }else{
            getView().showMessage("登录失败");
        }
    }


}
