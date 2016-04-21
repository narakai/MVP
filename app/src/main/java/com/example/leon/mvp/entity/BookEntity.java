package com.example.leon.mvp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;


/**
 * Created by leon on 16/4/7.
 */
public class BookEntity implements Parcelable, Cloneable {
    private String bookname;
    private String localpath;
    private String link;
    private String thumbnailStr;
    private int Id;
    private String[] thumbnail;

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getLocalpath() {
        return localpath;
    }

    public void setLocalpath(String localpath) {
        this.localpath = localpath;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnailStr() {
        return thumbnailStr;
    }

    public void setThumbnailStr(String thumbnailStr) {
        this.thumbnailStr = thumbnailStr;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "bookname='" + bookname + '\'' +
                ", localpath='" + localpath + '\'' +
                ", link='" + link + '\'' +
                ", thumbnailStr='" + thumbnailStr + '\'' +
                ", Id=" + Id +
                ", thumbnail=" + Arrays.toString(thumbnail) +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookname);
        dest.writeString(this.localpath);
        dest.writeString(this.link);
        dest.writeString(this.thumbnailStr);
        dest.writeInt(this.Id);
        dest.writeStringArray(this.thumbnail);
    }

    public BookEntity() {
    }

    protected BookEntity(Parcel in) {
        this.bookname = in.readString();
        this.localpath = in.readString();
        this.link = in.readString();
        this.thumbnailStr = in.readString();
        this.Id = in.readInt();
        this.thumbnail = in.createStringArray();
    }

    public static final Creator<BookEntity> CREATOR = new Creator<BookEntity>() {
        @Override
        public BookEntity createFromParcel(Parcel source) {
            return new BookEntity(source);
        }

        @Override
        public BookEntity[] newArray(int size) {
            return new BookEntity[size];
        }
    };
}
