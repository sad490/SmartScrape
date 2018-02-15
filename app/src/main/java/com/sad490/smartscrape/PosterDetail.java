package com.sad490.smartscrape;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sad490.smartscrape.GridFragment.Element;
import com.sad490.smartscrape.GridFragment.GridFragment;
import com.sad490.smartscrape.NetWork.Log;

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

        if (container != null) {
            Fragment gridFragment = new GridFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, gridFragment).commit();
        }

        Intent intent = getIntent();
        String Id = intent.getStringExtra("Item_Id");
    }

    @Override
    public void onGridItemClick(Element.ElementItem item){
        Log.d("Clicked : ", item.id);
        Toast.makeText(getApplicationContext(), item.id, Toast.LENGTH_SHORT).show();
    }
}
