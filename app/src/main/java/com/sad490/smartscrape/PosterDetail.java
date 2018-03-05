package com.sad490.smartscrape;

import android.app.Activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.sad490.smartscrape.GridFragment.Element;
import com.sad490.smartscrape.GridFragment.Grid;
import com.sad490.smartscrape.GridFragment.GridFragment;
import com.sad490.smartscrape.NetWork.Article;
import com.sad490.smartscrape.NetWork.Log;
import com.sad490.smartscrape.NetWork.Tag;
import com.sad490.smartscrape.Posters.Posters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 2/14/18.
 */

public class PosterDetail extends BaseActivity implements GridFragment.OnGridItemClickListener
{

    FrameLayout container;
    private ImageView poster = null;
    private static Toolbar toolbar = null;

    private static String poster_url = "";

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_posterdetail);

        container = (FrameLayout)findViewById(R.id.container);
        poster = (ImageView) findViewById(R.id.poster);
        toolbar = (Toolbar)findViewById(R.id.toolbar);

        GridFragment gridFragment;
        gridFragment = new GridFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, gridFragment).commit();

        Intent intent = getIntent();
        // Tag tag = intent.getParcelableExtra("Item_Tag");
        ArrayList<Article> articles = intent.getParcelableArrayListExtra("Articles");
        poster_url = intent.getStringExtra("Poster_url");
        String title = intent.getStringExtra("title");
        toolbar.setTitle(title);

        Glide.with(this).load(poster_url).centerCrop().into(poster);

        List<Grid> grids = new ArrayList<>();
        for (Article article : articles) {
            Log.d("Article :::", article.getTitle());
            grids.add(new Grid(article));
        }
        gridFragment.adapter.setItems(grids);
        gridFragment.adapter.notifyDataSetChanged();
        // Log.i("Get Tag: ", tag.toString());
    }

    @Override
    public void onGridItemClick(Grid item){
        Log.d("Clicked : ", item.toString());
        Toast.makeText(getApplicationContext(), item.getArticle().getUrl(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), BlogViewer.class);
        intent.putExtra("Item_Id", item.getArticle().getTitle());
        intent.putExtra("title", item.getArticle().getTitle());
        intent.putExtra("blogurl", item.getArticle().getUrl());
        intent.putExtra("imageurl", poster_url);
        startActivity(intent);
    }

}
