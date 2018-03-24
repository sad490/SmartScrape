package com.sad490.smartscrape.NetWork;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sad490.smartscrape.Posters.Poster_element;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sad490 on 3/4/18.
 */

public class GrabImage {

    public static List<Poster_element> grubPostersHeaders(DefaultHttpClient httpClient, String Url) throws Exception {
        String html = getHtml(httpClient, Url);
        return HtmlProcessor.getPostersElements(html);
    };


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
        android.util.Log.i("Images", html);
        return html;
    }

    public static Bitmap getImage(DefaultHttpClient httpclient, String url)
    {
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse resp = httpclient.execute(httpget);
            // 判断是否正确执行
            if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
                // 将返回内容转换为bitmap
                HttpEntity entity = resp.getEntity();
                InputStream in = entity.getContent();
                Bitmap mBitmap = BitmapFactory.decodeStream(in);
                // 向handler发送消息，执行显示图片操作
                return mBitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            httpget.abort();
        }

        return null;
    }
}
