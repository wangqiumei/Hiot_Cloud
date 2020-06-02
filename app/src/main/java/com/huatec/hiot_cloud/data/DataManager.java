package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.test.networktest.UserBean;
import com.huatec.hiot_cloud.utils.Constants;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
                        if (resultBase.getStatus() == Constants.MSG_STATUS_SUCCESS) {
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
     * @param
     * @return
     */

    public io.reactivex.Observable<ResultBase<UserBean>> getUserInfo() {
        return service.getUserInfo(sharedPreferencesHelper.getUserToken());
    }


    /**
     * 修改邮箱
     *
     * @param email
     * @return
     */

    public io.reactivex.Observable<ResultBase<String>> updateEmail(String email) {
        return service.updateEmail(sharedPreferencesHelper.getUserToken(), email);
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

    /**
     * 上传图片
     *
     * @param filePath
     */
    public Observable<ResultBase<String>> uploadImage(String filePath) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse(Constants.MULTIPART_FORM_DATA), file);
        MultipartBody.Part multipartFile = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        return service.uploadImage(multipartFile, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 注销
     */
    public Observable<ResultBase> logout() {
        return service.logout(sharedPreferencesHelper.getUserToken())
                .doOnNext(new Consumer<ResultBase>() {
                    @Override
                    public void accept(ResultBase resultBase) throws Exception {
                        sharedPreferencesHelper.setUserToken("");
                    }
                });
    }


}
