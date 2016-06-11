package com.example.alex.todolist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.alex.todolist.R;

public class TaskActivity extends AppCompatActivity {
    TextView taskTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        initUI();
        taskTextView.setText(getIntent().getStringExtra("task"));
    }

    private void initUI(){
        taskTextView = (TextView) findViewById(R.id.taskTextView);
    }
}
