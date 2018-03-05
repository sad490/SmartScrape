package com.sad490.smartscrape.NetWork;

import android.util.*;

import org.apache.http.ContentTooLongException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by sad490 on 2/25/18.
 */

public class GrabArticle {


    private static String getHtml(HttpClient httpClient, String url ) throws Exception {
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

    public static String ExtractContent(DefaultHttpClient client, String back) throws Exception {
        String url = "http://111.230.181.121" + back;
        String html = getHtml(client, url);
        String content = "";
        if (back.startsWith("/app_article")) {
            Log.i("html", html);
            try {
                content = XMLProcessor.getArticleContent(html);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (back.startsWith("/article")) {
            try {
                content = HtmlProcessor.getArticle(html);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }
}
