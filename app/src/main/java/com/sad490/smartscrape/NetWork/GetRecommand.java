package com.sad490.smartscrape.NetWork;

import android.support.annotation.NonNull;
import android.util.*;

import com.sad490.smartscrape.Posters.Poster_element;
import com.sad490.smartscrape.Posters.Posters;
import com.sad490.smartscrape.Recommand.RecItem;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.sad490.smartscrape.NetWork.XMLProcessor.getTags;

/**
 * Created by sad490 on 2/18/18.
 */

/**
 * This class get
 * @link http://111.230.181.121/app_rec
 */
public class GetRecommand {

    private static final String GenHost = "http://111.230.181.121";

    /**
     * This function get the url .
     * @param httpClient
     * @return
     * @throws Exception
     */
    public static List<Tag> GetTitleAndArticle( HttpClient httpClient ) throws Exception{
        Map<String, String> ret = new HashMap<>();
        // URL url = new URL("http://111.230.181.121/app_rec");

        String html = getHtml(httpClient, "http://111.230.181.121/app_rec");
        List<Tag> tags =  XMLProcessor.getTags(html);

        for (int i = 0; i < tags.size(); ++i) {
            // Log.i("Tag" + i, tags.get(i).getTag_url());
            String subhtml = getHtml(httpClient, "http://111.230.181.121" + tags.get(i).getTag_url());
            // Log.i("subHtml" + i, subhtml);
            tags.get(i).setArticles(XMLProcessor.getArticle(subhtml));
            Log.d("tag_after_process", tags.get(i).toString());
        }
        Log.d("Tags Size", "" + tags.size());
        return tags;
    }

    public static List<Posters> getStarred( DefaultHttpClient httpClient ) throws Exception {
        List<Posters> posters = new ArrayList<>();
        String html = getHtml(httpClient, "http://111.230.181.121/person");
        List<Poster_element> temp = HtmlProcessor.getPostersElements(html);
        for (Poster_element poster_element : temp) {
            posters.add(new Posters(poster_element.getTitle(),  GenHost + poster_element.getImage_url(), poster_element.getPub_url()));
        }
        return posters;
    }

    public static List<RecItem> getHistory( DefaultHttpClient httpClient ) throws Exception {
        List<RecItem> recItems = new ArrayList<>();
        String html = getHtml(httpClient, "http://111.230.181.121/person");
        List<String> titles  = HtmlProcessor.getArticlesTitle(html);
        List<String> pubs = HtmlProcessor.getArticlesPub(html);
        List<String> dates = HtmlProcessor.getArticlesDate(html);
        List<String> urls = HtmlProcessor.getArticlesUrl(html);

        for (int i = 0; i < titles.size(); ++i) {
            Article article = new Article(titles.get(i), urls.get(i));
            article.setDate(dates.get(i));
            article.setPublisher(pubs.get(i));
            recItems.add(new RecItem(article));
        }
        return recItems;
    }

    public static String getHtml( HttpClient httpClient, String url ) throws Exception {
        HttpGet get =new HttpGet(url);
        HttpResponse response = httpClient.execute(get);
        int ch;
        InputStream inputStream = response.getEntity().getContent();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream,"UTF-8"));
        String data = "";
        StringBuilder sb = new StringBuilder();
        while((data = br.readLine()) != null) {
            sb.append(data);
            sb.append("\n");
        }
        String html = sb.toString();
        // android.util.Log.i("Get", html);
        return html;
    }
}
