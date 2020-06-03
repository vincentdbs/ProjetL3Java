package com.projet.Phase;

import com.projet.Chronometre;
import com.projet.Fenetre.Question.GUI_QCM;
import com.projet.Fenetre.Question.GUI_RC;
import com.projet.Fenetre.Question.GUI_VF;
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
    private List<Question> listeQuestionsPhase;
    private ListeQuestions listeQuestionsAll;
    private Joueur[] joueurs;
    private Joueur joueurElimine;
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
        int round = 0;
        boolean end = false;
        int[] score = new int[joueurs.length];
        do {
            //init des scores
            for (int i: score) { score[i] = 0;}
            //ask questions to each player
            askQuestionToPlayer(listeQuestionsPhase.size(), score);
            //analyse des scores
            int loser = whoLost(score);
            if (!(loser == -1)){
                joueurElimine = joueurs[loser];
                end = true;
            }
            round++;
        }while (((round != 3) || !end));
    }

    private void askQuestionToPlayer(int nbQuestion, int[] score){
        /** Modification direct de temps de réponse car shallow copy**/
        //affichage des questions
        for (int i = 0; i < joueurs.length ; i++) {
            int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);
            Question<?> q = listeQuestionsPhase.get(numQuestionSelected);
            switch (Tools.getQuestionType(q)){
                case "QCM" :
                    GUI_QCM qcm = new GUI_QCM(parent,((QCM) q.getEnonce()).getTexte(), ((QCM) q.getEnonce()).getReponses());
                    if (Tools.isGoodAnswer(q, qcm.getAnswer())){
                        score[i] += 1;
                    }
                    break;
                case "VF":
                    GUI_VF vf = new GUI_VF(parent, ((VF) q.getEnonce()).getTexte());
                    if (Tools.isGoodAnswer(q, vf.getAnswer())){
                        score[i] += 1;
                    }
                    break;
                case "RC":
                    GUI_RC rc = new GUI_RC(parent,((RC) q.getEnonce()).getTexte());
                    if (Tools.isGoodAnswer(q, rc.getAnswer())){
                        score[i] += 1;
                    }
                    break;
            }
        }
    }

    /**
     * Renvoie -1 si pas de perdant
     * Renvoie l'index du perdant sinon
     */
    private int whoLost(int[] score){
        int lose = 0;
        int nbLoser = 0;
        for (int i = 0; i < score.length; i++) {
            if(score[i] == 0){
                lose = i;
                nbLoser++;
            }
        }
        if(nbLoser != 1){
            return -1;
        }else{
            return lose;
        }
    }

    public Joueur getJoueurElimine() {
        return joueurElimine;
    }
}
