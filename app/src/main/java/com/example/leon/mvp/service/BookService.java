package com.example.leon.mvp.service;

import com.example.leon.mvp.app.MyConstance;
import com.example.leon.mvp.entity.BookEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by leon on 16/4/21.
 */
public class BookService {
    private APIFlask apiFlask;

    public BookService() {
        apiFlask = new RetrofitInstance(MyConstance.BASE_URL)
                .getRetrofit()
                .create(APIFlask.class);
    }

    private interface APIFlask{
        @GET("/allbooks")
        Observable<BookEntity> getBookEntity();
    }

    public Observable<BookEntity> getBookContent() {
        return apiFlask.getBookEntity();
    }
}
