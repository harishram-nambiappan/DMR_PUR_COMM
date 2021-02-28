package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView signin, register;
    Login_Handler lh;
    EditText username_text,password_text;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        username_text = (EditText) findViewById(R.id.editTextTextPersonName);
        password_text = (EditText) findViewById(R.id.editTextTextPersonName2);
        register = (TextView) findViewById(R.id.reg);
        signin = (TextView) findViewById(R.id.textView3);
        signin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                username = username_text.getText().toString();
                password = password_text.getText().toString();
                lh = new Login_Handler(getApplicationContext());
                lh.execute(username,password);
            }
        });
        register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent sign_up_intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(sign_up_intent);
            }
        });
    }
}