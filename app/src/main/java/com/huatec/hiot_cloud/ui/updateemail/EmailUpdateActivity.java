package com.huatec.hiot_cloud.ui.updateemail;

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

public class EmailUpdateActivity extends BaseActivity<EmailUpdateView, EmailUpdatePresenter> implements EmailUpdateView {

    @Inject
    EmailUpdatePresenter presenter;


    @BindView(R.id.btn_user_email_update)
    Button btnUserEmailUpdate;

    @BindView(R.id.et_user_email_update)
    EditText etUserEmailUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_update);
        ButterKnife.bind(this);
    }

    @Override
    public EmailUpdatePresenter createPresenter() {
        return presenter;
    }

    @Override
    public void injectIndependies() {
        getActivityComponent().inject(this);
    }


    @Override
    public void emailUpdateSucc() {
        startActivity(LoginActivity.class);
    }


    @OnClick(R.id.btn_user_email_update)
    public void onViewClicked() {
        String email = etUserEmailUpdate.getText().toString();
        presenter.EmailUpdate(email);
    }
}
