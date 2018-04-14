package com.sad490.smartscrape;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_splash);
        Intent intent = new Intent(this, LoginActivity.class);
        /// intent.putExtra("UserData", new UserData("sad490", "980515", "1049154785@qq.com"));
        startActivity(intent);
        finish();
    }
}
