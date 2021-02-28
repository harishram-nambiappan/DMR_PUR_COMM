package com.harishram.dmr_pur_comm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewItems extends AppCompatActivity {

    LinearLayout ill;
    DatabaseReference dbr;
    Bundle usr_det,bun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);
        getSupportActionBar().hide();
        bun = getIntent().getBundleExtra("usr_det");
        ill = (LinearLayout) findViewById(R.id.item_ll);
        dbr = FirebaseDatabase.getInstance().getReference();
        dbr.child("Items").child(bun.getString("Type")).addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                try {
                    Item item = snapshot.getValue(Item.class);
                    TextView itv = new TextView(getApplicationContext());
                    itv.setTypeface(null, Typeface.BOLD);
                    itv.setTextSize(18f);
                    itv.setText(item.getItem() + "\n" + "Seller:" + item.getSeller() + "\n" + "Cost:" + item.getCost());
                    itv.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            usr_det = new Bundle();
                            usr_det.putString("Name", bun.getString("Name"));
                            usr_det.putString("Username", bun.getString("Username"));
                            usr_det.putString("Category", bun.getString("Category"));
                            usr_det.putString("Type", bun.getString("Type"));
                            usr_det.putString("Item", item.getItem());
                            usr_det.putString("Seller", item.getSeller());
                            usr_det.putString("Cost", item.getCost());
                            Intent purchase_intent = new Intent(getApplicationContext(), ItemPurchase.class);
                            purchase_intent.putExtra("usr_det", usr_det);
                            startActivity(purchase_intent);
                        }
                    });
                    ill.addView(itv);
                }
                catch(Exception e){
                    TextView itv = new TextView(getApplicationContext());
                    itv.setText("No Items Available");
                    ill.addView(itv);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent pur_intent = new Intent(getApplicationContext(), ItemPurchaseMenu.class);
        usr_det = new Bundle();
        usr_det.putString("Name", bun.getString("Name"));
        usr_det.putString("Username", bun.getString("Username"));
        usr_det.putString("Category", bun.getString("Category"));
        pur_intent.putExtra("usr_det",usr_det);
        startActivity(pur_intent);
    }
}