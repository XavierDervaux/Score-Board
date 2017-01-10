package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cours.dervaux.projetandroid.R;

public class Top10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}
/*
* 1.4 Afficher le top 10 d'un jeu
Un formulaire permettra à l'utilisateur de saisir le nom d'un jeu au clavier ou via un wizard. Une fois
le nom du jeu choisi, il devra appuyer sur un bouton pour voir le top 10 des scores d'un jeu.

* */