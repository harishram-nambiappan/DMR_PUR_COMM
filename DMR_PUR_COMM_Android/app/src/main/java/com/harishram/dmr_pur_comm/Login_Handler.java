package com.harishram.dmr_pur_comm;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Handler extends AsyncTask<String,Void,Void> {
    Context context;
    DatabaseReference dbr;
    public Login_Handler(Context context){
        this.context=context;
    }
    @Override
    protected Void doInBackground(String... strings) {
        dbr = FirebaseDatabase.getInstance().getReference();
        dbr.child("Users").child(strings[0]).addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    User user = snapshot.getValue(User.class);
                    if(user.getPassword().equals(strings[1])){
                        Bundle usr_det = new Bundle();
                        Intent menu_intent = null;
                        usr_det.putString("Username",user.getUsername());
                        usr_det.putString("Name",user.getName());
                        usr_det.putString("Category",user.getCategory());
                        if(user.getCategory().equals("Community")){
                            menu_intent = new Intent(context,Menu.class);
                            menu_intent.putExtra("usr_det",usr_det);
                        }
                        else if(user.getCategory().equals("Merchant")){
                            menu_intent = new Intent(context,MenuMerchant.class);
                            menu_intent.putExtra("usr_det",usr_det);
                        }
                        context.startActivity(menu_intent);
                    }
                }catch(Exception e){
                    Toast error = Toast.makeText(context,"Invalid Credentials. Try Again",Toast.LENGTH_SHORT);
                    error.show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return null;
    }
}
