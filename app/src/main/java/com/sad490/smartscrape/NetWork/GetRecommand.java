package com.sad490.smartscrape.NetWork;

import android.support.annotation.NonNull;
import android.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
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
