package com.sad490.smartscrape.NetWork;

import android.util.Xml;
import android.widget.ListView;

import com.sad490.smartscrape.UserData;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
}
