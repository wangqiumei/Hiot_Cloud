package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private TestRetrofitService service;
    private String TAG = " TestRetrofitActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);

        //创建retrofit和service对象
        createRetrofit();

        //百度
        Button btnTest = findViewById(R.id.btn_retrofit_enqueue);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();

            }
        });

        //登录
        Button btnLogin = findViewById(R.id.btn_retrofit_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login("wangqiumei123", "abc123456", "app");

            }
        });

        //另一种登录方式
        Button btnLogin2 = findViewById(R.id.btn_retrofit_login2);
        btnLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login2("wangqiumei123", "abc123456", "app");

            }
        });

        //用户信息
        Button btnUserInfo = findViewById(R.id.btn_retrofit_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserInfo("329e4e0af74248809fc60b878d897567_1b6b34a0e1214f9b9f025ac4840ed3ac_use");

            }
        });


        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_retrofit_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail("329e4e0af74248809fc60b878d897567_1b6b34a0e1214f9b9f025ac4840ed3ac_use",
                        "12345678@qq.com");

            }
        });


        //注册
        Button btnRegister = findViewById(R.id.btn_retrofit_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    /**
     * 注册用户
     */
    private void register() {
        UserBean userBean = new UserBean();
        userBean.setUsername("wang123");
        userBean.setEmail("wang123@qq.com");
        userBean.setPassword("abc123");
        userBean.setUserType("1");
        Call<ResponseBody> call = service.register(userBean);
        CallEnqueue(call);

    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        Call<ResponseBody> call = service.updateEmail(authorization, email);
        CallEnqueue(call);
    }

    /**
     * 用户信息
     * @param authorization
     */
    private void getUserInfo(String authorization) {

        Call<ResponseBody> call = service.getUserInfo(authorization);
        CallEnqueue(call);
    }

    private void CallEnqueue(Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, "onResponse: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "onResponse: " + e.getMessage(), e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    /**
     * 登录
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {

        Call<ResponseBody> call = service.login(userName, password, loginCode);
        CallEnqueue(call);
    }

    /**
     * 另一种登录方式
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login2(String userName, String password, String loginCode) {

        Call<ResponseBody> call = service.login2(userName, password, loginCode);
        CallEnqueue(call);
    }

    private void test() {
        Call<ResponseBody> call = service.test();
        CallEnqueue(call);

    }

    private void createRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl(TestRetrofitService.basUrl)
                .addConverterFactory(GsonConverterFactory.create()).build();
        service = retrofit.create(TestRetrofitService.class);

    }
}
