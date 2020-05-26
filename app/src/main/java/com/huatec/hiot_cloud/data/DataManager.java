package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 网络请求封装类
 */
public class DataManager {

    private NetworkService service;

    SharedPreferencesHelper sharedPreferencesHelper;

    @Inject
    public DataManager(NetworkService service, SharedPreferencesHelper sharedPreferencesHelper) {
        this.service = service;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @return
     */
    public io.reactivex.Observable<ResultBase<LoginResultDTO>> login( String userName, String password) {
        return service.login(userName, password, Constants.LOGIN_CODE_APP)
                .doOnNext(new Consumer<ResultBase<LoginResultDTO>>() {
                    @Override
                    public void accept(ResultBase<LoginResultDTO> resultBase) throws Exception {
                        if (resultBase.getStatus() == Constants.MSG_STATUS_SECCESS) {
                            if (resultBase != null && resultBase.getData() != null) {
                                sharedPreferencesHelper.setUserToken(resultBase.getData().getTokenValue());
                            }
                        }
                    }
                });
    }

    /**
     * 获取用户信息
     *
     * @param authorization
     * @return
     */

    public io.reactivex.Observable<ResultBase<UserBean>> getUserInfo(String authorization) {
        return service.getUserInfo(authorization);
    }


    /**
     * 修改邮箱
     *
     * @param authorization
     * @param email
     * @return
     */

    public io.reactivex.Observable<ResultBase<String>> updateEmail( String authorization,String email) {
        return service.updateEmail(authorization, email);
    }

    /**
     *注册
     * @param username  用户名
     * @param password  密码
     * @param email  邮箱
     * @return
     */

    public io.reactivex.Observable<ResultBase<UserBean>> register(String username, String password, String email) {

        UserBean userBean = new UserBean();
        userBean.setUsername(username);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setUserType(Constants.REGISTER_TYPE_NOMAL);
        return service.register(userBean);
    }


}
