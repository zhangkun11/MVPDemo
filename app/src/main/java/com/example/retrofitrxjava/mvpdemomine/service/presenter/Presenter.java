package com.example.retrofitrxjava.mvpdemomine.service.presenter;

import com.example.retrofitrxjava.mvpdemomine.service.view.BookView;

/**
 * Created by admin on 2017-03-24.
 */

public interface Presenter {
    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void onAttachView(BookView view);

}
