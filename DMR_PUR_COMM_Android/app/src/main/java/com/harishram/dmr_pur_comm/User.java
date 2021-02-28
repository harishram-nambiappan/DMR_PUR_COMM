package com.harishram.dmr_pur_comm;

public class User {
    String name,username,password,category,address;
    Account acc_info;
    public User(){

    }
    public User(String name,String username,String password,String category,String address,Account acc_info){
        this.name = name;
        this.username = username;
        this.password = password;
        this.category = category;
        this.address = address;
        this.acc_info = acc_info;
    }
    public String getName(){
        return this.name;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getCategory(){
        return this.category;
    }
    public String getAddress(){
        return this.address;
    }
}
