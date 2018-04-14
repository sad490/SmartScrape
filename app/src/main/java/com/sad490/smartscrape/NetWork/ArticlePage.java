package com.sad490.smartscrape.NetWork;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sad490 on 4/6/18.
 */

public class ArticlePage implements Parcelable {
    private List<Article> articles = new ArrayList<>();
    private String nextPage_url = "";

    @Override
    public void writeToParcel(Parcel dest, int flags){
        Article[] articles_tmp = this.articles.toArray(new Article[articles.size()]);
        dest.writeParcelableArray(articles_tmp, flags);
        dest.writeString(this.nextPage_url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public ArticlePage(List<Article> _articles, String _nextPage_url) {
        this.articles = _articles;
        this.nextPage_url = _nextPage_url;
    }

    public ArticlePage(Parcel parcel) {
        Article[] _articles = parcel.createTypedArray(Article.CREATOR);
        this.articles = Arrays.asList(_articles);
        this.nextPage_url = parcel.readString();
    }

    public static final Creator<ArticlePage> CREATOR = new Creator<ArticlePage>() {
        @Override
        public ArticlePage createFromParcel(Parcel parcel) {
            return new ArticlePage(parcel);
        }

        @Override
        public ArticlePage[] newArray(int size) {
            return new ArticlePage[size];
        }
    };

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setNextPage_url(String nextPage_url) {
        this.nextPage_url = nextPage_url;
    }

    public String getNextPage_url() {
        return nextPage_url;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        return nextPage_url + " " + articles.size();
    }
}
