package com.huatec.hiot_cloud.ui.login;

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
import com.huatec.hiot_cloud.ui.register.RegisterActivity;
import com.huatec.hiot_cloud.utils.Constants;
import com.huatec.hiot_cloud.utils.LoadingUtil;
import com.huatec.hiot_cloud.utils.ValidatorUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录活动类
 */
public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {

    @Inject
    LoginPresenter presenter;

    @BindView(R.id.tiptet_email)
    TextInputEditText tiptetEmail;

    @BindView(R.id.tiptet_password)
    TextInputEditText tiptetPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_link_signup)
    TextView tvLinkSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//       tiptetEmail = findViewById(R.id.tiptet_email);
//       tiptetPassword = findViewById(R.id.tiptet_password);
//       Button btnLogin = findViewById(R.id.btn_login);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View v) {
//               //如果校验成功，则保存登录状态，跳转到列表界面
//               String email = tiptetEmail.getText().toString();
//                String password = tiptetPassword.getText().toString();
//                if (ValidateSucc(email,password)){
//                    //请求服务端身份验证
//                   LoadingUtil.showLoading(LoginActivity.this,"正在登录...");
//                   presenter.login(email,password);
//
//               }
//            }
//        });
    }

    @Override
    public LoginPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    /**
     * 校验用户输入
     *
     * @param email
     * @param password
     * @return
     */
    private boolean ValidateSucc(String email, String password) {

        //校验邮箱非空
        if (TextUtils.isEmpty(email)) {
            tiptetEmail.setError(Constants.EMAIL_UNNULL_AGAIN);
            return false;
        }
        //校验邮箱合规
        if (!ValidatorUtils.isEmail(email)) {
            tiptetEmail.setError(Constants.EMAIL_ERROR_AGAIN);
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


    @Override
    public void loginSucc() {
        //跳转到主界面
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onClick(View view) {
        //如果校验成功，则保存登录状态，跳转到列表界面
        String email = tiptetEmail.getText().toString();
        String password = tiptetPassword.getText().toString();
        if (ValidateSucc(email, password)) {
            //请求服务端身份验证
            LoadingUtil.showLoading(LoginActivity.this, Constants.LOGINING);
            presenter.login(email, password);

        }
    }

    @OnClick(R.id.tv_link_signup)
    public void onViewClicked() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }


}
