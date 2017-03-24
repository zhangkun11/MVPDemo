package com.example.retrofitrxjava.mvpdemomine.service;

import android.content.Context;

import com.example.retrofitrxjava.mvpdemomine.service.entity.Book;

import rx.Observable;

/**
 * Created by admin on 2017-03-24.
 */

public class RetrofitServiceManager {
    private RetrofitService mRetrofitService;

    public RetrofitServiceManager(Context context) {
        this.mRetrofitService = RetrofitHelpter.getInstance(context).getService();
    }
    public Observable<Book> getSearchBooks(String name,String tag,int start,int count){
        return mRetrofitService.getSearchBooks(name, tag, start, count);
    }
}
