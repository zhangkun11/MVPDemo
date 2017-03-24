package com.example.retrofitrxjava.mvpdemomine.service.view;

import com.example.retrofitrxjava.mvpdemomine.service.entity.Book;

/**
 * Created by admin on 2017-03-24.
 */

public interface BookView {
    void onSuccess(Book mBook);
    void onError(String result);
}
