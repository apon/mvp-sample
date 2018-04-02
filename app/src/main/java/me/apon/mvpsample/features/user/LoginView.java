package me.apon.mvpsample.features.user;

import me.apon.mvpsample.app.MvpView;

/**
 * Created by yaopeng(aponone@gmail.com) on 2018/4/2.
 */

public interface LoginView extends MvpView{

    void loginSuccess();

    void loginError(Throwable e);
}
