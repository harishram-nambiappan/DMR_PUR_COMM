package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemPurchaseMenu extends AppCompatActivity {

    TextView precaution,post_rec,cart;
    String type;
    Bundle usr_det;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_purchase_menu);
        getSupportActionBar().hide();
        usr_det = getIntent().getBundleExtra("usr_det");
        precaution = (TextView) findViewById(R.id.textView22);
        post_rec = (TextView) findViewById(R.id.textView23);
        cart = (TextView) findViewById(R.id.textView24);
        precaution.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                type = "Precautionary Item";
                usr_det.putString("Type",type);
                Intent view_intent = new Intent(getApplicationContext(),ViewItems.class);
                view_intent.putExtra("usr_det",usr_det);
                startActivity(view_intent);
            }
        });
        post_rec.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                type = "Post Recovery Item";
                usr_det.putString("Type",type);
                Intent view_intent = new Intent(getApplicationContext(),ViewItems.class);
                view_intent.putExtra("usr_det",usr_det);
                startActivity(view_intent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent view_intent = new Intent(getApplicationContext(),Cart.class);
                view_intent.putExtra("usr_det",usr_det);
                startActivity(view_intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent login_intent = new Intent(getApplicationContext(),Menu.class);
        login_intent.putExtra("usr_det",getIntent().getBundleExtra("usr_det"));
        startActivity(login_intent);
    }
}