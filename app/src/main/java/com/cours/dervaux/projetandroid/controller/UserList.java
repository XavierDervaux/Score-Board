package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cours.dervaux.projetandroid.R;

public class UserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}

/*1.6 Lister les utilisateurs
Les pseudos des différents utilisateurs devront être affichés sur un écran avec, si nécessaire, la
possibilité de faire défiler cet écran.
Remarque
Le formulaire devra être créé "à la volée" en Java.*/