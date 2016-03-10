package com.plu.huangxingli.androidlearningprocess.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lily on 16-2-26.
 */
public abstract class BaseCallback<T> implements Callback<T> {

    public abstract  void success();
    @Override
    public void onResponse(Call<T> call, Response<T> response) {

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

    }
}
