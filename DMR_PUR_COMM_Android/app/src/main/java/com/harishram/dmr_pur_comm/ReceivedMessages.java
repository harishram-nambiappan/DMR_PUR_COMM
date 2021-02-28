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

public class ReceivedMessages extends AppCompatActivity {

    LinearLayout ml;
    DatabaseReference dbr;
    Bundle usr_det;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_received_messages);
        getSupportActionBar().hide();
        usr_det = getIntent().getBundleExtra("usr_det");
        ml = (LinearLayout) findViewById(R.id.rm);
        dbr = FirebaseDatabase.getInstance().getReference();
        if(usr_det.getString("Category").equals("Merchant")){
            dbr.child("Merchant").child("Messages").addChildEventListener(new ChildEventListener(){

                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                      try{
                          Message message = snapshot.getValue(Message.class);
                          TextView msg_text = new TextView(getApplicationContext());
                          msg_text.setTypeface(null, Typeface.BOLD);
                          msg_text.setTextSize(18f);
                          String noti = message.getSender()+" messaged on "+message.getDate()+":"+message.getMsg();
                          msg_text.setText(noti);
                          msg_text.setOnClickListener(new View.OnClickListener(){

                              @Override
                              public void onClick(View v) {
                                  Intent reply_intent = new Intent(getApplicationContext(),ReplyMessage.class);
                                  usr_det.putString("Notification",noti);
                                  reply_intent.putExtra("usr_det",usr_det);
                                  startActivity(reply_intent);
                              }
                          });
                          ml.addView(msg_text);
                      }catch(Exception e){
                          TextView msg_text = new TextView(getApplicationContext());
                          msg_text.setText("No messages available");
                          ml.addView(msg_text);
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
        else if(usr_det.getString("Category").equals("Community")){
            dbr.child("Community").child(usr_det.getString("Username")).child("Messages").addChildEventListener(new ChildEventListener(){

                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    try {
                        Message message = snapshot.getValue(Message.class);
                        TextView msg_text = new TextView(getApplicationContext());
                        msg_text.setTypeface(null, Typeface.BOLD);
                        msg_text.setTextSize(18f);
                        String noti = message.getSender() + " messaged on " + message.getDate() + ": " + message.getMsg();
                        msg_text.setText(noti);
                        ml.addView(msg_text);
                    }
                    catch(Exception e){
                        TextView msg_text = new TextView(getApplicationContext());
                        msg_text.setText("No messages available");
                        ml.addView(msg_text);
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
    }
    @Override
    public void onBackPressed(){
        usr_det = getIntent().getBundleExtra("usr_det");
        if(usr_det.getString("Category").equals("Merchant")) {
            Intent login_intent = new Intent(getApplicationContext(), MenuMerchant.class);
            login_intent.putExtra("usr_det",usr_det);
            startActivity(login_intent);
        }
        else if(usr_det.getString("Category").equals("Community")) {
            Intent login_intent = new Intent(getApplicationContext(), Menu.class);
            login_intent.putExtra("usr_det",usr_det);
            startActivity(login_intent);
        }
    }

}