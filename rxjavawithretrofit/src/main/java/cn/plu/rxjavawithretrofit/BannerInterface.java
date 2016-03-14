package cn.plu.rxjavawithretrofit;


import retrofit.Call;
import retrofit.http.GET;
import rx.Observable;

/**
 * Created by lily on 16-2-16.
 */
public interface BannerInterface {
    @GET("api/home/banner")
    Call<String> getBanner();
    @GET("api/home/banner")
    Observable<Banner> getBanner1();
}
