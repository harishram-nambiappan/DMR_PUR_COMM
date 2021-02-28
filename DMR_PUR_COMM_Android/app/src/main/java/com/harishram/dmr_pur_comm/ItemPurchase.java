package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ItemPurchase extends AppCompatActivity {

    TextView item_text;
    Bundle bun,usr_det;
    TextView add_cart;
    DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_purchase);
        getSupportActionBar().hide();
        bun = getIntent().getBundleExtra("usr_det");
        item_text = (TextView) findViewById(R.id.textView37);
        dbr = FirebaseDatabase.getInstance().getReference();
        item_text.setText("Want to add item to cart?"+"\n"+bun.getString("Item")+"\n"+bun.getString("Seller")+"\n"+bun.getString("Cost"));
        add_cart = (TextView) findViewById(R.id.textView38);
        add_cart.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Item entry = new Item(bun.getString("Item"),bun.getString("Cost"),bun.getString("Seller"));
                dbr.child("Community").child(bun.getString("Username")).child("Item Cart").child(bun.getString("Item")).setValue(entry);
                usr_det = new Bundle();
                usr_det.putString("Name", bun.getString("Name"));
                usr_det.putString("Username", bun.getString("Username"));
                usr_det.putString("Category", bun.getString("Category"));
                usr_det.putString("Type", bun.getString("Type"));
                Intent view_intent = new Intent(getApplicationContext(),ViewItems.class);
                view_intent.putExtra("usr_det",usr_det);
                startActivity(view_intent);
            }
        });

    }
    @Override
    public void onBackPressed(){
        bun = getIntent().getBundleExtra("usr_det");
        usr_det = new Bundle();
        usr_det.putString("Name", bun.getString("Name"));
        usr_det.putString("Username", bun.getString("Username"));
        usr_det.putString("Category", bun.getString("Category"));
        usr_det.putString("Type", bun.getString("Type"));
        Intent view_intent = new Intent(getApplicationContext(),ViewItems.class);
        view_intent.putExtra("usr_det",usr_det);
        startActivity(view_intent);
    }
}