package com.cours.dervaux.projetandroid.model;

public class Score {
    String user = null;
    String game = null;
    int score = -1;

    public Score(String user, String game, int score){
        this.user = user;
        this.game = game;
        this.score = score;
    }

    public String  getUser(){
        return this.user;
    }
    public void    setUser(String user) {
        this.user = user;
    }
    public String  getGame(){
        return this.game;
    }
    public void    setGame(String game) {
        this.game = game;
    }
    public int     getScore(){
        return this.score;
    }
    public void    setScore(int score) {
        this.score = score;
    }
}
