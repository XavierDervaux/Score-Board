package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cours.dervaux.projetandroid.R;
import com.cours.dervaux.projetandroid.model.GetGameList;
import com.cours.dervaux.projetandroid.model.GetTopTen;
import com.cours.dervaux.projetandroid.model.Score;

import java.util.List;

public class GameList extends AppCompatActivity {
    private int i = 0;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        getSupportActionBar().setHomeButtonEnabled(true);

        new GetGameList(GameList.this).execute("");
    }

    protected void btn_goPrevious(View view){
        goPrevious();
    }

    protected void btn_goNext(View view){
        goNext();
    }

    public void gameListResponse(int code, List<String> list){
        switch(code){
            case 0   :
                this.list = list;
                this.i = 0;
                updateVue();
                if(list.size() > 5){ ((Button) findViewById(R.id.btn_next)).setEnabled(true); }
                break;
            case 100 : ((TextView) findViewById(R.id.output)).setText(R.string.error100T); break;
            case 300 : ((TextView) findViewById(R.id.output)).setText(R.string.error200T); break;
            case 1000: ((TextView) findViewById(R.id.output)).setText(R.string.error1000); break;
            case 2000: ((TextView) findViewById(R.id.output)).setText(R.string.error2000); break;
        }
    }

    private void updateVue(){
        TextView tv1 = (TextView) findViewById(R.id.tv_game1);
        TextView tv2 = (TextView) findViewById(R.id.tv_game2);
        TextView tv3 = (TextView) findViewById(R.id.tv_game3);
        TextView tv4 = (TextView) findViewById(R.id.tv_game4);
        TextView tv5 = (TextView) findViewById(R.id.tv_game5);

        if(i+0 < list.size()){ tv1.setText(list.get(this.i+0)); } else { tv1.setText(""); }
        if(i+1 < list.size()){ tv2.setText(list.get(this.i+1)); } else { tv2.setText(""); }
        if(i+2 < list.size()){ tv3.setText(list.get(this.i+2)); } else { tv3.setText(""); }
        if(i+3 < list.size()){ tv4.setText(list.get(this.i+3)); } else { tv4.setText(""); }
        if(i+4 < list.size()){ tv5.setText(list.get(this.i+4)); } else { tv5.setText(""); }
    }

    private void goPrevious(){
        this.i -= 5;
        if(this.i <= 0){
            this.i = 0;
            ((Button) findViewById(R.id.btn_prev)).setEnabled(false);
        }
        if(this.list.size() > 5){
            ((Button) findViewById(R.id.btn_next)).setEnabled(true);
        }
        updateVue();
    }

    private void goNext(){
        this.i += 5;
        if(this.i+5 >= this.list.size()){
            ((Button) findViewById(R.id.btn_next)).setEnabled(false);
        }
        if(this.i >= 5){
            ((Button) findViewById(R.id.btn_prev)).setEnabled(true);
        }
        updateVue();
    }
}
