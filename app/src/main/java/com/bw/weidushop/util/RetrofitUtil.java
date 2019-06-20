package com.bw.weidushop.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * com.baway.retrofitdemo.util
 *
 * @author 李宁康
 * @date 2019 2019/05/13 16:16
 */
public  class RetrofitUtil {

    private OkHttpClient okHttpClient;
    private static RetrofitUtil retrofitUtil=new RetrofitUtil();
    public static RetrofitUtil getRetrofitUtil(){
        return retrofitUtil;
    }
    public Retrofit retrofit;
    private RetrofitUtil(){
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//设置日志打印内容：BODY,HEADER,BASIC,NONE
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)//添加日志拦截器
                .connectTimeout(60,TimeUnit.SECONDS)//连接超时
                .writeTimeout(10,TimeUnit.SECONDS)//写入超时
                .readTimeout(10,TimeUnit.SECONDS)//读取超时
                .build();

        retrofit=  new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://mobile.bwstudent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


  }




}
