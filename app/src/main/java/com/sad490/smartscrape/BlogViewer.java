package com.sad490.smartscrape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.sad490.smartscrape.NetWork.GrabArticle;
import com.sad490.smartscrape.NetWork.User;
import com.sad490.smartscrape.Recommand.RecItem;
import com.sad490.smartscrape.Recommand.RecommandFragment;

import org.w3c.dom.Text;

import me.drakeet.multitype.Items;

/**
 * Created by sad490 on 2/16/18.
 */

public class BlogViewer extends BaseActivity {

    TextView content;

    private ImageView poster = null;
    private Toolbar toolbar = null;

    private static String blogurl = "";

    private static final int LOAD_ARTICLE_FINISHED = 1;

    private String BlogContent = "";

    private static String poster_url = "";

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_blogviewer);

        content = (TextView)findViewById(R.id.blogcontent);
        poster = (ImageView)findViewById(R.id.poster);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        content.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        String mTitle = intent.getStringExtra("title");
        toolbar.setTitle(mTitle);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));

        // todo : you need a get data function .
        blogurl = intent.getStringExtra("blogurl");
        poster_url = intent.getStringExtra("imageurl");
        if (poster_url == null) {
            Log.d("Url :", "NULL");
            Glide.with(this).load("http://111.230.181.121/upload/pub/%E8%88%AA%E6%B5%B7%E5%AD%A6%E9%99%A2_gaitubao_com_243x243.jpg").centerCrop().into(poster);
        }else {
            Glide.with(this).load(poster_url).centerCrop().into(poster);
        }

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
