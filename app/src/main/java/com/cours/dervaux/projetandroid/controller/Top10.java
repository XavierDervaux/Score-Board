package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.cours.dervaux.projetandroid.R;
import com.cours.dervaux.projetandroid.model.GetTopTen;
import com.cours.dervaux.projetandroid.model.Score;

import java.util.List;

public class Top10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        getSupportActionBar().setHomeButtonEnabled(true);
        ((TextView) findViewById(R.id.output)).setText("");
    }

    protected void sendTopTen(View view){
        String gameName = ((EditText) findViewById(R.id.inputT10GameName)).getText().toString();
        new GetTopTen(Top10.this).execute(gameName);
    }

    public void topTenResponse(int code, List<Score> list){
        switch(code){
            case 0   :
                ((TextView) findViewById(R.id.t10GameName)).setVisibility(View.INVISIBLE);
                ((EditText) findViewById(R.id.inputT10GameName)).setVisibility(View.INVISIBLE);
                ((TextView) findViewById(R.id.output)).setVisibility(View.INVISIBLE);
                ((Button)   findViewById(R.id.butTop10)).setVisibility(View.INVISIBLE);
                remplirVue(code, list); break;
            case 100 : ((TextView) findViewById(R.id.output)).setText(R.string.error100T); break;
            case 300 : ((TextView) findViewById(R.id.output)).setText(R.string.error200T); break;
            case 1000: ((TextView) findViewById(R.id.output)).setText(R.string.error1000); break;
            case 2000: ((TextView) findViewById(R.id.output)).setText(R.string.error2000); break;
        }
    }

    public void remplirVue(int code, List<Score> list){
        String out = this.getString(R.string.t10gameList) + " " + list.get(0).getGame() + " : \n";
        out +="-------------------------------------------------------------\n";
        for(Score ligne  : list){
            out = out + ligne.getScore() + "         " + ligne.getUser() + "\n";
        }
        ((TextView) findViewById(R.id.showTop10)).setText(out);
    }
}