package com.harishram.dmr_pur_comm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewOrder extends AppCompatActivity {

    DatabaseReference dbr;
    Bundle usr_det;
    LinearLayout oll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);
        getSupportActionBar().hide();
        usr_det = getIntent().getBundleExtra("usr_det");
        dbr = FirebaseDatabase.getInstance().getReference();
        oll = (LinearLayout) findViewById(R.id.order_ll);
        dbr.child("Merchant").child(usr_det.getString("Username")).child("Orders").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Order order = snapshot.getValue(Order.class);
                TextView ort = new TextView(getApplicationContext());
                ort.setTypeface(null, Typeface.BOLD);
                ort.setTextSize(18f);
                ort.setText(order.getDate()+"\n"+order.getItems()+"\n"+order.getBuyer());
                oll.addView(ort);
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
        Intent login_intent = new Intent(getApplicationContext(),MenuMerchant.class);
        login_intent.putExtra("usr_det",getIntent().getBundleExtra("usr_det"));
        startActivity(login_intent);
    }
}