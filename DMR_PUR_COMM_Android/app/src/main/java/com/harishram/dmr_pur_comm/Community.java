package com.harishram.dmr_pur_comm;

public class Community {
    String name,username;
    public Community(){

    }
    public Community(String name,String username){
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
