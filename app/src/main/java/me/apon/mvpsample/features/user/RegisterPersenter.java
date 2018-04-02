package me.apon.mvpsample.features.user;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.apon.mvpsample.app.BasePresenter;
import me.apon.mvpsample.data.model.BaseResponse;
import me.apon.mvpsample.data.network.RetrofitClient;
import me.apon.mvpsample.data.network.api.UserApi;

/**
 * Created by yaopeng(aponone@gmail.com) on 2018/4/2.
 */

public class RegisterPersenter<V extends RegisterView> extends BasePresenter<V> {

    private CompositeDisposable compositeDisposable;

    public RegisterPersenter() {
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * 注册
     * @param email
     * @param pwd
     */
    public void register(String email,String pwd){

        RetrofitClient.service(UserApi.class)
                .register(email,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        getMvpView().showLoading();
                    }
                })
                .subscribe(new Observer<BaseResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(@NonNull BaseResponse baseResponse) {
                        getMvpView().registerSuccess();
                        getMvpView().dismissLoading();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        getMvpView().registerError(e);
                        getMvpView().dismissLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });




    }

    @Override
    public void detachView() {
        super.detachView();
        compositeDisposable.clear();
    }
}
