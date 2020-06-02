package com.projet.Phase;

import com.projet.Fenetre.GUI_Qcm;
import com.projet.Fenetre.GUI_Repcourte;
import com.projet.Fenetre.GUI_Vf;
import com.projet.Joueur.Joueur;
import com.projet.Question.ListeQuestions;
import com.projet.Question.Question;
import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;
import com.projet.Themes;
import com.projet.Tools;

import javax.swing.*;
import java.util.List;

public class PhaseDepartage implements Phase {
    private Themes theme;
    private List<Question> listeQuestions;
    private Joueur[] joueurs;
    private JFrame parent;

    public PhaseDepartage(Themes theme, ListeQuestions listeQuestions, JFrame parent, Joueur... joueurs) {
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
