package com.example.leon.mvp.view;

import com.example.leon.mvp.entity.BookEntity;

import java.util.List;

/**
 * Created by leon on 16/4/7.
 */
public interface BookView {
    void bindBookEntityDetail(List<BookEntity> bookEntityDetail);
}
