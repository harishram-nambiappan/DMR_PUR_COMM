package com.harishram.dmr_pur_comm;

public class Message {
    String sender,msg,date;
    public Message(){

    }
    public Message(String sender,String msg,String date){
        this.sender = sender;
        this.msg = msg;
        this.date = date;
    }
    public String getSender(){
        return this.sender;
    }
    public String getMsg(){
        return this.msg;
    }
    public String getDate(){
        return this.date;
    }
}
