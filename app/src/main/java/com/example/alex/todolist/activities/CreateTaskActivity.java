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
import com.example.alex.todolist.jsons.MessageJson;

import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class CreateTaskActivity extends AppCompatActivity {
    Button createTaskButton;
    EditText enterTaskEditText;

    String email;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        initUI();
        email = getIntent().getStringExtra("email");
        id = getIntent().getIntExtra("id", -1);

        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = enterTaskEditText.getText().toString();
                MessageJson messageJson = new MessageJson(0, id, task);

                RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
                Call<JSONObject> call = requesterAPI.sendTask(messageJson);

                call.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Response<JSONObject> response) {
                        Toast.makeText(CreateTaskActivity.this,
                                "Задача добавлена!",
                                Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CreateTaskActivity.this, SessionActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("id", id);
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
        createTaskButton = (Button) findViewById(R.id.createButton);
        enterTaskEditText = (EditText)findViewById(R.id.enterTaskEditText);
    }
}
