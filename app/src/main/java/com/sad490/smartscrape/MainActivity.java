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
import android.os.Handler;
import android.os.Message;
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
import com.sad490.smartscrape.NetWork.Article;
import com.sad490.smartscrape.NetWork.GetRecommand;
import com.sad490.smartscrape.NetWork.GrabClass;
import com.sad490.smartscrape.NetWork.Tag;
import com.sad490.smartscrape.NetWork.User;
import com.sad490.smartscrape.Posters.Posters;
import com.sad490.smartscrape.Posters.PostersFragment;
import com.sad490.smartscrape.Recommand.FollowingFragment;
import com.sad490.smartscrape.Recommand.RecItem;
import com.sad490.smartscrape.Recommand.RecommandFragment;
import com.sad490.smartscrape.Recommand.StarredFragment;
import com.sad490.smartscrape.Recommand.dummy.DummyContent;
import com.sad490.smartscrape.StaticFragment.StaticFragment;
import com.sad490.smartscrape.UserInfo.UserFragment;

import org.apache.http.impl.client.DefaultHttpClient;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.Items;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        UserFragment.OnUserFragmentListener,
        RecommandFragment.OnRecommandPageListener,
        PostersFragment.OnPostersListener,
        StaticFragment.OnStaticListener,
        StarredFragment.OnStareedPageListener,
        FollowingFragment.OnFollowingPageListener
{

    private android.support.design.widget.TabLayout tabLayout;
    private SlideDisabledViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private LoadingDialog dialog;
    private UserData userData;
    private List<Tag> tags = new ArrayList<>();
    private static SharedPreferences sharedPreferences;

    private static String class_url_to_load = "";
    private static String class_image_to_load = "";
    private static String class_title = "";

    private static ArrayList<Article> articles = new ArrayList<>();
    private static List<Posters> starred_posters = new ArrayList<>();

    private static final int Load_Data_finished = 1;
    private static final int Load_Detail_Data_finished = 2;
    private static final int Load_Starred_Data_finished = 3;

    private static final String GenerHost = "http://111.230.181.121";
    // private static final int Load_Data_ = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        sharedPreferences = getSharedPreferences("SmartScrape", 0);
        //todo ::
        userData = getIntent().getParcelableExtra("UserData");
        // userData = new UserData("sad490","980515","1049154785@qq.com");
        Log.d("UserData", userData.toString());

        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        keys.add("Name");
        keys.add("Email");
        keys.add("Password");
        values.add(userData.getUsername());
        values.add(userData.getEmail());
        values.add(userData.getPassword());
        SaveData.SaveSharedPreference(sharedPreferences, keys, values);

        // TODO : Here is the Bug !!!!!!!!!!! tabLayout already Delete !!!!!!!!!
        // tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (SlideDisabledViewPager) findViewById(R.id.pager);
        navigationView = (NavigationView)findViewById(R.id.nav_view);
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        dialog = new LoadingDialog(this);
        dialog.setCanceledOnTouchOutside(false);
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
                int[] ids = {R.id.home, R.id.poster,R.id.comput,R.id.search};
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
                    case R.id.poster:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.comput:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.search:
                        viewPager.setCurrentItem(3);
                        break;

                }
                return true;
            }
        });
        dialog.show();
        new Thread(Load_data).start();
        // todo : Adjust the Client used by Thread .
        // new Thread(Load_starred).start();
    }

    Runnable Load_data = new Runnable() {
        @Override
        public void run() {
            try {
                DefaultHttpClient client = null;
                synchronized (this) {
                    client = User.getHttpclient();
                }
                tags = GetRecommand.GetTitleAndArticle(client);
                Message message = mHandler.obtainMessage();
                message.what = Load_Data_finished;
                mHandler.sendMessage(message);
            }catch (Exception e) {
                new Thread(Load_data).start();
                e.printStackTrace();
            }
        }
    };

    Runnable Load_class = new Runnable() {
        @Override
        public void run() {
            try {
                List<Article> _articles = GrabClass.getClass(User.getHttpclient(), GenerHost + class_url_to_load);
                articles.clear();
                for (Article article : _articles) {
                    Log.d("Article : ", article.getTitle());
                    articles.add(article);
                }
                Message message = mHandler.obtainMessage();
                message.what = Load_Detail_Data_finished;
                mHandler.sendMessage(message);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch( msg.what ) {
                case Load_Data_finished:
                    dialog.dismiss();
                    Log.d("Load Data Finished", "Finished");
                    Items items = new Items();
                    List<String> Tags = new ArrayList<>();
                    for (int j = 0; j < tags.size(); ++j) {
                        for (int i = 0; i < tags.get(j).articles.size(); ++i) {
                            items.add(new RecItem(tags.get(j).getArticles().get(i)));
                        }
                        Tags.add(tags.get(j).getTag_name());
                        RecommandFragment.adapter.setItems(items);
                        RecommandFragment.adapter.notifyDataSetChanged();
                    }
                    RecommandFragment.setTags(Tags);
                    break;
                case Load_Detail_Data_finished:
                    dialog.dismiss();
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), PosterDetail.class);
                    intent.putParcelableArrayListExtra("Articles", articles);
                    intent.putExtra("Poster_url", class_image_to_load);
                    intent.putExtra("title", class_title);
                    startActivityForResult(intent, 1);
                    break;
            }
        }
    };

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


    public void onRecommandClick(RecItem uri){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), BlogViewer.class);
        // todo : intent have to be changed ...
        Log.d("To Send", uri.article.toString());
        intent.putExtra("title", uri.article.getTitle());
        intent.putExtra("blogurl", uri.article.getUrl());
        startActivityForResult(intent, 1);
    }

    @Override
    public void onPosterClick(Posters uri) {
        Toast.makeText(getApplicationContext(), uri.getName(), Toast.LENGTH_SHORT).show();
        class_url_to_load = uri.getContent_url();
        class_image_to_load = uri.getImage_url();
        class_title = uri.getName();
        dialog.show();
        new Thread(Load_class).start();
//        Toast.makeText(getApplicationContext(), uri.id, Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent();
//        intent.setClass(getApplicationContext(), PosterDetail.class);
//        intent.putExtra("Item_Id", uri.id);
//        startActivityForResult(intent, 1);
    }

    @Override
    public void onStarredClick(Posters item){
        Log.d("Starred : ", "" + item.getName());
        class_url_to_load = item.getContent_url();
        class_image_to_load = item.getImage_url();
        class_title = item.getName();
        dialog.show();
        new Thread(Load_class).start();
    }

    @Override
    public void onUserFragmentInteraction(com.sad490.smartscrape.UserInfo.User user) {

    }


    @Override
    public void onFollowingClick(RecItem item){
        Toast.makeText(this, "" + item.article.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStaticItemInteraction(com.sad490.smartscrape.StaticFragment.dummy.DummyContent.DummyItem item) {

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
