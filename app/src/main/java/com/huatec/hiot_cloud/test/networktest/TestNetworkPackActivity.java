package com.huatec.hiot_cloud.test.networktest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.data.DataManager;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * 网络封装层测试类
 */
public class TestNetworkPackActivity extends BaseActivity implements TestNetworkPackView{

    private String TAG = "TestNetworkPackActivity";
    private EditText etToken;

    @Inject
    DataManager dataManager;

    @Inject
    TestNetworkPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_network_pack);


        //editText
        etToken = findViewById(R.id.et_network_pack_retrofit);

        //登录
        Button btnLogin = findViewById(R.id.btn_network_pack_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.login("test1", "abc123456");
//                login("wangqiumei123", "abc123456");
            }
        });
        //获取用户信息
        Button btnUserInfo = findViewById(R.id.btn_network_pack_userinfo);
        btnUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getUserInfo(etToken.getText(), toString());
            }
        });
        //修改邮箱
        Button btnUpdateEmail = findViewById(R.id.btn_network_pack_update_email);
        btnUpdateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateEmail(etToken.getText().toString(), "test2@qq.com");
            }
        });
        //注册
        Button btnRegister = findViewById(R.id.btn_network_pack_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.register("test1", "abc123456", "test1@qq.com");
            }
        });
    }

    @Override
    public BasePresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    /**
     * 登录方法
     * @param userName
     * @param password
     */
    private void login(String userName, String password) {
//        dataManager.login(userName, password)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Observer<ResultBase<LoginResultDTO>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResultBase<LoginResultDTO> resultBase) {
//                        if (resultBase != null && resultBase.getData() != null){
//                            etToken.setText(resultBase.getData().getTokenValue());
//                        }else if (resultBase != null && !TextUtils.isEmpty(resultBase.getMsg())){
//                            Toast.makeText(TestNetworkPackActivity.this, resultBase.getMsg(), Toast.LENGTH_SHORT).show();
//                            Log.d(TAG, "onNext: " + resultBase.getMsg());
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
    }

    @Override
    public void showToken(String token) {
        etToken.setText(token);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
