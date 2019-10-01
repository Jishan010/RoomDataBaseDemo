package com.mobility.roomdatabasedemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(intent);
            }
        });


        initRecycleView();

        new AsyncTaskRunner().execute();

    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        new AsyncTaskRunner().execute();
    }


    private class AsyncTaskRunner extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            userList = UserDataBase.getInstance(MainActivity.this).getUserDao().getAllUser();
            if (userList != null) {
                for (User user : userList) {
                    Log.d("UserDetails", user.getStudentId() + " =" + user.getFirstName());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            adapter = new UserListRecycleAdapter(MainActivity.this, userList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


}
