package com.plu.huangxingli.androidlearningprocess.retrofit;


import com.plu.huangxingli.androidlearningprocess.RxJava.Banner;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by lily on 16-2-16.
 */
public interface BannerInterface {
    @GET("api/home/banner")
    Call<String> getBanner();
    @GET("api/home/banner")
    Observable<String> getBanner1();
}
