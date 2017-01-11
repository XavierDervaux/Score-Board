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
import com.cours.dervaux.projetandroid.model.GetRegister;
import com.cours.dervaux.projetandroid.model.Utilisateur;

public class Connection extends AppCompatActivity {
    private int type = 1; //1 = connect, -1 = register

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        ((TextView) findViewById(R.id.output)).setText("");
    }

    protected void btn_toggleRegCon(View view) {
        toggleRegCon();
    }

    protected void btn_sendRegister(View view){
        String username = ((EditText) findViewById(R.id.inputUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.inputPassword)).getText().toString();

        if(password.equals(((EditText) findViewById(R.id.inputPassConf)).getText().toString())){ //Le mdp est égal à sa confirmation
            Utilisateur.connectedUser = new Utilisateur(username, password);
            new GetRegister(Connection.this).execute(Utilisateur.connectedUser);
        } else ((TextView) findViewById(R.id.output)).setText(R.string.error110);
    }

    protected void btn_sendConnect(View view){
        String username = ((EditText) findViewById(R.id.inputUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.inputPassword)).getText().toString();

        Utilisateur.connectedUser = new Utilisateur(username, password);
        new GetConnect(Connection.this).execute(Utilisateur.connectedUser);
    }

    public void registerResponse(int code){
        switch(code){
            case 0   :
                Utilisateur.connectedUser = null; //L'user doit ensuite se connecter
                toggleRegCon();
                ((TextView) findViewById(R.id.output)).setText(R.string.registered); break;
            case 100 : ((TextView) findViewById(R.id.output)).setText(R.string.error100); break;
            case 110 : ((TextView) findViewById(R.id.output)).setText(R.string.error110); break;
            case 200 : ((TextView) findViewById(R.id.output)).setText(R.string.error200I); break;
            case 1000: ((TextView) findViewById(R.id.output)).setText(R.string.error1000); break;
            case 2000: ((TextView) findViewById(R.id.output)).setText(R.string.error2000); break;
        }
    }

    public void connectResponse(int id, int code){
        switch(code){
            case 0   :
                Utilisateur.connectedUser.setId(id);
                Intent intent = new Intent(this, Index.class);
                startActivity(intent); break;
            case 100 : ((TextView) findViewById(R.id.output)).setText(R.string.error100); Utilisateur.connectedUser = null; break;
            case 110 : ((TextView) findViewById(R.id.output)).setText(R.string.error110); Utilisateur.connectedUser = null; break;
            case 200 : ((TextView) findViewById(R.id.output)).setText(R.string.error200C); Utilisateur.connectedUser = null; break;
            case 1000: ((TextView) findViewById(R.id.output)).setText(R.string.error1000); Utilisateur.connectedUser = null; break;
            case 2000: ((TextView) findViewById(R.id.output)).setText(R.string.error2000); Utilisateur.connectedUser = null; break;
        }
    }

    private void toggleRegCon() {
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
        type = type * -1;
    }
}
