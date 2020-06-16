package com.huatec.hiot_cloud.ui.updatepassword;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.huatec.hiot_cloud.R;
import com.huatec.hiot_cloud.ui.base.BaseActivity;
import com.huatec.hiot_cloud.ui.login.LoginActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordUpdateActivity extends BaseActivity<PasswordUpdateView, PasswordUpdatePresenter> implements PasswordUpdateView {

    @Inject
    PasswordUpdatePresenter presenter;

    @BindView(R.id.et_user_old_password)
    EditText etUserOldPassword;

    @BindView(R.id.et_user_new_password)
    EditText etUserNewPassword;

    @BindView(R.id.et_user_confirm_password)
    EditText etUserConfirmPassword;

    @BindView(R.id.btn_user_password_updata)
    Button btnUserPasswordUpdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_update);
        ButterKnife.bind(this);
    }

    @Override
    public PasswordUpdatePresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.btn_user_password_updata)
    public void onViewClicked() {
        String oldpassword = etUserOldPassword.getText().toString();
        String newpassword = etUserNewPassword.getText().toString();
        String confirmpassword = etUserConfirmPassword.getText().toString();
        presenter.PasswordUpdate(oldpassword, newpassword, confirmpassword);
    }

    @Override
    public void PasswordUpdateSucc() {
        startActivity(LoginActivity.class);
    }
}
