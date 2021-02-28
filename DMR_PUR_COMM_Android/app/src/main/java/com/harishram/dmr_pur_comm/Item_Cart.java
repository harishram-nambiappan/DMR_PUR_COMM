package com.harishram.dmr_pur_comm;

public class Item_Cart {
    float cost;
    String seller;
    public Item_Cart(){
        this.cost = 0;
        this.seller = "";
    }
    public void addValue(float val){
        this.cost += val;
    }
    public void addSeller(String seller){
        this.seller = seller;
    }
    public float getValue(){
        return this.cost;
    }
    public String getSeller(){
        return this.seller;
    }
}
