package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReplyMessage extends AppCompatActivity {

    EditText reply_text;
    TextView send_reply;
    Bundle usr_det;
    DatabaseReference dbr;
    String reply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_message);
        getSupportActionBar().hide();
        usr_det = getIntent().getBundleExtra("usr_det");
        reply_text = (EditText) findViewById(R.id.editTextTextMultiLine2);
        send_reply = (TextView) findViewById(R.id.textView35);
        dbr = FirebaseDatabase.getInstance().getReference();
        send_reply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                reply = reply_text.getText().toString();
                String noti = usr_det.getString("Notification");
                String recipient = noti.split(" ")[0];
                Date date = new Date();
                SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String dt_entry = dtf.format(date);
                String dt_date = dt_entry.split(" ")[0].split("/")[0]+"_"+dt_entry.split(" ")[0].split("/")[1]+"_"+dt_entry.split(" ")[0].split("/")[2];
                String dt_string = dt_date+"_"+dt_entry.split(" ")[1];
                Message message = new Message(usr_det.getString("Username"),reply,dt_entry);
                dbr.child("Community").child(recipient).child("Messages").child(dt_string).setValue(message);
                Bundle bun = new Bundle();
                bun.putString("Name",usr_det.getString("Name"));
                bun.putString("Username",usr_det.getString("Username"));
                bun.putString("Category",usr_det.getString("Category"));
                Intent msg_intent = new Intent(getApplicationContext(),ReceivedMessages.class);
                msg_intent.putExtra("usr_det",bun);
                startActivity(msg_intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent msg_intent = new Intent(getApplicationContext(),ReceivedMessages.class);
        Bundle bun = new Bundle();
        bun.putString("Name",usr_det.getString("Name"));
        bun.putString("Username",usr_det.getString("Username"));
        bun.putString("Category",usr_det.getString("Category"));
        msg_intent.putExtra("usr_det",bun);
        startActivity(msg_intent);
    }
}