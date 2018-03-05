package com.sad490.smartscrape.Recommand;

import com.sad490.smartscrape.NetWork.Article;
import com.sad490.smartscrape.NetWork.Tag;

/**
 * Created by sad490 on 2/19/18.
 */
public class RecItem {
//    public Tag tag;
//
//    public RecItem( Tag tag ) {
//        this.tag = tag;
//    }
    public Article article;

    public RecItem( Article  _article ) {
        this.article = _article;
    }
}