package com.example.alex.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alex.todolist.R;
import com.example.alex.todolist.RequesterAPI;
import com.example.alex.todolist.TaskListViewAdapter;
import com.example.alex.todolist.jsons.MessageJson;
import com.example.alex.todolist.jsons.MessagesListJson;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class SessionActivity extends AppCompatActivity {
    TextView yourEmailTextView;
    ListView listView;
    Button createNewTaskButton;

    String email;
    Integer id;
    List<MessageJson> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        initUI();
        email = getIntent().getStringExtra("email");
        id = getIntent().getIntExtra("id", -1);
        yourEmailTextView.setText("Your email: " + email);
        setAllTasks();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = messages.get(position).getMessageString();

                Intent intent = new Intent(SessionActivity.this, TaskActivity.class);
                intent.putExtra("task", message);

                startActivity(intent);
            }
        });

        createNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, CreateTaskActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("id", id);

                startActivity(intent);
            }
        });
    }

    private void initUI(){
        yourEmailTextView = (TextView)findViewById(R.id.yourEmailTextView);
        listView = (ListView) findViewById(R.id.listView);
        createNewTaskButton = (Button)findViewById(R.id.createTaskButton);
    }

    private void setAllTasks(){
        RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
        Call<MessagesListJson> call = requesterAPI.getAllTasksByEmail(email);

        call.enqueue(new Callback<MessagesListJson>() {
            @Override
            public void onResponse(Response<MessagesListJson> response) {
                messages = response.body().getMessages();
                listView.setAdapter(new TaskListViewAdapter(messages, SessionActivity.this));
        }

            @Override
            public void onFailure(Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }
}
