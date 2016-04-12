package com.example.leon.mvp.presenter.impl;

import com.example.leon.mvp.entity.BookEntity;
import com.example.leon.mvp.model.BookModel;
import com.example.leon.mvp.presenter.BookPresenter;
import com.example.leon.mvp.view.BookView;

import java.util.List;

/**
 * Created by leon on 16/4/7.
 */
public class BookPresenterImpl implements BookPresenter {
    private BookView bookView;
    private BookModel bookModel;

    public BookPresenterImpl(BookView bookView) {
        bookModel = new BookModel();
        this.bookView = bookView;
    }

    @Override
    public void getBookDetail() {
        List<BookEntity> bookEntityList = bookModel.getBookEntityData();
        bookView.bindBookEntityDetail(bookEntityList);
    }
}
