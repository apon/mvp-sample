package me.apon.mvpsample.app;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yaopeng(aponone@gmail.com) on 2018/4/2.
 */

public class BasePresenter <V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;
    private V mProxyView;
    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
        // 动态代理
        mProxyView = (V)Proxy.newProxyInstance(mvpView.getClass().getClassLoader(),
                mvpView.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (mMvpView != null) {
                            return method.invoke(mMvpView, args);
                        }
                        return null;
                    }
                });
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public V getMvpView() {
        return mProxyView;
    }
}
