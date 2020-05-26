package com.huatec.hiot_cloud.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.huatec.hiot_cloud.MainActivity;
import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.login.LoginActivity;
import com.huatec.hiot_cloud.utils.Constants;
import com.huatec.hiot_cloud.utils.ValidatorUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册活动
 */
public class RegisterActivity extends BaseActivity<RegisterView, RegisterPresenter> implements RegisterView {

    @BindView(R.id.tiptet_user_name)
    TextInputEditText tiptetUserName;

    @BindView(R.id.tiptet_email)
    TextInputEditText tiptetEmail;

    @BindView(R.id.tiptet_password)
    TextInputEditText tiptetPassword;

    @BindView(R.id.btn_register)
    Button btnRegister;

    @BindView(R.id.tv_link_signup)
    TextView tvLinkSignup;

    @Inject
    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    public RegisterPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }


    /**
     * 校验用户输入
     *
     * @param userName
     * @param email
     * @param password
     * @return
     */
    private boolean ValidateSucc(String userName, String email, String password) {

        //校验用户名非空
        if (TextUtils.isEmpty(userName)) {
            tiptetUserName.setError(Constants.USERNAME_UNNULL_AGAIN);
            return false;
        }

        //校验用户名合规
        if (!ValidatorUtils.isUserName(userName)) {
            tiptetUserName.setError(Constants.USERNAME_ERROR_AGAIN);
            return false;
        }

        //校验密码非空
        if (TextUtils.isEmpty(password)) {
            tiptetPassword.setError(Constants.PASSWORD_UNNULL_AGAIN);
            return false;
        }

        //校验密码合规
        if (!ValidatorUtils.isPassword(password)) {
            tiptetPassword.setError(Constants.PASSWORD_ERROR_AGAIN);
            return false;
        }

        return true;
    }

    @OnClick({R.id.btn_register, R.id.tv_link_signup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                //注册操作
                String userName = tiptetUserName.getText().toString();
                String password = tiptetPassword.getText().toString();
                String email = tiptetEmail.getText().toString();
                presenter.register(userName, password, email);
                break;
            case R.id.tv_link_signup:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void registerSucc(String email, String password) {
        //注册成功后，做自动登录
        presenter.login(email, password);
    }

    @Override
    public void loginSucc() {
        //跳转到主界面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
