package com.example.leon.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by leon on 16/4/7.
 */
public class BookEntity implements Parcelable, Cloneable {
    private String bookname;
    private String bookURL;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookURL() {
        return bookURL;
    }

    public void setBookURL(String bookURL) {
        this.bookURL = bookURL;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "bookname='" + bookname + '\'' +
                ", bookURL='" + bookURL + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookname);
        dest.writeString(this.bookURL);
    }

    public BookEntity() {
    }

    protected BookEntity(Parcel in) {
        this.bookname = in.readString();
        this.bookURL = in.readString();
    }

    public static final Parcelable.Creator<BookEntity> CREATOR = new Parcelable.Creator<BookEntity>() {
        @Override
        public BookEntity createFromParcel(Parcel source) {
            return new BookEntity(source);
        }

        @Override
        public BookEntity[] newArray(int size) {
            return new BookEntity[size];
        }
    };

    @Override
    protected BookEntity clone() throws CloneNotSupportedException {
        //deep copy
        try {
            BookEntity bookEntity = (BookEntity) super.clone();
            bookEntity.bookname = this.bookname;
            bookEntity.bookURL = this.bookURL;
            return bookEntity;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
