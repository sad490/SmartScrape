package com.sad490.smartscrape.NetWork;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sad490 on 2/18/18.
 */

public class Tag implements Parcelable{
    private String tag_name;
    private String tag_num;
    private String tag_url;

    public List<Article> articles = new ArrayList<>();

    @Override
    public void writeToParcel(
            Parcel dest,
            int flags
            ) {
        dest.writeString(this.getTag_name());
        dest.writeString(this.getTag_num());
        dest.writeString(this.getTag_url());
        dest.writeTypedArray(
                this.getArticles().toArray(new Article[this.getArticles().size()]),
                flags
        );
    }

    public Tag() {
        this.tag_url = "";
        this.tag_name = "";
        this.tag_num = "";
    }

    public Tag( String _tag_name, String _tag_num, String _tag_url) {
        this.tag_name = _tag_name;
        this.tag_num = _tag_num;
        this.tag_url = _tag_url;
    }

    public Tag( Parcel parcel ) {
        this.tag_name = parcel.readString();
        this.tag_num = parcel.readString();
        this.tag_url = parcel.readString();
        // Article[] temp = parcel.readParcelableArray(Article.class.getClassLoader());
        Article[] temp = parcel.createTypedArray(Article.CREATOR);

        this.articles = Arrays.asList(temp);
    }

    public static final Creator<Tag> CREATOR = new Creator<Tag>() {
        @Override
        public Tag createFromParcel(Parcel parcel) {
            return new Tag(parcel);
        }

        @Override
        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void setTag_name( String name ) {
        this.tag_name = name;
    }

    public void setTag_num( String num ) {
        this.tag_num = tag_num;
    }

    public void setTag_url( String url ) {
        this.tag_url = url;
    }

    public String getTag_name() {
        return this.tag_name;
    }

    public String getTag_num() {
        return this.tag_num;
    }

    public String getTag_url() {
        return this.tag_url;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        String article = "";
        for (int i = 0; i < articles.size(); ++i) {
            article += articles.get(i).toString() + "\n";
        }
        return
                " : " + this.tag_num + " : " + this.tag_url + "\n" + article;
    }
}
