package com.example.alex.todolist.jsons;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Alex on 11.06.2016.
 */
public class UserJson implements Serializable {
    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public UserJson(){}

    public UserJson(Integer id, String email, String password){
        setId(id);
        setEmail(email);
        setPassword(password);
    }

    public int getId(){
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}