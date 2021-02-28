package com.harishram.dmr_pur_comm;

public class Item {
    String item,cost,seller;
    public Item(){

    }
    public Item(String item,String cost,String seller){
        this.item = item;
        this.cost = cost;
        this.seller = seller;
    }
    public String getItem(){
        return this.item;
    }
    public String getCost(){
        return this.cost;
    }
    public String getSeller(){
        return this.seller;
    }
}
