package com.cours.dervaux.projetandroid.model;

public class Score {
    int id_user = -1;
    String game = null;
    int score = -1;

    public Score(int id_user, String game, int score){
        this.id_user = id_user;
        this.game = game;
        this.score = score;
    }
    public Score(String game, int score){
        this.score = score;
        this.game = game;
    }

    public int    getId(){
        return this.id_user;
    }
    public void   setId(int id_user) {
        this.id_user = id_user;
    }
    public String getGame(){
        return this.game;
    }
    public void setGame(String game) {
        this.game = game;
    }
    public int    getScore(){
        return this.score;
    }
    public void   setScore(int score) {
        this.score = score;
    }
}
