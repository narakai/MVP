package com.example.leon.mvp.model;

import com.example.leon.mvp.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 16/4/7.
 */
public class BookModel {
    public List<BookEntity> getBookEntityData (){
        List<BookEntity> bookEntityList = new ArrayList<>();

        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookname("Thinking in Java");
        bookEntity.setBookURL("http://0.0.0.127");

        bookEntityList.add(bookEntity);

        bookEntity = new BookEntity();
        bookEntity.setBookname("Spring in Action");
        bookEntity.setBookURL("http://126.0.0.1");

        bookEntityList.add(bookEntity);

        return bookEntityList;
    }
}
