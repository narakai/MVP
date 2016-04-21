package com.example.leon.mvp.view;

import com.example.leon.mvp.entity.BookEntity;

/**
 * Created by leon on 16/4/7.
 */
public interface BookView {
    //    void bindBookEntityDetail(List<BookEntity> bookEntityDetail);
    void showLoading();

    void bindJuHeJoke(BookEntity bookEntity);

    void showErrorMessage(String error);

    void dismissLoading();

    void completed();
}
