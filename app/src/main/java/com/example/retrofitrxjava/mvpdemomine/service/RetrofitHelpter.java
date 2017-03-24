package com.example.retrofitrxjava.mvpdemomine.service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017-03-24.
 */

public class RetrofitHelpter {
    private Context context;
    public static RetrofitHelpter instance;
    private Retrofit mRetrofit;
    private OkHttpClient client=new OkHttpClient();
    private GsonConverterFactory factory=GsonConverterFactory.create(new GsonBuilder().create());


    public RetrofitHelpter(Context context) {
        this.context = context;
        init();
    }

    private void init() {
        mRetrofit=new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public static RetrofitHelpter getInstance(Context context) {
        if(instance==null){
            instance=new RetrofitHelpter(context);
        }
        return instance;
    }
    public RetrofitService getService(){
        return mRetrofit.create(RetrofitService.class);
    }

}
