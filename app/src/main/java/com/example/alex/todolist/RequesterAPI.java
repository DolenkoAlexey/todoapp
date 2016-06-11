package com.example.alex.todolist;

import com.example.alex.todolist.jsons.MessageJson;
import com.example.alex.todolist.jsons.MessagesListJson;
import com.example.alex.todolist.jsons.UserJson;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Alex on 11.06.2016.
 */

public interface RequesterAPI {

    @GET("authorization/user")
    Call<UserJson> getUserByEmail(@Query("email") String email);

    @POST("authorization/user")
    Call<JSONObject> sendUser(@Body UserJson userJson);

    @GET("session/usermessages")
    Call<MessagesListJson> getAllTasksByEmail(@Query("email") String email);

    @POST("session/message")
    Call<JSONObject> sendTask(@Body MessageJson messageJson);

    class Creator {
        static RequesterAPI requesterAPI;

        public static RequesterAPI getRequester() {
            if(requesterAPI == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://quiet-chamber-59081.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                requesterAPI = retrofit.create(RequesterAPI.class);
            }
            return requesterAPI;
        }
    }
}