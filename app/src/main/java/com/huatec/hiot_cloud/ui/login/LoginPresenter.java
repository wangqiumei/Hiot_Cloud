package com.huatec.hiot_cloud.ui.login;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 登录模块presenter类
 */

class LoginPresenter extends BasePresenter<LoginView> {
    @Inject
    DataManager dataManager;

    @Inject
    public LoginPresenter() {

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
                if (resultBase.getStatus() == Constants.MSG_STATUS_SECCESS) {
                    //如果登陆身份正确
                    if (resultBase != null && resultBase.getData() != null) {
                        //弹出登录成功
                        getView().showMessage(Constants.LOGIN_SUCC);
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
                getView().showMessage(Constants.TOAST_MSG_NETWORK_FAIL);
            }
        });
    }
}


