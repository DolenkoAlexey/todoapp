package com.example.alex.todolist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alex.todolist.R;

public class BeginActivity extends AppCompatActivity {
    Button authButton;
    Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        initUI();

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeginActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BeginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI(){
        authButton = (Button) findViewById(R.id.authButton);
        registrationButton= (Button) findViewById(R.id.registrationButton);
    }
}
