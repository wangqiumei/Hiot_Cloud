package com.huatec.hiot_cloud.ui.updateemail;

import android.text.TextUtils;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 修改密码Presenter类
 */
class EmailUpdatePresenter extends BasePresenter<EmailUpdateView> {

    @Inject
    DataManager dataManager;

    @Inject
    public EmailUpdatePresenter() {
    }

    /**
     * 修改邮箱
     *
     * @param email
     */
    public void EmailUpdate(String email) {
        subscribe(dataManager.updateEmail(email), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                super.onNext(resultBase);
                if (resultBase != null && resultBase.getData() != null) {
                    getView().showMessage("邮箱修改成功");
                    getView().emailUpdateSucc();
                } else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())) {
                    getView().showMessage(resultBase.getMsg());
                }
            }
        });
    }
}
