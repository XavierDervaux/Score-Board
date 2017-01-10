package com.cours.dervaux.projetandroid.model;

public class Utilisateur {
    public static Utilisateur connectedUser = null;
    private int id = -1;
    private String pseudo = null;
    private String mdp = null;

    public Utilisateur(int id, String pseudo){
        this.id = id;
        this.pseudo = pseudo;
        this.mdp = mdp;
    }
    public Utilisateur(String pseudo, String mdp){
        this.pseudo = pseudo;
        this.mdp = mdp;
    }

    public int getId(){
        return this.id;
    }
    public void   setId(int id) {
        this.id = id;
    }
    public String getPseudo(){
        return this.pseudo;
    }
    public void   setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getMdp(){
        return this.mdp;
    }
    public void   setMdp(String mdp) {
        this.mdp = mdp;
    }
}
