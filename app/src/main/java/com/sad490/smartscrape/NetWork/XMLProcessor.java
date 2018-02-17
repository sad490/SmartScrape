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
        int eventType = parser.getEventType(); String name = "";
        String version = "";
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
}
