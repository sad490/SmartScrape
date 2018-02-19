package com.sad490.smartscrape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by sad490 on 2/16/18.
 */

public class BlogViewer extends Activity {

    TextView title;
    TextView content;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_blogviewer);

        title = (TextView)findViewById(R.id.blogtitle);
        content = (TextView)findViewById(R.id.blogcontent);

        Intent intent = getIntent();

        // todo : you need a get data function .
        String blogurl = intent.getStringExtra("blogurl");
    }
}
