package com.sad490.smartscrape;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sad490 on 1/17/18.
 */

public class InfoSetting extends Activity {

    private EditText name, email, password;
    private Button submit;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_info);

        name = (EditText)findViewById(R.id.setting_name);
        email = (EditText)findViewById(R.id.setting_email);
        password = (EditText)findViewById(R.id.setting_password);
        submit = (Button)findViewById(R.id.btn_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString() == null || email.getText().toString() == null || password.getText().toString() == null){
                    return ;
                }
                Intent intent = new Intent();
                intent.putExtra("Name", name.getText().toString());
                intent.putExtra("Email", email.getText().toString());
                intent.putExtra("Password", password.getText().toString());
                setResult(2, intent);
                finish();
            }
        });
    }
}
