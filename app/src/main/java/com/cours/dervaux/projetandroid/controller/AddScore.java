package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.cours.dervaux.projetandroid.R;
import com.cours.dervaux.projetandroid.model.GetScore;
import com.cours.dervaux.projetandroid.model.Score;
import com.cours.dervaux.projetandroid.model.Utilisateur;

public class AddScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void btn_sendScore(View view){
        int userId   =  Utilisateur.connectedUser.getId();
        String name  = ((EditText) findViewById(R.id.inputGameName)).getText().toString();
        try {
            int in = Integer.parseInt(((EditText) findViewById(R.id.inputScore)).getText().toString());
            Score score = new Score(userId,name,in);

            new GetScore(AddScore.this).execute(score);
        }catch(Exception e){ ((TextView) findViewById(R.id.output)).setText(R.string.err100); }
    }

    public void scoreResponse(int code){
        switch(code){
            case 0   : finish(); break;
            case 100 : ((TextView) findViewById(R.id.output)).setText(R.string.err100);    break;
            case 110 : ((TextView) findViewById(R.id.output)).setText(R.string.err110);    break;
            case 120 : ((TextView) findViewById(R.id.output)).setText(R.string.err120);    break;
            case 1000: ((TextView) findViewById(R.id.output)).setText(R.string.error1000); break;
            case 2000: ((TextView) findViewById(R.id.output)).setText(R.string.error2000); break;
        }
    }
}