package com.cours.dervaux.projetandroid.model;

public class Score {
    int id = -1;
    String jeu = null;
    int score = -1;
    Utilisateur util = null;

    public Score(int id, String jeu, int score, Utilisateur util){
        this.id = id;
        this.score = score;
        this.jeu = jeu;
        this.util = util;
    }
    public Score(String jeu, int score, Utilisateur util){
        this.score = score;
        this.jeu = jeu;
        this.util = util;
    }

    public int    getId(){
        return this.id;
    }
    public void   setId(int id) {
        this.id = id;
    }
    public String getJeu(){
        return this.jeu;
    }
    public void   setJeu(String jeu) {
        this.jeu = jeu;
    }
    public int    getScore(){
        return this.score;
    }
    public void   setScore(int score) {
        this.score = score;
    }
    public Utilisateur getUtil() {
        return this.util;
    }
    public void setUtil(Utilisateur util) {
        this.util = util;
    }
}
