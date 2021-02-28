package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    Bundle usr_det;
    TextView next;
    EditText name_text,username_text,password_text,address_text;
    RadioGroup category_rg;
    RadioButton category_rb;
    String name,username,password,category,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        next = (TextView) findViewById(R.id.next);
        name_text = (EditText) findViewById(R.id.editTextTextPersonName3);
        username_text = (EditText) findViewById(R.id.editTextTextPersonName4);
        password_text = (EditText) findViewById(R.id.editTextTextPersonName5);
        address_text = (EditText) findViewById(R.id.editTextTextPersonName10);
        category_rg = (RadioGroup) findViewById(R.id.Category);
        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                name = name_text.getText().toString();
                username = username_text.getText().toString();
                password = password_text.getText().toString();
                address = address_text.getText().toString();
                category_rb = (RadioButton) findViewById(category_rg.getCheckedRadioButtonId());
                category = category_rb.getText().toString();
                usr_det = new Bundle();
                usr_det.putString("Name",name);
                usr_det.putString("Username",username);
                usr_det.putString("Password",password);
                usr_det.putString("Category",category);
                usr_det.putString("Address",address);
                Intent account_intent = new Intent(getApplicationContext(),CreateAccount.class);
                account_intent.putExtra("usr_det",usr_det);
                startActivity(account_intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent login_intent = new Intent(getApplicationContext(),Login.class);
        startActivity(login_intent);
    }
}