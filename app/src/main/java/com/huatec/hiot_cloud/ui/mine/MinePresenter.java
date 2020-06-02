package com.huatec.hiot_cloud.ui.mine;

import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

/**
 * 用户中心present类
 */
public class MinePresenter extends BasePresenter<MineView> {

    @Inject
    DataManager dataManager;

    @Inject
    public MinePresenter() {
    }

    /**
     * 加载用户信息
     */
    public void loadUserInfo() {
        subscribe(dataManager.getUserInfo(), new RequestCallback<ResultBase<UserBean>>() {
            @Override
            public void onNext(ResultBase<UserBean> resultBase) {
                if (resultBase == null) {
                    getView().showMessage(Constants.ERROR_MESSAGE);
                    return;
                }
                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }
                if (resultBase.getData() == null) {
                    getView().showMessage(Constants.ERROR_MESSAGE);
                    return;
                }
                UserBean userBean = resultBase.getData();
                getView().refreshUserInfo(userBean);
            }
        });
    }

    /**
     * 上传用户头像
     *
     * @param filePath
     */
    public void uploadImage(String filePath) {
        subscribe(dataManager.uploadImage(filePath), new RequestCallback<ResultBase<String>>() {
            @Override
            public void onNext(ResultBase<String> resultBase) {
                if (resultBase == null) {
                    getView().showMessage(Constants.ERROR_MESSAGE);
                    return;
                }
                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }
                //获取相对地址
                String url = resultBase.getData();
                //刷新用户头像
                getView().refreshUserHead(url);
            }
        });
    }

    /**
     * 注销
     */
    public void logout() {
        subscribe(dataManager.logout(), new RequestCallback<ResultBase>() {
            @Override
            public void onNext(ResultBase resultBase) {
                if (resultBase == null) {
                    getView().showMessage(Constants.ERROR_MESSAGE);
                    return;
                }
                //如果token失效
                if (resultBase.getStatus() == Constants.MSG_STATUS_TOKEN_OUT) {
                    getView().tokenOut();
                    return;
                }
                if (resultBase.getStatus() != Constants.MSG_STATUS_SUCCESS) {
                    getView().showMessage(resultBase.getMsg());
                    return;
                }
                //如果返回成功，打开登录界面
                getView().tokenOut();
            }
        });
    }
}
