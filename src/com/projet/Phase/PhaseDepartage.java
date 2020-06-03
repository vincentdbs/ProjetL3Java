package com.projet.Phase;

import com.projet.Joueur.Joueur;
import com.projet.Question.ListeQuestions;
import com.projet.Question.Question;
import com.projet.Themes;

import javax.swing.*;
import java.util.List;

public class PhaseDepartage implements Phase {
    private Themes theme;
    private List<Question> listeQuestionsPhase;
    private ListeQuestions listeQuestionsAll;
    private Joueur[] joueurs;
    private JFrame parent;

    public PhaseDepartage(Themes theme, ListeQuestions listeQuestions, JFrame parent, Joueur... joueurs) {
        this.theme = theme;
        theme.selectionnerTheme();
        this.listeQuestionsAll = listeQuestions;
        this.listeQuestionsPhase = listeQuestions.getQuestionByThemeLevel(theme.getArrayTheme()[theme.getIndicateur()], 1);
        this.joueurs = joueurs;
        this.parent = parent;
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {

    }
}
