package com.cours.dervaux.projetandroid.model;

public class RPC_FETCH { //Here to easiky access the RPC in the asynchronous functions, easy to modify if need be.
    public static String SERVER_URL = "http://192.168.1.149/projects/ProjetAndroid/"; //Home
    //public static String SERVER_URL = "http://10.52.29.138/projects/ProjetAndroid/";  //School
    public static String CONNECTION = SERVER_URL + "se_connecter.php";
    public static String REGISTER   = SERVER_URL + "creer_compte.php";
    public static String ADD_SCORE  = SERVER_URL + "ajouter_score.php";
    public static String TOP_TEN    = SERVER_URL + "afficher_top.php";
    public static String LIST_GAME  = SERVER_URL + "lister_jeux.php";
    public static String LIST_USER  = SERVER_URL + "lister_pseudos.php";
}