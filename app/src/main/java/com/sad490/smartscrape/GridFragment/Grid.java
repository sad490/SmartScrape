package com.sad490.smartscrape.GridFragment;

import com.sad490.smartscrape.NetWork.Article;
import com.sad490.smartscrape.NetWork.Tag;

/**
 * Created by sad490 on 2/24/18.
 */
public class Grid {
    private Article article;

    public Grid( Article article){
        this.article = article;
    }

    public Article getArticle() {
        return article;
    }
}