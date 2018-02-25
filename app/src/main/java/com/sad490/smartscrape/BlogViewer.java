package com.sad490.smartscrape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.sad490.smartscrape.NetWork.GrabArticle;
import com.sad490.smartscrape.NetWork.User;
import com.sad490.smartscrape.Recommand.RecItem;
import com.sad490.smartscrape.Recommand.RecommandFragment;

import org.w3c.dom.Text;

import me.drakeet.multitype.Items;

/**
 * Created by sad490 on 2/16/18.
 */

public class BlogViewer extends Activity {

    TextView title;
    TextView content;

    private static String blogurl = "";

    private static final int LOAD_ARTICLE_FINISHED = 1;

    private String BlogContent = "";

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_blogviewer);

        title = (TextView)findViewById(R.id.blogtitle);
        content = (TextView)findViewById(R.id.blogcontent);
        content.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("title");
        title.setText(mTitle);

        // todo : you need a get data function .
        blogurl = intent.getStringExtra("blogurl");

        new Thread(loadBlog).start();
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch( msg.what ) {
                case LOAD_ARTICLE_FINISHED:
                    Log.d("Load Data Finished", "Finished");
                    content.setText(BlogContent);
            }
        }
    };

    Runnable loadBlog = new Runnable() {
        @Override
        public void run() {
            try {
                BlogContent = GrabArticle.ExtractContent(User.getHttpclient(), blogurl);
                Message message = mHandler.obtainMessage();
                message.what = LOAD_ARTICLE_FINISHED;
                mHandler.sendMessage(message);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
