package com.sad490.smartscrape;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by sad490 on 1/15/18.
 */

public class SignupActivity extends Activity {

    private Button signup;
    private EditText name, email;
    private EditText pwd;

    public static String Name, Pwd, Email;
    private boolean canClose;

    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        setContentView(R.layout.activity_signup);
        Pwd = "";
        Name = "";
        Email = "";

        sharedPreferences = getSharedPreferences("Signup", 0);

        canClose = false;
        signup = (Button)findViewById(R.id.signup);
        name = (EditText)findViewById(R.id.your_full_name);
        email = (EditText)findViewById(R.id.your_email_address);
        pwd = (EditText)findViewById(R.id.create_new_password);

        init();
    }

    private void init() {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = name.getText().toString();
                Pwd = pwd.getText().toString();
                Email = email.getText().toString();

                Log.d("Click", "Signup");
                if (!CheckValid()){
                    Toast.makeText(getApplicationContext(), "Please check it !!!", Toast.LENGTH_SHORT).show();
                    return ;
                }

                Intent intent = new Intent();
                intent.putExtra("Name", Name);
                intent.putExtra("Pwd", Pwd);
                intent.putExtra("Email", Email);
                //设置返回数据
                SignupActivity.this.setResult(Activity.RESULT_OK, intent);
                //关闭Activity
                SignupActivity.this.finish();
            }
        });
    }

    private boolean CheckValid() {
        if (Pwd.equals("") || Email.equals("") || Name.equals("")) {
            return false;
        }
        return true;
    }
}
