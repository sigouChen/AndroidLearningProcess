package com.plu.huangxingli.androidlearningprocess.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lily on 16-2-16.
 */
public interface BannerInterface {
    @GET("api/home/banner")
    Call<String> getBanner();
}
