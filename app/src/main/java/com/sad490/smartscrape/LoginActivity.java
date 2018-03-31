package com.sad490.smartscrape;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sad490.smartscrape.DataStorage.ExtractData;
import com.sad490.smartscrape.NetWork.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sad490 on 1/15/18.
 *
 */

public class LoginActivity extends Activity {

    private Button login ;
    private EditText name, pwd_text;
    private TextView create_account;

    private static boolean Logined = false;

    private static final int Login_Successed = 0;
    private static final int Login_Failed = -1;

    private static SharedPreferences sharedPreferences;

    public static UserData userData = new UserData();

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.btn_login);
        name = (EditText)findViewById(R.id.input_email);
        pwd_text = (EditText)findViewById(R.id.input_password);
        create_account = (TextView)findViewById(R.id.link_signup);

        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();
        sharedPreferences = getSharedPreferences("SmartScrape", 0);
        if (sharedPreferences.contains("Name")) {
            ExtractData.ExtractSharedPreference(sharedPreferences, keys, values);

            name.setText(values.get(2));
            pwd_text.setText(values.get(1));
        }

        // todo : donot forget delete it .
//        pwd_text.setText("980515");
//        name.setText("sad490");

        init();
    }

    public void init() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = name.getText().toString();
                String pwd = pwd_text.getText().toString();
                CheckPwd(user_name, pwd);
                // todo : Change it
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Login_Successed:
                    Log.d("名字","Success");
                    Intent intent = new Intent();
                    intent.putExtra("UserData", userData);
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case Login_Failed:
                    Log.d("Check pwd", "Fail");
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }

        }
    };

    /**
     * Login Thread .
     */
    Runnable Login = new Runnable() {
        @Override
        public void run() {
            try {
                Logined = User.Login(getApplicationContext(), name.getText().toString(), pwd_text.getText().toString());
                // User.App_Login("sad490", "980515");
                userData = User.getUserData();
                userData.setPassword(pwd_text.getText().toString());
                if ( Logined ) {
                    Message message = mHandler.obtainMessage();
                    message.what = Login_Successed;
                    mHandler.sendMessage(message);
                }
            }catch (Exception e) {
                Logined = false;
                // Toast.makeText(getApplicationContext(), "Network Error !!!", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String NAME=data.getStringExtra("Name");
                String PWD=data.getStringExtra("Pwd");
                String EMAIL=data.getStringExtra("Email");
                Log.v("Scanner Result : ", NAME);
                name.setText(NAME);
                pwd_text.setText(PWD);
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
            else{
                Log.e("Error", "No Data receive");
            }
        }
        else{
            Log.e("Error", "No Data ");
        }
    }//onActivityResult

    private boolean CheckPwd(String name, String pwd) {
        // TODO
        /*
        UserData userData = new UserData(name, pwd);
        if ( User.Login(userData) ) {
            return true;
        }*/
        new Thread(Login).start();
        if ( Logined ){
            return true;
        }
        return false;
    }
}
