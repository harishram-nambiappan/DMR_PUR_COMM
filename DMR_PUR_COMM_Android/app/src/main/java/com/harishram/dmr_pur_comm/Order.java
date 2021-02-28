package com.harishram.dmr_pur_comm;

public class Order {
    String date,buyer,items;
    public Order(){

    }
    public Order(String date,String buyer,String items){
        this.date = date;
        this.buyer = buyer;
        this.items = items;
    }
    public String getDate(){
        return this.date;
    }
    public String getBuyer(){
        return this.buyer;
    }
    public String getItems(){
        return this.items;
    }
}
