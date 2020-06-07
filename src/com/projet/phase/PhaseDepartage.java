package com.projet.phase;

import com.projet.fenetre.question.GUI_QCM;
import com.projet.fenetre.question.GUI_RC;
import com.projet.fenetre.question.GUI_VF;
import com.projet.joueur.Joueur;
import com.projet.question.ListeQuestions;
import com.projet.question.Question;
import com.projet.question.type.QCM;
import com.projet.question.type.RC;
import com.projet.question.type.VF;
import com.projet.Themes;
import com.projet.Tools;

import javax.swing.*;
import java.util.List;

public class PhaseDepartage implements Phase {
    private Themes theme;
    private List<Question> listeQuestionsPhase;
    private ListeQuestions listeQuestionsAll;
    private Joueur[] joueurs;
    private Joueur joueurElimine;
    private JFrame parent;
    private int level;

    public PhaseDepartage(Themes theme, ListeQuestions listeQuestions, JFrame parent, Joueur... joueurs) {
        this.level = 1;
        this.theme = theme;
        theme.selectionnerTheme();
        this.listeQuestionsAll = listeQuestions;
        this.listeQuestionsPhase = listeQuestions.getQuestionByThemeLevel(theme.getArrayTheme()[theme.getIndicateur()], 1);
        this.joueurs = joueurs;
        this.parent = parent;
    }

    public PhaseDepartage(Themes theme, ListeQuestions listeQuestions, JFrame parent, int numPhase, Joueur... joueurs) {
        this.level = numPhase;
        this.theme = theme;
        theme.selectionnerTheme();
        this.listeQuestionsAll = listeQuestions;
        this.listeQuestionsPhase = listeQuestions.getQuestionByLevel(numPhase); //les questions proviennent de n'importe quel thème de niveau équivalent à la phase;
        this.joueurs = joueurs;
        this.parent = parent;
    }


    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {
        int round = 0;
        boolean end = false;
        int[] score = new int[joueurs.length];
        for (int i: score) { score[i] = 0;}
        Joueur[] joueurADepartager = joueurs.clone();

        displayMessageRules();
        do {
            //ask questions to each player
            askQuestionToPlayer(listeQuestionsPhase.size(), score, joueurADepartager);
            //analyse des scores
            joueurADepartager = whoLost(score, joueurADepartager).clone();
            if (joueurADepartager.length == 1){ //si une seul joueur n'ayant pas bien répondu
                joueurElimine = joueurADepartager[0];
                end = true;
                JOptionPane.showMessageDialog(null, "Le joueur " + joueurElimine.getNom() + " est éliminé !", "Elimination", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Aucune joueur éliminé, il reste " + (2-round) + " rounds pour départager les joueurs" +
                        "", "Round suivant", JOptionPane.INFORMATION_MESSAGE);
            }
            //Init des scores pour le round d'après
            score = new int[joueurADepartager.length];
            for (int i: score) { score[i] = 0;}
            round++;
        }while (((round != 3) && !end));

        if(joueurElimine == null){
            joueurElimine = joueurADepartager[(int) (Math.random()*joueurADepartager.length)];
        }
    }

    /**
     * Affichage des questions pour chaque joueur à departager
     */
    private void askQuestionToPlayer(int nbQuestion, int[] score, Joueur[] joueurADepartager){
        /** Modification direct de temps de réponse car shallow copy**/
        //affichage des questions
        for (int i = 0; i < joueurADepartager.length ; i++) {
            int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);
            Question<?> q = listeQuestionsPhase.get(numQuestionSelected);
            switch (Tools.getQuestionType(q)){
                case "QCM" :
                    GUI_QCM qcm = new GUI_QCM(parent,((QCM) q.getEnonce()).getTexte(),theme.getArrayTheme()[theme.getIndicateur()], joueurADepartager[i].getNom(), ((QCM) q.getEnonce()).getReponses());
                    if (q.saisir(qcm.getAnswer())){
                        score[i] += 1;
                    }
                    break;
                case "VF":
                    GUI_VF vf = new GUI_VF(parent, ((VF) q.getEnonce()).getTexte(),theme.getArrayTheme()[theme.getIndicateur()], joueurADepartager[i].getNom());
                    if (q.saisir(vf.getAnswer())){
                        score[i] += 1;
                    }
                    break;
                case "RC":
                    GUI_RC rc = new GUI_RC(parent,((RC) q.getEnonce()).getTexte(),theme.getArrayTheme()[theme.getIndicateur()], joueurADepartager[i].getNom());
                    if (q.saisir(rc.getAnswer())){
                        score[i] += 1;
                    }
                    break;
            }
        }
    }

    /**
     * Recuperation du tableau de joueur restant à departager ou de l'unique joueur éliminé ou le tableau original si que des bonnes réponses
     */
    private Joueur[] whoLost(int[] score, Joueur[] joueurs){
        int nbLoser = 0;
        for (int value : score) {
            if (value == 0) {
                nbLoser++;
            }
        }
        if(nbLoser != 0){// si au moins un loser => renvoie ces joueurs pour le round d'après
            Joueur[] loser = new Joueur[nbLoser];
            int index = 0;
            for (int i = 0; i < joueurs.length ; i++) {
                if(score[i] == 0){
                    loser[index] = joueurs[i];
                    index++;
                }
            }
            return loser;
        }else{ //si que des bonnes réponses => renvoie le tableau original
            return joueurs;
        }
    }

    private void displayMessageRules(){
        StringBuilder message = new StringBuilder("Phase de départages \nLes joueurs jouent à tour de rôle sur des questions de niveau  " + level +
                "\nLe joueur éliminé est celui répondant mal lors d'un round (max 3)\n" +
                "Ordre : ");
        for (Joueur j: joueurs) {
            message.append(j.getNom()).append(" ");
        }
        JOptionPane.showMessageDialog(null, message.toString(), "Régle de la phase", JOptionPane.INFORMATION_MESSAGE);
    }

    public Joueur getJoueurElimine() {
        return joueurElimine;
    }
}
