package com.harishram.dmr_pur_comm;

public class Merchant {
    String name,username;
    public Merchant(){

    }
    public Merchant(String name,String username){
        this.name = name;
        this.username = username;
    }
    public String getName(){
        return this.name;
    }
    public String getUsername(){
        return this.username;
    }
}
