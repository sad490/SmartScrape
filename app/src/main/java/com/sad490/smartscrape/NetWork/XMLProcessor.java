package com.sad490.smartscrape.NetWork;

import android.util.Xml;
import android.widget.ListView;

import com.sad490.smartscrape.UserData;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by sad490 on 2/5/18.
 */

public class XMLProcessor {

    public static void genXML(OutputStream stream, List<String> Keys, List<String> values) throws IOException{
        XmlSerializer serializer = Xml.newSerializer();
        serializer.setOutput(stream, "UTF-8");
        serializer.startDocument("UTF-8", true);
        for (int i = 0; i < Keys.size(); ++i) {
            serializer.startTag(null, Keys.get(i));
            serializer.text(values.get(i));
            serializer.endTag(null, Keys.get(i));
        }
        serializer.endDocument();
        serializer.flush();
    }
    public static boolean haveElement( String html, String ele ) {
        Document document = Jsoup.parse(html);
        Elements element = document.select(ele);
        List<String> href = element.eachAttr("href");
        List<String> element_list = element.eachText();
        for (String ele_item : href) {
            Log.i("List Item ", ele_item);
            if (ele_item.equals("/login")) {
                return false;
            }
            if (ele_item.equals("/logout")) {
                return true;
            }
        }
        // Log.e("Login Element ", element.toString());
        return false;
    }

    public static UserData getUserDatafromXML( String XML ) throws Exception {
        UserData userData = new UserData();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(XML));
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            String nodeName = parser.getName();
            switch (eventType)
            {
                case XmlPullParser.START_TAG:
                {
                    if ("username".equals(nodeName))
                    {
                        userData.setUsername(parser.nextText());
                    }
                    else if ("email".equals(nodeName))
                    {
                        userData.setEmail( parser.nextText() );
                    }
                    break;
                }
                case XmlPullParser.END_TAG:
                {
                    if ("webpage".equals(nodeName))
                    {
                        // Log.d("MainActivity", "name is " + name);
                        // Log.d("MainActivity", "version is " + version);
                        Log.i("UserData", userData.toString());
                    }
                    break;
                }
                default:
                    break;
            }
            eventType = parser.next();
        }
        return userData;
    }

    public static List<Tag> getTags( String html ) throws Exception {
        List<Tag> tags = new ArrayList<>();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader( html ));
        int eventType = parser.getEventType();

        int flags = 0;
        String tag_name = "";
        String tag_num = "";
        String tag_url = "";
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            String nodeName = parser.getName();



            if ( nodeName != null ) {
                // Log.i("tag_in_XMLProcessor:", nodeName);
                if (nodeName.equals("tag")) {
                    flags = 1;
                }
                if (flags == 1) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG: {
                            if ("tag_name".equals(nodeName)) {
                                tag_name = parser.nextText();
                            } else if ("tag_num".equals(nodeName)) {
                                tag_num = parser.nextText();
                            } else if ("tag_url".equals(nodeName)) {
                                tag_url = parser.nextText();
                            }
                            break;
                        }
                        case XmlPullParser.END_TAG: {
                            if ("tag".equals(nodeName)) {
                                // Log.d("MainActivity", "name is " + name);
                                // Log.d("MainActivity", "version is " + version);
                                flags = 0;
                                tags.add(new Tag(tag_name, tag_num, tag_url));
                                tag_name = "";
                                tag_num = "";
                                tag_url = "";
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
            else {

            }
            eventType = parser.next();
        }
        return tags;
    }

    public static List<Article> getArticle( String html ) throws Exception {
        List<Article> articles = new ArrayList<>();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader( html ));
        int eventType = parser.getEventType();

        int flags = 0;
        Article article = null;
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            String nodeName = parser.getName();

            if ( nodeName != null ) {
                if (nodeName.equals("article") && eventType == XmlPullParser.START_TAG) {
                    Log.i("tag_in_XMLProcessor:", nodeName);
                    article = new Article();
                    flags = 1;
                }
                if (flags == 1) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG: {
                            if ("title".equals(nodeName)) {

                                article.setTitle(parser.nextText());

                            } else if ("date".equals(nodeName)) {

                                article.setDate(parser.nextText());

                            } else if ("publisher".equals(nodeName)) {

                                article.setPublisher( parser.nextText());

                            } else if ("url".equals(nodeName)) {

                                article.setUrl(parser.nextText());

                            }
                            break;
                        }
                        case XmlPullParser.END_TAG: {
                            if ("article".equals(nodeName)) {
                                // Log.d("MainActivity", "name is " + name);
                                // Log.d("MainActivity", "version is " + version);
                                flags = 0;
                                articles.add(article);
                                article = null;
                            }
                            break;
                        }
                        default:
                            break;
                    }
                }
            }
            else {

            }
            eventType = parser.next();
        }

        return articles;
    }

    public static List<String> getHeaderImageUrl( String html ) throws Exception {
        List<String> urls = new ArrayList<>();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader( html ));
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT)
        {
            String nodeName = parser.getName();
            if (nodeName == null || !nodeName.equals("img")) {
                Log.d("NodeName", "" + nodeName);
                eventType = parser.next();
                continue;
            }
            if ( nodeName != null ) {
                switch (eventType) {
                    case XmlPullParser.START_TAG: {

                        if ("img".equals(nodeName)) {
                            urls.add(parser.getAttributeValue(null, "src"));
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        break;
                    }
                    default:
                        break;
                }
            }
            eventType = parser.next();
        }

        return urls;
    }

    public static String getArticleContent( String html ) throws Exception {
        String content = "";
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(html));
        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String nodeName = parser.getName();

            if (nodeName != null) {
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("content".equals(nodeName)) {
                            content = parser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("content".equals(nodeName)) {
                            // Log.d("MainActivity", "name is " + name);
                            // Log.d("MainActivity", "version is " + version);
                        }
                        break;
                    }
                    default:
                        break;
                }
            } else {

            }
            eventType = parser.next();
        }
        return content;
    }
}
