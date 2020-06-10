package com.huatec.hiot_cloud.ui.register;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 注册模块presenter类
 */
class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    DataManager dataManager;

    @Inject
    public RegisterPresenter() {
        this.dataManager = dataManager;
    }

    /**
     * 注册
     */
    public void register(String userName, final String password, final String email) {
        subscribe(dataManager.register(userName, password, email), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBase) {
                if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //判断如果注册成功，吐司注册成功
                    if (resultBase != null && resultBase.getData() != null) {
                        //弹出注册成功
                        getView().showMessage(Constants.REGISTER_SUCC);
                        //自动登录，跳转到主界面
                        getView().registerSucc(email, password);
                    }
                } else {
                    //如果注册失败，吐司服务端返回的错误信息
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                //吐司连接失败
                getView().showMessage(Constants.TOAST_MSG_NETWORK_FAIL);
            }
        });
    }

    /**
     * 登录
     *
     * @param email
     * @param password
     */
    public void login(String email, String password) {
        subscribe(dataManager.login(email, password), new RequestCallback<ResultBase<LoginResultDTO>>() {
            @Override
            public void onNext(ResultBase<LoginResultDTO> resultBase) {
                if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
                    //如果登陆身份正确，弹出登录成功，跳转到主界面
                    if (resultBase != null && resultBase.getData() != null) {
                        //弹出登录成功
//                        getView().showMessage("登录成功");
                        //跳转到主界面
                        getView().loginSucc();
                    }
                } else {
                    //如果登陆身份错误，弹出服务端返回的错误信息
                    if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                        getView().showMessage(resultBase.getMsg());
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                //吐司连接失败
                getView().showMessage(Constants.TOAST_MSG_NETWORK_FAIL);
            }
        });
    }
}
