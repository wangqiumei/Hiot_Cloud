package com.huatec.hiot_cloud.data;

import com.huatec.hiot_cloud.data.bean.DeviceBean;
import com.huatec.hiot_cloud.data.bean.DeviceDetailBean;
import com.huatec.hiot_cloud.data.bean.UpDataStreamGpsBean;
import com.huatec.hiot_cloud.data.bean.UpDataStreamSwitchBean;
import com.huatec.hiot_cloud.data.bean.UserBean;
import com.huatec.hiot_cloud.test.networktest.LoginResultDTO;
import com.huatec.hiot_cloud.test.networktest.ResultBase;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 网络请求接口
 */
public interface NetworkService {

    public static final String BASE_URL = "http://114.67.88.191:8080";

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     * @return
     */
    @POST("/auth/login")
    io.reactivex.Observable<ResultBase<LoginResultDTO>> login(@Query("username")String userName, @Query("password")String password,
                                                 @Query("loginCode")String loginCode);

    /**
     * 获取用户信息
     * @param authorization
     * @return
     */
    @GET("/user/one")
    io.reactivex.Observable<ResultBase<UserBean>> getUserInfo(@Header("Authorization")String authorization);


    /**
     * 修改邮箱
     * @param authorization
     * @param email
     * @return
     */
    @PUT("/user/email")
    io.reactivex.Observable<ResultBase<String>> updateEmail(@Header("Authorization")String authorization, @Query("email")String email);

    /**
     * 注册
     * @param userBean
     * @return
     */
    @POST("/user/register")
    io.reactivex.Observable<ResultBase<UserBean>> register(@Body UserBean userBean);

    /**
     * 修改密码
     * @param authorization
     * @param oldpassword
     * @param newpassword
     * @param confirmpassword
     * @return
     */
    @PUT("/user/password")
    Observable<ResultBase<String>> updatePassword(@Header("Authorization") String authorization,
                                                  @Query("oldpassword") String oldpassword, @Query("newpassword") String newpassword,
                                                  @Query("confirmpassword") String confirmpassword);

    /**
     * 注销
     *
     * @param authorization
     * @return
     */
    @POST("/auth/logout")
    Observable<ResultBase> logout(@Header("Authorization") String authorization);
//    @POST("/auth/login")
//    @FormUrlEncoded
//    Call<ResponseBody> login2(@Field("username") String userName, @Field("password") String password,
//                              @Field("loginCode") String loginCode);


    @POST("/user/img")
    @Multipart
    Observable<ResultBase<String>> uploadImage(@Part MultipartBody.Part file,
                                               @Header("Authorization") String authorization);

    /**
     * 绑定设备
     *
     * @param device_pk
     * @param authorization
     * @return
     */
    @POST("/holder/device/{device_pk} ")
    Observable<ResultBase> bindDevice(@Path("device_pk") String device_pk,
                                      @Header("Authorization") String authorization);

    @GET("/holder/user")
    Observable<ResultBase<List<DeviceBean>>> ListBindedDevice(@Query("bonding") int bonding,
                                                              @Header("Authorization") String authorization);

    @GET("/device/{id}")
    Observable<ResultBase<DeviceDetailBean>> getDeviceDetail(@Path("id") String deviceId,
                                                             @Header("Authorization") String authorization);

    /**
     * 控制通道开关
     *
     * @param dataStreamId
     * @param status
     * @param authorization
     * @return
     */
    @POST("/downdatastream/switch/{downdatastream_pk}")
    Observable<ResultBase> changeSwitch(@Path("downdatastream_pk") String dataStreamId,
                                        @Query("status") int status,
                                        @Header("Authorization") String authorization);

    /**
     * 获取上行通道历史数据
     *
     * @param skip
     * @param limit
     * @param updatastreamId
     * @param authorization
     * @return
     */
    @GET("/mongo/download/{updatastreamId}/{skip}/{limit}/List")
    Observable<ResultBase<List<UpDataStreamSwitchBean>>> getSwitchUpDataStreamHistory(@Path("skip") int skip,
                                                                                      @Path("limit") int limit,
                                                                                      @Path("updatastreamId") String updatastreamId,
                                                                                      @Header("Authorization") String authorization);

    /**
     * 获取GPS通道历史数据
     *
     * @param skip
     * @param limit
     * @param updatastreamId
     * @param authorization
     * @return
     */
    @GET("/mongo/download/{updatastreamId}/{skip}/{limit}/List")
    Observable<ResultBase<List<UpDataStreamGpsBean>>> getGpsUpDataStreamHistory(@Path("skip") int skip,
                                                                                @Path("limit") int limit,
                                                                                @Path("updatastreamId") String updatastreamId,
                                                                                @Header("Authorization") String authorization);

}
