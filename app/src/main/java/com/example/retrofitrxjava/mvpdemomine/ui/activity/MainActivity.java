package com.example.retrofitrxjava.mvpdemomine.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    @InjectView(R.id.line_book)
    View lineBook;
    private BookPresenter bookPresenter;
    private String bookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        bookPresenter = new BookPresenter(this);

        bookSearch.setText("第一行代码");
        bookSearch.setSelection(bookSearch.getText().length());

    }

    @Override
    protected void onStart() {
        super.onStart();
        bookPresenter.onCreate();
        bookPresenter.onAttachView(mBookView);
    }

    private BookView mBookView = new BookView() {
        @Override
        public void onSuccess(Book mBook) {
            //bookTitle.setText(mBook.toString());
            setBookTitle(mBook);
            //bookInfo.setText(mBook.getBooks().toString());
            setBookInfo(mBook);
            Glide.with(MainActivity.this).load(mBook.getBooks().get(0).getImage()).into(bookImage);
            lineBook.setVisibility(View.VISIBLE);
        }

        @Override
        public void onError(String result) {
            bookInfo.setText(result);

        }
    };

    @OnClick(R.id.checkInfo)
    public void onClick() {
        bookName = bookSearch.getText().toString();
        bookPresenter.getSearchBooks(bookName, null, 0, 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        bookPresenter.onStop();
    }

    private void setBookTitle(Book mBook) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("书籍：").append(mBook.getBooks().get(0).getTitle()).append("\n");
        stringBuilder.append("作者：").append(mBook.getBooks().get(0).getAuthor()).append("\n");
        stringBuilder.append("出版社：").append(mBook.getBooks().get(0).getPublisher()).append("\n");
        stringBuilder.append("出版时间：").append(mBook.getBooks().get(0).getPubdate()).append("\n");
        stringBuilder.append("装帧：").append(mBook.getBooks().get(0).getBinding()).append("\n");
        stringBuilder.append("页数：").append(mBook.getBooks().get(0).getPages()).append("\n");
        stringBuilder.append("价格：").append(mBook.getBooks().get(0).getPrice()).append("\n");
        bookTitle.setText(stringBuilder);
    }

    private void setBookInfo(Book mBook) {
        StringBuilder sb = new StringBuilder();
        sb.append(mBook.getBooks().get(0).getCatalog()).append("\n").append("\n");
        sb.append("   作者介绍:").append(mBook.getBooks().get(0).getAuthor_intro()).append("\n").append("\n");
        sb.append("   书籍摘要:").append(mBook.getBooks().get(0).getSummary()).append("\n");
        bookInfo.setText(sb);
    }
}
