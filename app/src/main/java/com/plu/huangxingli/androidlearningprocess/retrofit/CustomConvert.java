package com.plu.huangxingli.androidlearningprocess.retrofit;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by lily on 16-2-17.
 */
public  class CustomConvert<T> implements  Converter<ResponseBody,T>{

    Type type;

    public CustomConvert(Type type) {
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
       Reader reader= value.charStream();
        if ((type==String.class)){
            return (T)value.string();
        }else{
            Gson gson=new Gson();
            return  gson.fromJson(reader,type);
        }
    }
}
