package com.example.retrofitrxjava.mvpdemomine.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.retrofitrxjava.mvpdemomine.R;
import com.example.retrofitrxjava.mvpdemomine.service.entity.Book;
import com.example.retrofitrxjava.mvpdemomine.service.presenter.BookPresenter;
import com.example.retrofitrxjava.mvpdemomine.service.view.BookView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.book_info)
    TextView bookInfo;
    @InjectView(R.id.checkInfo)
    Button checkInfo;
    @InjectView(R.id.book_image)
    ImageView bookImage;
    @InjectView(R.id.book_title)
    TextView bookTitle;
    @InjectView(R.id.book_search)
    EditText bookSearch;
    private BookPresenter bookPresenter;
    private String bookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        bookPresenter = new BookPresenter(this);
        bookPresenter.onCreate();
        bookPresenter.onAttachView(mBookView);
        bookSearch.setText("西游记");
        bookSearch.setSelection(bookSearch.getText().length());

    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            bookTitle.setText(mBook.toString());
            bookInfo.setText(mBook.getBooks().toString());
            Glide.with(MainActivity.this).load(mBook.getBooks().get(0).getImage()).into(bookImage);
        }

        @Override
        public void onError(String result) {
            bookInfo.setText(result);

        }
    };

    @OnClick(R.id.checkInfo)
    public void onClick() {
        bookName=bookSearch.getText().toString();
        bookPresenter.getSearchBooks(bookName, null, 0, 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookPresenter.onStop();
    }
}
