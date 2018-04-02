package me.apon.mvpsample.data.network.api;


import io.reactivex.Observable;
import me.apon.mvpsample.data.model.BaseResponse;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by yaopeng(aponone@gmail.com) on 2018/1/26.
 */

public interface UserApi {

    @GET("/api/auth/login")
    Observable<BaseResponse> login(@Query("email") String email, @Query("pwd") String pwd);


    @POST("/api/auth/register")
    Observable<BaseResponse> register(@Query("email") String email, @Query("pwd") String pwd);


}
