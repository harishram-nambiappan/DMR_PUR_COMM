package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

public class MenuMerchant extends AppCompatActivity {

    TextView logout;
    Bundle usr_det;
    TextView ws,add_items,view_msg,view_orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_merchant);
        getSupportActionBar().hide();
        usr_det = getIntent().getBundleExtra("usr_det");
        ws = (TextView)findViewById(R.id.textView4);
        ws.setText("Welcome "+usr_det.getString("Name"));
        add_items = (TextView) findViewById(R.id.textView8);
        view_msg = (TextView) findViewById(R.id.textView13);
        view_orders = (TextView) findViewById(R.id.textView11);
        add_items.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent add_intent = new Intent(getApplicationContext(),AddItems.class);
                add_intent.putExtra("usr_det",usr_det);
                startActivity(add_intent);
            }
        });
        view_msg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent view_intent = new Intent(getApplicationContext(),ReceivedMessages.class);
                view_intent.putExtra("usr_det",usr_det);
                startActivity(view_intent);
            }
        });
        view_orders.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent view_intent = new Intent(getApplicationContext(),ViewOrder.class);
                view_intent.putExtra("usr_det",usr_det);
                startActivity(view_intent);
            }
        });
        logout = (TextView) findViewById(R.id.textView16);
        logout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent login_intent = new Intent(getApplicationContext(),Login.class);
                startActivity(login_intent);
            }
        });

    }
}