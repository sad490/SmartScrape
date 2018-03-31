package com.sad490.smartscrape.NetWork;

import android.widget.EditText;

import com.sad490.smartscrape.Posters.Poster_element;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 3/4/18.
 */

public class HtmlProcessor {

    public static String getArticle( String html ) throws Exception {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div.post-bd");
        if (elements != null) {
            return elements.text();
        }
        return null;
    }

    public static String getNextPage( String html ) throws Exception {
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("a.next-page");
        if (elements != null) {
            return elements.attr("href");
        }
        return null;
    }

    public static List<String> getArticlesTitle( String html ) throws Exception {
        List<String> ret = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("h1.title");
        ret = elements.eachText();
        // Log.d("ret:", ret.get(0));
        return ret;
    }

    public static List<String> getArticlesUrl( String html ) throws Exception {
        List<String> ret = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("h1.title");
        elements = elements.select("a");
        ret = elements.eachAttr("href");
        // Log.d("ret:", ret.get(0));
        return ret;
    }

    public static List<String> getArticlesPub( String html ) throws Exception {
        List<String> ret = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("span.glyphicon").select(".glyphicon-user");
        ret = elements.eachText();
        return ret;
    }

    public static List<String> getArticlesDate( String html ) throws Exception {
        List<String> ret = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div.post-ft").select("span.date");
        ret = elements.eachText();
        return ret;
    }

    public static List<String> getHeadersUrl( String html ) throws Exception {
        List<String> ret = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div.panel-body");
        elements = elements.select("img");
        ret = elements.eachAttr("src");
        // Log.d("ret:", ret.get(0));
        return ret;
    }

    public static List<String> getHeadersids( String html ) throws Exception {
        List<String> ret = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div.panel-footer");
        elements = elements.select("a");
        ret = elements.eachAttr("href");
        // Log.d("ret:", ret.get(0));
        return ret;
    }

    public static List<String> getHeadersPubs( String html ) throws Exception {
        List<String> ret = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div.panel-footer");
        ret = elements.eachText();
        // Log.d("Panel : ", ret.get(0));
        return ret;
    }

    public static List<Poster_element> getPostersElements( String html ) throws Exception {
        List<Poster_element> ret = new ArrayList<>();
        List<String> header_urls = getHeadersUrl( html );
        List<String> header_pubs = getHeadersids( html );
        List<String> header_ids = getHeadersPubs( html );

        for (int i = 0; i < header_ids.size(); ++i) {
            ret.add(new Poster_element(header_ids.get(i), header_urls.get(i), header_pubs.get(i)));
        }
        return ret;
    }
}
