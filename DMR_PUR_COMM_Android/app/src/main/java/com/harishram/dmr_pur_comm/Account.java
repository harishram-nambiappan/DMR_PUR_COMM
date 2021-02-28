package com.harishram.dmr_pur_comm;

public class Account {
    String account_id,customer_id;
    public Account(){

    }
    public Account(String customer_id,String account_id){
        this.account_id = account_id;
        this.customer_id = customer_id;
    }
    public String getAccount_id(){
        return this.account_id;
    }
    public String getCustomer_id(){
        return this.customer_id;
    }
}
