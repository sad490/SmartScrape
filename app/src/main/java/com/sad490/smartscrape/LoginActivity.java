package com.sad490.smartscrape;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sad490.smartscrape.NetWork.User;

/**
 * Created by sad490 on 1/15/18.
 *
 */

public class LoginActivity extends Activity {

    private Button login ;
    private EditText name, pwd_text;
    private TextView create_account;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);

        login = (Button)findViewById(R.id.btn_login);
        name = (EditText)findViewById(R.id.input_email);
        pwd_text = (EditText)findViewById(R.id.input_password);
        create_account = (TextView)findViewById(R.id.link_signup);

        init();
    }

    public void init() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = name.getText().toString();
                String pwd = pwd_text.getText().toString();

                if (CheckPwd(user_name, pwd)) {
                    Log.d("Check pwd","Success");
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Log.d("Check pwd", "Fail");
                }

            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    /**
     * Login Thread .
     */
    Runnable Login = new Runnable() {
        @Override
        public void run() {
            try {
                // User.Login(getApplicationContext(), "sad490", "980515");
                User.App_Login("sad490", "980515");
            }catch (Exception e) {
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
        if (name.equals("a") && pwd.equals("a")){
            return true;
        }
        return false;
    }
}
