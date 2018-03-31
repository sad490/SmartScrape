package com.sad490.smartscrape.NetWork;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpProcessor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/3/5.
 */

public class GrabClass {

    public static List<Article> getClass(DefaultHttpClient httpClient, String startUrl) throws Exception {
        List<Article> ret = new ArrayList<>();
        Log.d("Url :", startUrl);
        String html = getHtml(httpClient, startUrl);
        String nextPageUrl = HtmlProcessor.getNextPage(html);
        int page_num = 0;
         do {
            List<String> titles = HtmlProcessor.getArticlesTitle(html);
            List<String> urls = HtmlProcessor.getArticlesUrl(html);
            for (int i = 0; i < titles.size(); ++i) {
                ret.add(new Article(titles.get(i), urls.get(i)));
            }
            html = getHtml(httpClient, startUrl + nextPageUrl);
            nextPageUrl = HtmlProcessor.getNextPage(html);
//            String index = nextPageUrl.substring(nextPageUrl.indexOf("=") + 1);
//            int num = Integer.parseInt(index);
//            if (num > page_num) {
//                page_num = num;
//            }else{
//                break;
//            }
            Log.d("NextUrl", "" + nextPageUrl);
        } while ( !nextPageUrl.equals("") );
        return ret;
    }

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
        // android.util.Log.i("Images", html);
        return html;
    }
}
