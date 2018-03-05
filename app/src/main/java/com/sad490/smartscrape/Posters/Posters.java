package com.sad490.smartscrape.Posters;

import android.graphics.Bitmap;


/**
 * Created by sad490 on 3/4/18.
 */
public class Posters {
    public String Name;
    public Bitmap Header;

    public String image_url;
    public String content_url;

    public Posters() {
        Name = "";
        Header = Bitmap.createBitmap( 300, 300, Bitmap.Config.ARGB_8888 );
    }

    public Posters( String name, Bitmap bitmap ) {
        Name = name;
        Header = bitmap;
    }

    public Posters( String name, String _image_url, String _content_url ) {
        this.Name = name;
        this.image_url = _image_url;
        this.content_url = _content_url;
    }

    public String getName() {
        return this.Name;
    }

    public Bitmap getHeader() {
        return this.Header;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }
}