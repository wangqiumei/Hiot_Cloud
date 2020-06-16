package com.huatec.hiot_cloud.ui.updatepassword;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 修改密码presenter类
 */
class PasswordUpdatePresenter extends BasePresenter<PasswordUpdateView> {


    @Inject
    DataManager dataManager;

    @Inject
    public PasswordUpdatePresenter() {
    }

    /**
     * 修改密码
     *
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     */
    public void PasswordUpdate(String oldpassword, String newpassword, String confirmpassword) {
        subscribe(dataManager.updatePassword(oldpassword, newpassword, confirmpassword), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                super.onNext(resultBase);
                if (resultBase != null && resultBase.getData() != null) {
                    getView().showMessage("密码修改成功");
                    getView().PasswordUpdateSucc();
                } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                    getView().showMessage(resultBase.getMsg());
                }

            }
        });
    }
}
