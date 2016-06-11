package com.example.alex.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.todolist.R;
import com.example.alex.todolist.RequesterAPI;
import com.example.alex.todolist.jsons.UserJson;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passEditText;
    Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initUI();

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();

            String email = emailEditText.getText().toString();
            String pass = passEditText.getText().toString();
            UserJson userJson = new UserJson(0, email, pass);

            Call<JSONObject> call = requesterAPI.sendUser(userJson);

            call.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Response<JSONObject> response) {
                    Toast.makeText(RegistrationActivity.this,
                            "Регистрация прошла успешно!",
                            Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegistrationActivity.this, BeginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Throwable t) {
                    throw new RuntimeException(t);
                }
            });
            }
        });
    }

    private void initUI(){
        emailEditText = (EditText)findViewById(R.id.emailRegistrationEditText);
        passEditText = (EditText)findViewById(R.id.passwordRegistrationEditText);
        registrationButton = (Button) findViewById(R.id.doRegistrationButton);
    }
}
