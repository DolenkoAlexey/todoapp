package com.example.alex.todolist.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.todolist.R;
import com.example.alex.todolist.RequesterAPI;
import com.example.alex.todolist.jsons.UserJson;

import java.io.IOException;

import retrofit.Call;
import retrofit.Response;

public class AuthActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passEditText;
    Button authButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        initUI();
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String pass = passEditText.getText().toString();

                new AuthAsync(email, pass).execute();
            }
        });
    }

    private void initUI(){
        emailEditText = (EditText)findViewById(R.id.emailAuthEditText);
        passEditText = (EditText)findViewById(R.id.passwordAuthEditText);
        authButton = (Button) findViewById(R.id.doAuthButton);
    }

    private class AuthAsync extends AsyncTask<Void, Void, UserJson>{

        private String email;
        private String pass;

        public AuthAsync(String email, String pass) {
            this.email = email;
            this.pass = pass;
        }

        @Override
        protected UserJson doInBackground(Void... params) {

            RequesterAPI requesterAPI = RequesterAPI.Creator.getRequester();
            Call<UserJson> call = requesterAPI.getUserByEmail(email);

            Response<UserJson> response = null;
            try {
                response = call.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert response != null;
            UserJson usersJson = response.body();

            return usersJson;
        }

        @Override
        protected void onPostExecute(UserJson user) {
            super.onPostExecute(user);

            if((user.getEmail() == null) || (!user.getPassword().equals(pass))){
                Toast.makeText(AuthActivity.this,
                        "Неправильный e-mail или пароль, попробуйте еще раз",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(AuthActivity.this, SessionActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("id", user.getId());
            startActivity(intent);
        }
    }
}
