package com.sad490.smartscrape.NetWork;

import android.content.Context;
import android.util.Log;

import com.sad490.smartscrape.GridFragment.Element;
import com.sad490.smartscrape.UserData;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 2018/2/3.
 */

public class User {
    private static HttpCookie cookie;
    private static HttpURLConnection urlConnection;

    private static DefaultHttpClient httpclient;

    /**
     *  This is useless
     * @param context
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    public static boolean Login(Context context, String username, String password) throws Exception {
        URL url = new URL("http://111.230.181.121/login/");
        String csrf = "";
        httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://111.230.181.121/login/");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        for (Cookie cookie : cookies) {
            Log.i("Cookie : ", cookie.toString());
            csrf = getCsrffromCookie(cookie.toString());
            Log.i("Cookie : ", csrf);
        }
        HttpPost httpPost = new HttpPost("http://111.230.181.121/login/");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", username));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("csrfmiddlewaretoken", csrf));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        HttpResponse response1 = httpclient.execute(httpPost);
        Log.i("Response : ", response1.getEntity().getContent().toString());
        // Log.i("Response", response1.getEntity().toString());
        HttpGet httpget2 = new HttpGet("http://111.230.181.121/");

        HttpResponse response2 = httpclient.execute(httpget2);
        int ch;
        InputStream inputStream = response2.getEntity().getContent();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream,"UTF-8"));
        String data = "";
        StringBuilder sb = new StringBuilder();
        while((data = br.readLine()) != null) {
            sb.append(data);
            sb.append("\n");
        }
        String html = sb.toString();
        Log.i("Get2", html);

        return XMLProcessor.haveElement(html, "a");
    }


    public static boolean Signup( String username, String Email, String password ) throws Exception {
        String csrf = "";
        httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://111.230.181.121/reg/");
        HttpResponse response = httpclient.execute(httpget);
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        for (Cookie cookie : cookies) {
            Log.i("Cookie : ", cookie.toString());
            csrf = getCsrffromCookie(cookie.toString());
            Log.i("Cookie : ", csrf);
        }

        HttpPost httpPost = new HttpPost("http://111.230.181.121/reg/");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", username));
        nvps.add(new BasicNameValuePair("email", Email));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("csrfmiddlewaretoken", csrf));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        HttpResponse response1 = httpclient.execute(httpPost);
        Log.i("Response : ", response1.getEntity().getContent().toString());

        HttpGet httpget2 = new HttpGet("http://111.230.181.121/");

        HttpResponse response2 = httpclient.execute(httpget2);
        InputStream inputStream = response2.getEntity().getContent();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream,"UTF-8"));
        String data = "";
        StringBuilder sb = new StringBuilder();
        while((data = br.readLine()) != null) {
            sb.append(data);
            sb.append("\n");
        }
        String html = sb.toString();
        Log.i("Get2", html);

        return XMLProcessor.haveElement(html, "a");
    }

    /**
     * This Function is the main Login Function .
     * @param username Username to Login .
     * @param password Password .
     * @return Is Success ?
     * @throws Exception IOException.
     */
    public static boolean App_Login(String username, String password) throws Exception {
        String csrf = "";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://111.230.181.121/app_login/");
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        List<Cookie> cookies = httpclient.getCookieStore().getCookies();
        for (Cookie cookie : cookies) {
            Log.i("Cookie : ", cookie.toString());
            csrf = getCsrffromCookie(cookie.toString());
            Log.i("Cookie : ", csrf);
        }
        int ch;
        InputStream inputStream = response.getEntity().getContent();
        StringBuilder sb = new StringBuilder();
        while((ch = inputStream.read()) != -1) {
            sb.append((char) ch);
        }
        Log.d("App_Login : ", sb.toString());
        OutputStream stream = System.out;
        List<String> keys = new ArrayList<>();
        keys.add("crsf");
        keys.add("username");
        keys.add("password");
        List<String>values = new ArrayList<String>();
        values.add(csrf);
        values.add("sad490");
        values.add("980515");
        XMLProcessor.genXML(stream, keys, values);
        System.out.println(stream);
        return false;
    }

    /**
     * Get Crsf From Cookies ...
     * @param cookie
     * @return
     */
    private static String getCsrffromCookie( String cookie ) {
        return cookie.substring(cookie.indexOf("value: ") + 7, cookie.indexOf("value: ") + 7 + 64);
    }

    public static UserData getUserData() throws Exception{
        UserData userData;

        HttpGet httpget = new HttpGet("http://111.230.181.121/app_person");
        HttpResponse response = httpclient.execute(httpget);
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
        Log.i("Get2", html);
        userData = XMLProcessor.getUserDatafromXML(html);
        return userData;
    }

    public static DefaultHttpClient getHttpclient() {
        return httpclient;
    }
}
