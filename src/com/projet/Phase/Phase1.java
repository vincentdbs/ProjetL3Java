package com.projet.Phase;

import com.projet.Joueur.EnsJoueurs;
import com.projet.Joueur.Joueur;
import com.projet.Question.ListeQuestions;
import com.projet.Question.Question;
import com.projet.Themes;

import java.util.ArrayList;
import java.util.List;

public class Phase1 implements Phase {
    private Themes theme;
    private List<Question> listeQuestions;
    private Joueur[] joueurs;

    public Phase1(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs) {
        this.theme = theme;
        int indiceTheme = theme.selectionnerTheme();
        this.listeQuestions = listeQuestions.getQuestionByThemeLevel(theme.getArrayTheme()[indiceTheme], 1);
        this.joueurs = joueurs;
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {


    }

}
