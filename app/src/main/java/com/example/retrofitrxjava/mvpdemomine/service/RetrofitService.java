package com.example.retrofitrxjava.mvpdemomine.service;

import com.example.retrofitrxjava.mvpdemomine.service.entity.Book;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2017-03-24.
 */

public interface RetrofitService {
    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag,
                                    @Query("start") int start,
                                    @Query("count") int count
                                    );
}
