package com.cours.dervaux.projetandroid.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.cours.dervaux.projetandroid.R;

public class AddScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        getSupportActionBar().setHomeButtonEnabled(true);
    }
}


/*
* 1.3 Ajouter un score
Le formulaire d'encodage d'un score demandera à l'utilisateur de saisir les informations suivantes:
• le nom du jeu
• le score obtenu
Une fois les informations encodées, l'utilisateur devra cliquer sur un bouton. Si les informations ont
été sauvegardées correctement dans la base de données, la fenêtre se ferme, et on revient au menu
principal.
Remarque
Le nom du jeu peut soit être saisi au clavier, soit être sélectionné grâce à un wizard (géré par une
activité développée "à la main").
* */