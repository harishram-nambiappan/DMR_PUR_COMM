package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    Bundle usr_det;
    TextView ws,ask_ser,rec_msg,logout,pur_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getSupportActionBar().hide();
        usr_det = getIntent().getBundleExtra("usr_det");
        pur_item = (TextView) findViewById(R.id.textView10);
        ws = (TextView) findViewById(R.id.textView9);
        ask_ser = (TextView) findViewById(R.id.textView12);
        rec_msg = (TextView) findViewById(R.id.textView14);
        pur_item.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent purchase_intent = new Intent(getApplicationContext(),ItemPurchaseMenu.class);
                purchase_intent.putExtra("usr_det",usr_det);
                startActivity(purchase_intent);
            }
        });
        ask_ser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent ask_intent = new Intent(getApplicationContext(),AskForService.class);
                ask_intent.putExtra("usr_det",usr_det);
                startActivity(ask_intent);
            }
        });
        rec_msg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent msg_intent = new Intent(getApplicationContext(),ReceivedMessages.class);
                msg_intent.putExtra("usr_det",usr_det);
                startActivity(msg_intent);
            }
        });
        ws.setText("Welcome "+usr_det.getString("Name"));
        logout = (TextView) findViewById(R.id.lo);
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent login_intent = new Intent(getApplicationContext(),Login.class);
                startActivity(login_intent);
            }
        });

    }
}