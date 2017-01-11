package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cours.dervaux.projetandroid.R;
import com.cours.dervaux.projetandroid.model.GetGameList;
import com.cours.dervaux.projetandroid.model.GetUserList;

import java.util.List;

public class UserList extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        getSupportActionBar().setHomeButtonEnabled(true);

        new GetUserList(UserList.this).execute("");
    }

    public void userListResponse(int code, List<String> list){
        switch(code){
            case 0   :
                printView(list); break;
            case 100 : ((TextView) findViewById(R.id.output)).setText(R.string.error100T); break;
            case 300 : ((TextView) findViewById(R.id.output)).setText(R.string.error200T); break;
            case 1000: ((TextView) findViewById(R.id.output)).setText(R.string.error1000); break;
            case 2000: ((TextView) findViewById(R.id.output)).setText(R.string.error2000); break;
        }
    }

    private void printView(List<String> list){
        LinearLayout         layout  = new LinearLayout(UserList.this);
        ScrollView           sv  = new ScrollView(UserList.this);
        ListView             lv   = new ListView(UserList.this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(UserList.this,android.R.layout.simple_list_item_1, list);

        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT ,LinearLayout.LayoutParams.MATCH_PARENT ));
        sv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT ,LinearLayout.LayoutParams.MATCH_PARENT));
        sv.setFillViewport(true);
        lv.setLayoutParams( new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT ,LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));

        lv.setAdapter(adapter);
        sv.addView(lv);
        layout.addView(sv);
        setContentView(layout);
    }
}