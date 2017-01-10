package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cours.dervaux.projetandroid.R;

public class GameList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}

/*
* 1.5 Afficher la liste des jeux gérés actuellement
La liste des jeux actuellement gérés sera affichée à raison de 5 jeux par écran. Un système de
navigation devra permettre d'afficher les 5 jeux suivants ou les précédents.
Pour chaque ligne représentant un jeu, on devra trouver le nom du jeu, le pseudo du joueur ayant
actuellement le meilleur score et un bouton. En cliquant sur ce bouton, il sera possible d'afficher le
top 10 des scores pour ce jeu.

* */