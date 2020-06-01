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

public class Phase1 implements Phase {
    private Themes theme;
    private List<Question> listeQuestions;
    private Joueur[] joueurs;
    private JFrame parent;

    public Phase1(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs, JFrame parent) {
        this.theme = theme;
        theme.selectionnerTheme();
        this.listeQuestions = listeQuestions.getQuestionByThemeLevel(theme.getArrayTheme()[theme.getIndicateur()], 1);
        this.joueurs = joueurs;
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {
        int nbQuestion = listeQuestions.size();
        int[][] tempsReponses = new int[4][4];

        //affichage des questions
        for (int i = 0; i < joueurs.length ; i++) {
            int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);
            Question<?> q = listeQuestions.get(numQuestionSelected);
            switch (Tools.getQuestionType(q)){
                case "QCM" :
                    GUI_Qcm qcm = new GUI_Qcm(parent,((QCM) q.getEnonce()).getTexte(), ((QCM) q.getEnonce()).getReponses());
                    if (isGoodAnswer(q, qcm.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = qcm.getTempsReponse();
                    System.out.println(joueurs[i].getScore());
                    break;
                case "VF":
                    GUI_Vf vf = new GUI_Vf(parent, ((VF) q.getEnonce()).getTexte());
                    if (isGoodAnswer(q, vf.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = vf.getTempsReponse();
                    System.out.println(joueurs[i].getScore());
                    break;
                case "RC":
                    GUI_Repcourte rc = new GUI_Repcourte(parent,((RC) q.getEnonce()).getTexte());
                    if (isGoodAnswer(q, rc.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = rc.getTempsReponse();
                    System.out.println(joueurs[i].getScore());
                    break;
            }
        }
    }

    private boolean isGoodAnswer(Question question, String answer){
        switch (question.getEnonce().getClass().getName()){
            case "com.projet.Question.Type.QCM":{
                QCM enonce = (QCM) question.getEnonce();
                if(enonce.getBonneReponse() == Integer.parseInt(answer)){
                    return true;
                }
                break;
            }
            case "com.projet.Question.Type.VF":{
                VF enonce = (VF) question.getEnonce();
                if(enonce.isReponse() == Boolean.parseBoolean(answer)){
                    return true;
                }
                break;
            }
            case "com.projet.Question.Type.RC":{
                RC enonce = (RC) question.getEnonce();
                if(enonce.getBonneReponse().equals(answer)){
                    return true;
                }
                break;
            }
            default:
                break;
        }
        return false;
    }


}
