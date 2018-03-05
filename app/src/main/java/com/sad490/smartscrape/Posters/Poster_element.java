package com.sad490.smartscrape.Posters;

/**
 * Created by hp on 2018/3/4.
 */

public class Poster_element {
    public String title;
    public String image_url;
    public String pub_url;

    public Poster_element() {
        this.pub_url = "";
        this.image_url = "";
        this.title = "";
    }

    public Poster_element( String _title, String _image_url, String _pub_url ) {
        this.title = _title;
        this.image_url = _image_url;
        this.pub_url = _pub_url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getPub_url() {
        return pub_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setPub_url(String pub_url) {
        this.pub_url = pub_url;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
