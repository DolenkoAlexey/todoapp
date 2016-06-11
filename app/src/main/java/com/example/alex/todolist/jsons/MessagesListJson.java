package com.example.alex.todolist.jsons;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alex on 11.06.2016.
 */
public class MessagesListJson implements Serializable {

    @SerializedName("messages")
    private List<MessageJson> messages;

    public MessagesListJson(List<MessageJson> messages) {
        this.messages = messages;
    }

    public List<MessageJson> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageJson> messages) {
        this.messages = messages;
    }

}
