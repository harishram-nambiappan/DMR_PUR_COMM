package com.harishram.dmr_pur_comm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItems extends AppCompatActivity {

    EditText item_text,cost_text;
    RadioGroup item_rg;
    RadioButton item_rb;
    TextView add_item;
    String item,cost,type;
    Bundle usr_det;
    DatabaseReference dbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        usr_det = getIntent().getBundleExtra("usr_det");
        item_text = (EditText) findViewById(R.id.editTextTextPersonName11);
        cost_text = (EditText) findViewById(R.id.editTextNumberDecimal);
        item_rg = (RadioGroup) findViewById(R.id.radioGroup);
        add_item = (TextView) findViewById(R.id.textView27);
        dbr = FirebaseDatabase.getInstance().getReference();
        add_item.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                item = item_text.getText().toString();
                cost = cost_text.getText().toString();
                item_rb = (RadioButton) findViewById(item_rg.getCheckedRadioButtonId());
                type = item_rb.getText().toString();
                Item entry = new Item(item,cost,usr_det.getString("Name")+"-"+usr_det.getString("Username"));
                dbr.child("Items").child(type).child(item).setValue(entry);
                Intent menu_intent = new Intent(getApplicationContext(),MenuMerchant.class);
                menu_intent.putExtra("usr_det",usr_det);
                startActivity(menu_intent);
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