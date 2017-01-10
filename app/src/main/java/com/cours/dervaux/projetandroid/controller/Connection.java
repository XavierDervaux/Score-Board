package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import com.cours.dervaux.projetandroid.R;
import com.cours.dervaux.projetandroid.model.GetConnect;
import com.cours.dervaux.projetandroid.model.Utilisateur;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Connection extends AppCompatActivity {
    private int type = 1; //1 = connect, -1 = register
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public static Utilisateur connectedUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    protected void toggleRegCon(View view) {
        Button btnCon = (Button) findViewById(R.id.butConnect);
        Button btnReg = (Button) findViewById(R.id.but_register);
        TextView label = (TextView) findViewById(R.id.passConfirm);
        EditText confMdp = (EditText) findViewById(R.id.inputPassConf);

        if (type == 1) { //Switching to register
            btnReg.setEnabled(true);
            btnReg.setVisibility(View.VISIBLE);
            btnCon.setEnabled(false);
            btnCon.setVisibility(View.INVISIBLE);
            label.setVisibility(View.VISIBLE);
            confMdp.setVisibility(View.VISIBLE);
        } else { //switching to connect
            btnCon.setEnabled(true);
            btnCon.setVisibility(View.VISIBLE);
            btnReg.setEnabled(false);
            btnReg.setVisibility(View.INVISIBLE);
            label.setVisibility(View.INVISIBLE);
            confMdp.setVisibility(View.INVISIBLE);
        }
    }

    protected void sendRegister(View view){
        String username = ((EditText) findViewById(R.id.inputName)).getText().toString();
        String password = ((EditText) findViewById(R.id.inputPassword)).getText().toString();

        if(password.equals(((EditText) findViewById(R.id.inputPassConf)).getText().toString())){ //Le mdp est égal à sa confirmation

        }
    }

    protected void sendConnect(View view) {
        String username = ((EditText) findViewById(R.id.inputName)).getText().toString();
        String password = ((EditText) findViewById(R.id.inputPassword)).getText().toString();

        Utilisateur user = new Utilisateur(username, password);
        new GetConnect(Connection.this).execute(user);
    }

    public void registerResponse(int id, int code){

    }

    public void connectResponse(int id, int code){

        Intent intent = new Intent(this, Index.class);
        startActivity(intent);
    }
}
