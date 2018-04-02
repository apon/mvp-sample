package me.apon.mvpsample.features.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.apon.mvpsample.R;
import me.apon.mvpsample.app.BaseActivity;

/**
 * Created by yaopeng(aponone@gmail.com) on 2018/4/2.
 */

public class RegisterActivity extends BaseActivity implements RegisterView {

    TextInputEditText emailEt;
    TextInputEditText pwdEt;

    RegisterPersenter<RegisterView> registerPersenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, RegisterActivity.class);
        //starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_act);
        registerPersenter = new RegisterPersenter<>();
        registerPersenter.attachView(this);
        initView();
    }

    private void initView() {
        emailEt = (TextInputEditText) findViewById(R.id.email_et);
        pwdEt = (TextInputEditText) findViewById(R.id.pwd_et);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerPersenter.detachView();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.register_bt:
                String email = emailEt.getText().toString();
                String pwd = pwdEt.getText().toString();
                registerPersenter.register(email,pwd);
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
    public void registerSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void registerError(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
