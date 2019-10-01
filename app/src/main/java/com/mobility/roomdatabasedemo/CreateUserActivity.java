package com.mobility.roomdatabasedemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class CreateUserActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private Button button;
    private UserDataBase userDataBase;

    private void findViews() {
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        button = findViewById(R.id.button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        findViews();
        userDataBase = UserDataBase.getInstance(CreateUserActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new InsertUserDataTask(firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), emailEditText.getText().toString()).execute();
            }
        });
    }


    private class InsertUserDataTask extends AsyncTask<Void, Void, String> {

        private String firstName, lastName, email;

        public InsertUserDataTask(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        @Override
        protected String doInBackground(Void... voids) {
            User user = new User(firstName, lastName, email);
            userDataBase.getUserDao().insertUser(user);
            return "Success";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(CreateUserActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
