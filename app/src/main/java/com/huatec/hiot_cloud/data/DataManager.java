package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.data.bean.DeviceBean;
import com.huatec.hiot_cloud.data.bean.DeviceDetailBean;
import com.huatec.hiot_cloud.data.bean.UpDataStreamGpsBean;
import com.huatec.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;
import com.huatec.hiot_cloud.utils.Constants;

import java.io.File;
import java.util.List;

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
     * 修改密码
     *
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     * @return
     */
    public Observable<ResultBase<String>> updatePassword(String oldpassword, String newpassword, String confirmpassword) {
        return service.updatePassword(oldpassword, newpassword, confirmpassword, sharedPreferencesHelper.getUserToken());
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

    /**
     * 设备绑定
     *
     * @param deviceId
     * @return
     */
    public Observable<ResultBase> bindDevice(String deviceId) {
        return service.bindDevice(deviceId, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 获取指定绑定状态的设备类别
     *
     * @param bonding
     * @return
     */
    public Observable<ResultBase<List<DeviceBean>>> ListBindedDevice(int bonding) {
        return service.ListBindedDevice(bonding, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 获取设备详情
     *
     * @param deviceId
     * @return
     */
    public Observable<ResultBase<DeviceDetailBean>> getDeviceDetail(String deviceId) {
        return service.getDeviceDetail(deviceId, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 控制开关通道状态
     *
     * @param dataStreamId
     * @param status
     * @return
     */
    public Observable<ResultBase> changeSwitch(String dataStreamId, int status) {
        return service.changeSwitch(dataStreamId, status, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 获取通道历史数据
     *
     * @param updatastreamId
     * @return
     */
    public Observable<ResultBase<List<UpDataStreamSwitchBean>>> getSwitchUpDataStreamHistory(String updatastreamId) {
        return service.getSwitchUpDataStreamHistory(0, Constants.DEFAULT_DATASTREAM_LIMIT, updatastreamId, sharedPreferencesHelper.getUserToken());
    }

    /**
     * 获取GPS通道历史数据
     *
     * @param updatastreamId
     * @return
     */
    public Observable<ResultBase<List<UpDataStreamGpsBean>>> getGpsUpDataStreamHistory(String updatastreamId) {
        return service.getGpsUpDataStreamHistory(0, Constants.DEFAULT_DATASTREAM_LIMIT, updatastreamId, sharedPreferencesHelper.getUserToken());
    }



}
