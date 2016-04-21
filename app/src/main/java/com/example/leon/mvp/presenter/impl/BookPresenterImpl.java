package com.example.leon.mvp.presenter.impl;

import com.example.leon.mvp.entity.BookEntity;
import com.example.leon.mvp.model.BookModel;
import com.example.leon.mvp.presenter.BookPresenter;
import com.example.leon.mvp.service.BookService;
import com.example.leon.mvp.view.BookView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by leon on 16/4/7.
 */
public class BookPresenterImpl implements BookPresenter {
    private BookView bookView;
    private BookModel bookModel;
    private BookService bookService;

    public BookPresenterImpl(BookView bookView) {
        bookModel = new BookModel();
        bookService = bookModel.getBookService();
        this.bookView = bookView;
    }


    @Override
    public void getBookDetail() {
        bookService.getBookContent()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        bookView.showLoading();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BookEntity>() {
                    @Override
                    public void onCompleted() {
                        bookView.dismissLoading();
                        bookView.completed();
                        this.unsubscribe();
                    }

                    @Override
                    public void onError(Throwable e) {
                        bookView.showErrorMessage(e.toString());
                        this.unsubscribe();
                    }

                    @Override
                    public void onNext(BookEntity bookEntity) {
                        bookView.bindJuHeJoke(bookEntity);
                    }
                });
    }
}
