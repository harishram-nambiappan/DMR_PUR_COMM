package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AskForService extends AppCompatActivity {

    EditText msg_text;
    TextView send_msg;
    String msg;
    DatabaseReference dbr;
    Bundle usr_det;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_service);
        getSupportActionBar().hide();
        dbr = FirebaseDatabase.getInstance().getReference();
        msg_text = (EditText) findViewById(R.id.editTextTextMultiLine);
        send_msg = (TextView) findViewById(R.id.textView31);
        usr_det = getIntent().getBundleExtra("usr_det");
        send_msg.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                msg = msg_text.getText().toString();
                Date date = new Date();
                SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String dt_entry = dtf.format(date);
                String dt_date = dt_entry.split(" ")[0].split("/")[0]+"_"+dt_entry.split(" ")[0].split("/")[1]+"_"+dt_entry.split(" ")[0].split("/")[2];
                String dt_string = dt_date+"_"+dt_entry.split(" ")[1];
                Message message = new Message(usr_det.getString("Username"),msg,dt_entry);
                dbr.child("Merchant").child("Messages").child(dt_string).setValue(message);
                Intent login_intent = new Intent(getApplicationContext(),Menu.class);
                login_intent.putExtra("usr_det",usr_det);
                startActivity(login_intent);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent menu_intent = new Intent(getApplicationContext(),Menu.class);
        menu_intent.putExtra("usr_det",usr_det);
        startActivity(menu_intent);
    }
}