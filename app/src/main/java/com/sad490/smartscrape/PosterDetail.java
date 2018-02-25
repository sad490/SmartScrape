package com.sad490.smartscrape;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sad490.smartscrape.GridFragment.Element;
import com.sad490.smartscrape.GridFragment.Grid;
import com.sad490.smartscrape.GridFragment.GridFragment;
import com.sad490.smartscrape.NetWork.Article;
import com.sad490.smartscrape.NetWork.Log;
import com.sad490.smartscrape.NetWork.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 2/14/18.
 */

public class PosterDetail extends FragmentActivity implements GridFragment.OnGridItemClickListener
{

    FrameLayout container;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_posterdetail);

        container = (FrameLayout)findViewById(R.id.container);
        GridFragment gridFragment;
        gridFragment = new GridFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, gridFragment).commit();

        Intent intent = getIntent();
        String Id = intent.getStringExtra("Item_Id");
        Tag tag = intent.getParcelableExtra("Item_Tag");

        List<Grid> grids = new ArrayList<>();
        for (Article article : tag.articles) {
            grids.add(new Grid(article));
        }
        gridFragment.adapter.setItems(grids);
        gridFragment.adapter.notifyDataSetChanged();
        Log.i("Get Tag: ", tag.toString());
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
        startActivityForResult(intent, 1);
    }
}
