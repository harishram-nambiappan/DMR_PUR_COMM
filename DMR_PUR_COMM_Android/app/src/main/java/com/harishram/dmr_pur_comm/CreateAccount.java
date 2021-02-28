package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;

public class CreateAccount extends AppCompatActivity {

    String address,city,state,zip;
    int amount;
    EditText address_text,city_state_text,zip_text,amount_text;
    TextView create_acc;
    DatabaseReference dbr;
    Bundle usr_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();
        address_text = (EditText) findViewById(R.id.editTextTextPersonName6);
        city_state_text = (EditText) findViewById(R.id.editTextTextPersonName7);
        zip_text = (EditText) findViewById(R.id.editTextTextPersonName8);
        amount_text = (EditText) findViewById(R.id.editTextTextPersonName9);
        create_acc = (TextView) findViewById(R.id.textView20);
        usr_data = getIntent().getBundleExtra("usr_det");
        dbr = FirebaseDatabase.getInstance().getReference();
        create_acc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                address = address_text.getText().toString();
                city = city_state_text.getText().toString().split(",")[0];
                state = city_state_text.getText().toString().split(",")[1];
                zip = zip_text.getText().toString();
                amount = Integer.parseInt(amount_text.getText().toString());
                Thread sign_handler = new Thread(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://"+usr_data.getString("Address")+"/dmr_pur_comm_server/create_account.php");
                            HttpURLConnection sock = (HttpURLConnection) url.openConnection();
                            sock.setDoOutput(true);
                            sock.setRequestMethod("POST");
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                            String params = "fname="+usr_data.getString("Name").split(" ")[0]+"&lname="+usr_data.getString("Name").split(" ")[1]+"&address="+address+"&city="+city+"&state="+state+"&zip="+zip+"&amount="+amount;
                            bw.write(params);
                            bw.flush();
                            bw.close();
                            BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                            String resp = br.readLine();
                            System.out.println(resp);
                            if(resp.split("/")[0].equals("Success")) {
                                Account acc = new Account(resp.split("/")[1],resp.split("/")[2]);
                                User usr = new User(usr_data.getString("Name"),usr_data.getString("Username"),usr_data.getString("Password"),usr_data.getString("Category"),usr_data.getString("Address"),acc);
                                dbr.child("Users").child(usr_data.getString("Username")).setValue(usr);
                                dbr.child("Users").child(usr_data.getString("Username")).child("Account").setValue(acc);
                                if(usr_data.getString("Category").equals("Community")){
                                    Community comm = new Community(usr_data.getString("Name"),usr_data.getString("Username"));
                                    dbr.child("Community").child(usr_data.getString("Username")).setValue(comm);
                                }
                                else if(usr_data.getString("Category").equals("Merchant")){
                                    Merchant mer = new Merchant(usr_data.getString("Name"),usr_data.getString("Username"));
                                    dbr.child("Merchant").child(usr_data.getString("Username")).setValue(mer);
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                sign_handler.start();
                Intent login_intent = new Intent(getApplicationContext(),Login.class);
                startActivity(login_intent);
            }
        });

    }

    @Override
    public void onBackPressed(){
        Intent login_intent = new Intent(getApplicationContext(),Login.class);
        startActivity(login_intent);
    }
}