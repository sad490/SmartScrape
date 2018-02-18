package com.sad490.smartscrape.NetWork;

/**
 * Created by sad490 on 2/18/18.
 */

public class Article {
    private String title;
    private String url;
    private String date;
    private String publisher;

    public Article() {
        this.title = "";
        this.url = "";
    }

    public Article(String _title, String _content) {
        this.title = _title;
        this.url = _content;
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
