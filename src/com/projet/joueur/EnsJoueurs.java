package com.projet.joueur;

import java.util.Vector;

public class EnsJoueurs {

    private Vector<Joueur> vectorJoueur;
    private int index = 0;//index du dernier joueur selectionné

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
        this.index = index;
        vectorJoueur.get(index).changerEtat("sélectionné");
    }

    public Joueur[] getJoueurSelectionne(){
        return vectorJoueur
                .stream()
                .filter(j -> j.getEtat().equals("sélectionné"))
                .toArray(Joueur[]::new);
    }

    public Joueur getLastJoueurSelectionne(){
        return vectorJoueur.get(index);
    }

    /**
     * Getter et setter
     */
    public Vector<Joueur> getVectorJoueur() {
        return vectorJoueur;
    }

}
