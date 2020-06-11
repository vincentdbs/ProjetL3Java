package com.projet.joueur;

/**
 * DORFFER - DUBOIS - ESTEBAN - GOMEZ CASTELLON - MATHIEN
 */

public class Joueur {
    private static int indexJoueur = 100; //numero des joueurs commence à 100
    private String nom, etat; //etat = sélectionné, gagnant, super gagnant, éliminé ou en attente
    private int score, numero;


    public Joueur(String nom, String etat, int score) {
        this.nom = nom;
        this.etat = etat;
        this.score = score;
        this.numero = indexJoueur;
        indexJoueur += 10; //numero des joueurs de 10 en 10
    }

    public Joueur(String nom, String etat) {
        this.nom = nom;
        this.etat = etat;
        this.score = 0;
        this.numero = indexJoueur;
        indexJoueur += 10;
    }

    public Joueur(String nom) {
        this.nom = nom;
        this.etat = "en attente";
        this.score = 0;
        this.numero = indexJoueur;
        indexJoueur += 10;
    }

    public void afficher(){
        System.out.println("Joueur n°" + getNumero() + "(" + getEtat() +  "): " + getNom() + " a " + getScore() + " points\n");
    }

    @Override
    public String toString(){
        return "Joueur n°" + getNumero() + "(" + getEtat() +  "): " + getNom() + " a " + getScore() + " points\n";
    }

    public void saisir(){
        //aucne utilité car saisir dans Question (cf FAQ Prof de java)
    }

    public void majScore(int score){
        setScore(getScore() + score);
    }

    public void changerEtat(String etat){
        setEtat(etat);
    }

    /**
     * Getter et setter
     */
    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
