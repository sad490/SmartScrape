package com.sad490.smartscrape.NetWork;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sad490 on 2/18/18.
 */

public class Article implements Parcelable{
    private String title;
    private String url;
    private String date;
    private String publisher;

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.title);
        dest.writeString(this.url);
    }

    public Article() {
        this.title = "";
        this.url = "";
    }

    public Article(String _title, String _url) {
        this.title = _title;
        this.url = _url;
    }

    public Article( Parcel parcel ) {
        this.title = parcel.readString();
        this.url = parcel.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel parcel) {
            return new Article(parcel);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
