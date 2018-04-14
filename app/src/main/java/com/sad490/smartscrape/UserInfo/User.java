package com.sad490.smartscrape.UserInfo;

/**
 * Created by sad490 on 3/24/18.
 */

/**
 * This is for user func . You should Know .
 */
public class User {

    private String header_url ;
    private String name;

    public User( String url, String _name ) {
        this.header_url = url;
        this.name = _name;
    }

    public String getHeader_url() {
        return header_url;
    }

    public String getName() {
        return name;
    }
}