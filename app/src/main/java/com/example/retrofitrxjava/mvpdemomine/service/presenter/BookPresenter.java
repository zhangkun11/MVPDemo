package com.example.retrofitrxjava.mvpdemomine.service.presenter;

import android.content.Context;

import com.example.retrofitrxjava.mvpdemomine.service.RetrofitServiceManager;
import com.example.retrofitrxjava.mvpdemomine.service.entity.Book;
import com.example.retrofitrxjava.mvpdemomine.service.view.BookView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by admin on 2017-03-24.
 */

public class BookPresenter implements Presenter {

    private RetrofitServiceManager retrofitServiceManager;
    private CompositeSubscription compositeSubscription;
    private Context mContxt;
    private BookView mBookView;
    private Book mBook;

    public BookPresenter(Context mContxt) {
        this.mContxt=mContxt;
    }


    @Override
    public void onCreate() {
        compositeSubscription=new CompositeSubscription();
        retrofitServiceManager=new RetrofitServiceManager(mContxt);

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        if(compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onAttachView(BookView view) {
        mBookView= view;

    }

    public void getSearchBooks(String name,String tag,int start,int count){
        compositeSubscription.add(retrofitServiceManager.getSearchBooks(name, tag, start, count)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Book>() {
            @Override
            public void onCompleted() {
                if(mBook!=null){
                    mBookView.onSuccess(mBook);
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mBookView.onError("请求失败");

            }

            @Override
            public void onNext(Book book) {
                mBook=book;
            }
        }));
    }
}
