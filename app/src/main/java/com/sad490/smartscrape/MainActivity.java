package com.sad490.smartscrape;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.MediaRouter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.sad490.smartscrape.DataStorage.SaveData;
import com.sad490.smartscrape.Posters.PostersFragment;
import com.sad490.smartscrape.Recommand.RecommandFragment;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;
import com.sad490.smartscrape.StaticFragment.StaticFragment;
import com.sad490.smartscrape.UserInfo.UserFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        UserFragment.OnFragmentInteractionListener,
        RecommandFragment.OnRecommandPageListener,
        PostersFragment.OnPostersListener,
        StaticFragment.OnStaticListener
{

    private android.support.design.widget.TabLayout tabLayout;
    private SlideDisabledViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private UserData userData;
    private static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        sharedPreferences = getSharedPreferences("SmartScrape", 0);
        //todo ::
        userData = getIntent().getParcelableExtra("UserData");
        Log.d("UserData", userData.toString());
        // TODO : Here is the Bug !!!!!!!!!!! tabLayout already Delete !!!!!!!!!
        // tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (SlideDisabledViewPager) findViewById(R.id.pager);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        BottomAdapter adapter = new BottomAdapter(getSupportFragmentManager(), FLAGS.HOME, userData, getApplicationContext());
        viewPager.setAdapter(adapter);

        // tabLayout.setupWithViewPager(viewPager);

        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       // ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("View Pager : ", "Scrolled .");
            }

            @Override
            public void onPageSelected(int position) {
                int[] ids = {R.id.home, R.id.search, R.id.comput, R.id.poster};
                bottomNavigationView.setSelectedItemId(ids[position]);
                Log.d("Page Selected : ", "Start");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.search:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.comput:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.poster:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onRecommandClick(DummyContent.DummyItem uri){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), BlogViewer.class);
        // todo : intent have to be changed ...
        intent.putExtra("Item_Id", uri.id);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onPosterClick(com.sad490.smartscrape.Posters.dummy.DummyContent.DummyItem uri) {
        Toast.makeText(getApplicationContext(), uri.id, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), PosterDetail.class);
        intent.putExtra("Item_Id", uri.id);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onStaticItemInteraction(com.sad490.smartscrape.StaticFragment.dummy.DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.setting) {
            Intent intent = new Intent();
            intent.setClass(this, InfoSetting.class);
            startActivityForResult(intent, 2);
            String Username = intent.getStringExtra("Name");
            String Email = intent.getStringExtra("Email");
            String Password = intent.getStringExtra("Password");
            ArrayList<String> keys = new ArrayList<>();
            ArrayList<String> values = new ArrayList<>();
            keys.add("Name");   values.add(Username);
            keys.add("Email");   values.add(Email);
            keys.add("Password");   values.add(Password);
            SaveData.SaveSharedPreference(sharedPreferences, keys, values);
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData) {
        switch (aRequestCode) {
            case 2:
                Log.i("Name", aData.getStringExtra("Name").toString());
                break;
            default:
                break;
        }
        super.onActivityResult(aRequestCode, aResultCode, aData);
    }
}
