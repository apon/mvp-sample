package me.apon.mvpsample.app;

/**
 * Created by yaopeng(aponone@gmail.com) on 2018/4/2.
 */

public interface MvpPresenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
