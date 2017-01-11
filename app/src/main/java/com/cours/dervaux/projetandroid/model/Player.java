package com.cours.dervaux.projetandroid.model;

public class Player {
    public static Player connectedUser = null; //The currently connected user, stored here for easy access

    private int    id     = -1;
    private String pseudo = null;
    private String mdp    = null;

    public Player(String pseudo, String mdp){
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public int    getId(){
        return this.id;
    }
    public void   setId(int id) {
        this.id = id;
    }
    public String getPseudo(){
        return this.pseudo;
    }
    public String getMdp(){
        return this.mdp;
    }
}
