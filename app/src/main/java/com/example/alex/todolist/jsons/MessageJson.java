package com.example.alex.todolist.jsons;

/**
 * Created by Alex on 11.06.2016.
 */
public class MessageJson {
    private int id;
    private int idFrom;
    private String messageString;

    public MessageJson(){}

    public MessageJson(int id, int idFrom, String messageString) {
        this.id = id;
        this.idFrom = idFrom;
        this.messageString = messageString;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }
}
