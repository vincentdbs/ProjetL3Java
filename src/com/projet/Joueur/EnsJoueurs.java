package com.projet.Joueur;

import java.util.Vector;

public class EnsJoueurs {

    private Vector<Joueur> vectorJoueur;

    public EnsJoueurs(Vector<Joueur> vectorJoueur) {
        this.vectorJoueur = vectorJoueur;
    }

    public EnsJoueurs() {
        vectorJoueur = new Vector<>();
    }

    public void creer(){
        //todo changer pour que les joueurs aient tous des noms différents
        for (int i = 0; i < 20; i++) {
            vectorJoueur.add(new Joueur(String.valueOf ((char) (i+65)))); //pour avoir les lettre de A à la 20e lettre
        }
    }

    public void afficher(){
        for (Joueur joueur : vectorJoueur) {
            System.out.println(joueur.toString());
        }
    }

    public Joueur selectionnerJoueur(){
        return vectorJoueur.get((int) (Math.random() * 20));
    }

    /**
     * Getter et setter
     */
    public Vector<Joueur> getVectorJoueur() {
        return vectorJoueur;
    }

    public void setVectorJoueur(Vector<Joueur> vectorJoueur) {
        this.vectorJoueur = vectorJoueur;
    }
}
