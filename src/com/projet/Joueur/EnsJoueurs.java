package com.projet.Joueur;

import java.util.Vector;
import java.util.stream.Collectors;

public class EnsJoueurs {

    private Vector<Joueur> vectorJoueur;

    public EnsJoueurs(Vector<Joueur> vectorJoueur) {
        this.vectorJoueur = vectorJoueur;
    }

    public EnsJoueurs() {
        vectorJoueur = new Vector<>();
    }

    public void creer(){
        for (int i = 0; i < 20; i++) {
            vectorJoueur.add(new Joueur(String.valueOf ((char) (i+65)))); //pour avoir les lettre de A à la 20e lettre
        }
    }

    public void afficher(){
        for (Joueur joueur : vectorJoueur) {
            System.out.println(joueur.toString());
        }
    }

    /**
     * Choisi un joueur pour jouer la partie et change son statut à "selectionné"
     */
    public void selectionnerJoueur(){
        boolean retry = false;
        int index;
        do {
            index = (int) (Math.random() * 20);
            if (vectorJoueur.get(index).getEtat().equals("en attente")){
                retry = true;
            }
        }while (!retry);
        vectorJoueur.get(index).changerEtat("sélectionné");
    }

    public Joueur[] getJoueurSelectionne(){
        Joueur[] a = (Joueur[]) vectorJoueur
                .stream()
                .filter(j -> j.getEtat().equals("sélectionné"))
                .toArray(Joueur[]::new);
        return a;
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
