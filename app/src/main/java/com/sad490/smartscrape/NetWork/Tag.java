package com.sad490.smartscrape.NetWork;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 2/18/18.
 */

public class Tag {
    private String tag_name;
    private String tag_num;
    private String tag_url;

    public List<Article> articles = new ArrayList<>();

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
