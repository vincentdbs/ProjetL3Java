package com.projet.Phase;

import com.projet.Fenetre.Qcm;
import com.projet.Fenetre.Repcourte;
import com.projet.Fenetre.Start;
import com.projet.Fenetre.Vf;
import com.projet.Joueur.EnsJoueurs;
import com.projet.Joueur.Joueur;
import com.projet.Question.ListeQuestions;
import com.projet.Question.Question;
import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;
import com.projet.Stopwatch;
import com.projet.Themes;
import com.projet.Tools;

import javax.swing.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

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

        for (int i = 0; i < joueurs.length ; i++) {
            int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);
//            System.out.println("----- C'est au joueur " + joueurs[i].getNom() + " de joueur.");
//            System.out.println(listeQuestions.get(numQuestionSelected).toString());
//            System.out.print("Votre réponse : ");
//
//            Scanner scanner = new Scanner(System.in);
//            String answer = scanner.nextLine();
//
//            if (isGoodAnswer(listeQuestions.get(numQuestionSelected), answer)){
//                joueurs[i].majScore(2);
//                System.out.println("Bonne réponse +2 pts\n");
//            }
//            else{
//                System.out.println("Mauvaise réponse +0 pts\n");
//            }
            Question<?> q = listeQuestions.get(numQuestionSelected);
            switch (Tools.getQuestionType(q)){
                case "QCM" :
                    Qcm qcm = new Qcm(parent,((QCM) q.getEnonce()).getTexte(), ((QCM) q.getEnonce()).getReponses());
                    if (isGoodAnswer(q, qcm.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    qcm.dispose();
                    System.out.println(joueurs[i].getScore());
                    break;
                case "VF":
                    Vf vf = new Vf(parent, ((VF) q.getEnonce()).getTexte());
                    if (isGoodAnswer(q, vf.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    vf.dispose();
                    System.out.println(joueurs[i].getScore());
                    break;
                case "RC":
                    Repcourte rc = new Repcourte(parent,((RC) q.getEnonce()).getTexte());
                    if (isGoodAnswer(q, rc.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    rc.dispose();
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
