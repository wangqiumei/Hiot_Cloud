package com.huatec.hiot_cloud;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.huatec.hiot_cloud.data.SharedPreferencesHelper;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.base.BasePresenter;
import com.huatec.hiot_cloud.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity {

    private static final int MSG_WHAT_START_MAIN = 1;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessageAtTime(MSG_WHAT_START_MAIN, 3000);
            }
        }, 3000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //如果已登录，跳转主界面
            Intent intent = null;
            if (!TextUtils.isEmpty(sharedPreferencesHelper.getUserToken())) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                //如果未登录，跳转登录界面
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        }
    };

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

}
