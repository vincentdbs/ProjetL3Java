package com.projet.Phase;

import com.projet.Joueur.Joueur;
import com.projet.Question.ListeQuestions;
import com.projet.Question.Question;
import com.projet.Themes;

import javax.swing.*;
import java.util.List;

public class PhaseDerpartage implements Phase {
    private Themes theme;
    private List<Question> listeQuestions;
    private Joueur[] joueurs;
    private JFrame parent;

    public PhaseDerpartage(Themes theme, ListeQuestions listeQuestions,  JFrame parent, Joueur... joueurs) {
        this.theme = theme;
        theme.selectionnerTheme();
        this.listeQuestions = listeQuestions.getQuestionByThemeLevel(theme.getArrayTheme()[theme.getIndicateur()], 1);
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
