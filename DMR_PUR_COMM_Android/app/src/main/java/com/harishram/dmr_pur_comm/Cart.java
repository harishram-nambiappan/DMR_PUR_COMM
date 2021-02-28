package com.harishram.dmr_pur_comm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cart extends AppCompatActivity {

    DatabaseReference dbr;
    Bundle usr_det;
    float cost = 0;
    Item_Cart ic;
    LinearLayout cll;
    TextView cost_text,pur_items;
    String acc_id,sell_acc_id,seller;
    String items,resp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();
        ic = new Item_Cart();
        seller="";
        items = "";
        resp = "";
        cost_text = (TextView) findViewById(R.id.textView40);
        pur_items = (TextView) findViewById(R.id.textView41);
        usr_det = getIntent().getBundleExtra("usr_det");
        cll = (LinearLayout) findViewById(R.id.cart_ll);
        dbr = FirebaseDatabase.getInstance().getReference();
        dbr.child("Community").child(usr_det.getString("Username")).child("Item Cart").addChildEventListener(new ChildEventListener(){

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Item item = snapshot.getValue(Item.class);
                TextView itd = new TextView(getApplicationContext());
                itd.setText(item.getItem()+"\n"+item.getSeller()+"\n"+item.getCost());
                cost += Float.parseFloat(item.getCost());
                ic.addValue(Float.parseFloat(item.getCost()));
                cll.addView(itd);
                seller = item.getSeller();
                ic.addSeller(item.getSeller());
                cost_text.setText("Total Cost: "+ic.getValue());
                System.out.println(ic.getValue());
                if(items.equals("")){
                    items = item.getItem();
                }
                else{
                    items = items + "," +item.getItem();
                }
                dbr.child("Users").child(ic.getSeller().split("-")[1]).child("Account").addValueEventListener(new ValueEventListener(){

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Account account = snapshot.getValue(Account.class);
                        sell_acc_id = account.getAccount_id();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
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
        //System.out.println(ic.getSeller().split("-")[1]);

        //System.out.println(cost);
        //System.out.println("After Listener:"+ic.getValue());
        pur_items.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.out.println(sell_acc_id);
                dbr.child("Users").child(usr_det.getString("Username")).child("Account").addValueEventListener(new ValueEventListener(){

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Account account = snapshot.getValue(Account.class);
                        acc_id = account.getAccount_id();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                Thread trans = new Thread(new Runnable(){

                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://192.168.43.100/dmr_pur_comm_server/money_transfer.php");
                            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                            huc.setDoOutput(true);
                            huc.setRequestMethod("POST");
                            String params = "acc="+acc_id+"&sell="+sell_acc_id+"&amt="+ic.getValue();
                            System.out.println(params);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(huc.getOutputStream()));
                            bw.write(params);
                            bw.flush();
                            bw.close();
                            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
                            resp = br.readLine();
                            System.out.println(resp);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                trans.start();
                try {
                    trans.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(resp.equals("Success")){
                    Date date = new Date();
                    SimpleDateFormat dtf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    String dt_entry = dtf.format(date);
                    String dt_date = dt_entry.split(" ")[0].split("/")[0]+"_"+dt_entry.split(" ")[0].split("/")[1]+"_"+dt_entry.split(" ")[0].split("/")[2];
                    String dt_string = dt_date+"_"+dt_entry.split(" ")[1];
                    Order order = new Order(dt_entry,usr_det.getString("Name")+"-"+usr_det.getString("Username"),items);
                    dbr.child("Merchant").child(ic.getSeller().split("-")[1]).child("Orders").child(dt_string).setValue(order);
                    dbr.child("Community").child(usr_det.getString("Username")).child("Item Cart").setValue(null);
                    Intent pur_intent = new Intent(getApplicationContext(),ItemPurchaseMenu.class);
                    pur_intent.putExtra("usr_det",usr_det);
                    startActivity(pur_intent);
                }
                else{
                    Toast error = Toast.makeText(getApplicationContext(),"Error in purchasing. Try again",Toast.LENGTH_SHORT);
                    error.show();
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent menu_intent = new Intent(getApplicationContext(),ItemPurchaseMenu.class);
        menu_intent.putExtra("usr_det",usr_det);
        startActivity(menu_intent);
    }
}