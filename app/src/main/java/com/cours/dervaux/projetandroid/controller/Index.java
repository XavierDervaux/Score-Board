package com.cours.dervaux.projetandroid.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cours.dervaux.projetandroid.R;

public class Index extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    protected void loadAddScore(View view){
        Intent intent = new Intent(this, AddScore.class);
        startActivity(intent);
    }

    protected void loadTop10(View view){
        Intent intent = new Intent(this, Top10.class);
        startActivity(intent);
    }

    protected void loadGameList(View view){
        Intent intent = new Intent(this, GameList.class);
        startActivity(intent);
    }

    protected void loadUserList(View view){
        Intent intent = new Intent(this, UserList.class);
        startActivity(intent);
    }
}
