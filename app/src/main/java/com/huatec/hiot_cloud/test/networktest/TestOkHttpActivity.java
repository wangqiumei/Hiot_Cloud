package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.huatec.hiot_cloud.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp框架测试类
 */
public class TestOkHttpActivity extends AppCompatActivity {

    //    private static final String basUrl="http://www.baidu.com";
    private static final String basUrl = "http://114.67.88.191:8080";
    private static final String TAG = "TestOkHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_ok_http);

        //execute方法测试类
        Button btnExecute = findViewById(R.id.btn_okhttp_execute);
        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testExecute();
            }
        });

        //enqueue方法
        Button btnEnqueue = findViewById(R.id.btn_okhttp_enqueue);
        btnEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testEqueue();
            }
        });

        //登录测试
        Button btnLogin = findViewById(R.id.btn_okhttp_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login("wangqiumei123", "abc123456", "app");

            }
        });

        //获取用户信息
        Button btnGetUserInfo = findViewById(R.id.btn_okhttp_userinfo);
        btnGetUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getGetUserInfo("329e4e0af74248809fc60b878d897567_a467b50f6a8648b694471a3bf7e030d5_use");

            }
        });

        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_okhttp_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateEmail("authorization", "email");

            }
        });
    }

    /**
     * 修改邮箱
     * @param authorization
     * @param email
     */
    private void updateEmail(String authorization, String email) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + "/user/email?email=" + email;
        Request request = new Request.Builder().put(body).url(url).header("Authorization", authorization).build();
        callEqueue(okHttpClient, request);

    }

    private void callEqueue(OkHttpClient okHttpClient, Request request) {
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(TAG, "onFailure: " + e.getMessage(), e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                Log.d(TAG, "onResponse: " + response.body().string());
            }
        });
    }


    /**
     * 获取用户信息
     *
     * @param authorization
     */
    private void getGetUserInfo(String authorization) {

        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + "/user/one";
        Request request = new Request.Builder().get().url(url).header("Authorization", authorization).build();

        callEqueue(okHttpClient, request);
    }


    /**
     * 登录
     *
     * @param userName
     * @param password
     * @param loginCode
     */
    private void login(String userName, String password, String loginCode) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody body = new FormBody.Builder().build();
        String url = basUrl + String.format("/auth/login?username=%s&password=%s&loginCode=%s",
                userName, password, loginCode);
        Request request = new Request.Builder().post(body).url(url).build();

        callEqueue(okHttpClient, request);
    }


    /**
     * 测试testExecute方法
     */
    private void testExecute() {

        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(basUrl).build();

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.d(TAG, "run: " + response.body().string());
                } catch (IOException e) {
                    Log.e(TAG, "testExecute: " + e.getMessage(), e);
                }
            }
        }.start();


    }

    /**
     * 测试testEqueue方法
     */
    private void testEqueue() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(basUrl).build();

        callEqueue(okHttpClient, request);


    }


}
