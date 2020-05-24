package com.projet.Phase;

import com.projet.Joueur.EnsJoueurs;
import com.projet.Joueur.Joueur;
import com.projet.Question.ListeQuestions;
import com.projet.Question.Question;
import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;
import com.projet.Themes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Phase1 implements Phase {
    private Themes theme;
    private List<Question> listeQuestions;
    private Joueur[] joueurs;

    public Phase1(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs) {
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
        int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);
        for (int i = 0; i < joueurs.length ; i++) {
            System.out.println("----- C'est au joueur " + joueurs[i].getNom() + " de jouer." );
            System.out.println(listeQuestions.get(numQuestionSelected).toString());
            System.out.print("Votre réponse : ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (isGoodAnswer(listeQuestions.get(numQuestionSelected), answer)){
                joueurs[i].majScore(2);
                System.out.println("Bonne réponse +2 pts\n");
            }
            else{
                System.out.println("Mauvaise réponse +0 pts\n");
            }
            numQuestionSelected = (numQuestionSelected+1)%nbQuestion;
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
