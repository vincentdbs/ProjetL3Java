package com.projet.Phase;

import com.projet.Joueur.EnsJoueurs;
import com.projet.Joueur.Joueur;
import com.projet.Question.ListeQuestions;
import com.projet.Themes;

import java.util.ArrayList;

public class Phase1 implements Phase {
    private Themes theme;
    private ListeQuestions listeQuestions;
    private Joueur[] joueurs;

    public Phase1(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs) {
        this.theme = theme;
        this.listeQuestions = listeQuestions;
        this.joueurs = joueurs;
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {
        int themePhase = theme.selectionnerTheme();


    }

}
