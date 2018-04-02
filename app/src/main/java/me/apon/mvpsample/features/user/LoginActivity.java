package me.apon.mvpsample.features.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Toast;

import me.apon.mvpsample.R;
import me.apon.mvpsample.app.BaseActivity;

/**
 * Created by yaopeng(aponone@gmail.com) on 2018/4/2.
 */

public class LoginActivity extends BaseActivity implements LoginView {
    TextInputEditText emailEt;
    TextInputEditText pwdEt;

    LoginPersenter<LoginView> loginPersenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_act);
        loginPersenter = new LoginPersenter<>();
        loginPersenter.attachView(this);
        initView();
    }

    private void initView() {
        emailEt = (TextInputEditText) findViewById(R.id.email_et);
        pwdEt = (TextInputEditText) findViewById(R.id.pwd_et);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPersenter.detachView();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_bt:
                String email = emailEt.getText().toString();
                String pwd = pwdEt.getText().toString();
                loginPersenter.login(email,pwd);
                break;
            case R.id.register:
                RegisterActivity.start(this);
                break;
        }
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void dismissLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
