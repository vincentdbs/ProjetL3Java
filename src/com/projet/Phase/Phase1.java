package com.projet.Phase;

import com.projet.Chronometre;
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
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

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
        this.parent = parent;
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {
        int nbQuestion = listeQuestions.size();
        Chronometre[]tempsReponses = new Chronometre[4];

        JOptionPane.showMessageDialog(null, "Phase 1 \nLes joueurs jouent à tour de rôle sur le thème : "
                + theme.getArrayTheme()[theme.getIndicateur()] +
                "\nChaque bonne réponse rapporte 2 points.\n" +
                "Ordre : " + joueurs[0].getNom() + ", "
                + joueurs[1].getNom() + ", "
                + joueurs[2].getNom() + ", "
                + joueurs[3].getNom(), "Régle de la phase", JOptionPane.INFORMATION_MESSAGE);

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
                    tempsReponses[i] = qcm.getChronometre();
                    System.out.println(joueurs[i].getScore());
                    break;
                case "VF":
                    GUI_Vf vf = new GUI_Vf(parent, ((VF) q.getEnonce()).getTexte());
                    if (isGoodAnswer(q, vf.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = vf.getChronometre();
                    System.out.println(joueurs[i].getScore());
                    break;
                case "RC":
                    GUI_Repcourte rc = new GUI_Repcourte(parent,((RC) q.getEnonce()).getTexte());
                    if (isGoodAnswer(q, rc.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = rc.getChronometre();
                    System.out.println(joueurs[i].getScore());
                    break;
            }
        }


        //Récuperation des joueurs avec le plus petit score
        Joueur[] joueursElimine = Tools.getJoueursLowestScore(joueurs);
        if(joueursElimine.length == 1){ //si il y en a un seul => fin de la phase
            System.out.println("departagé au score");
            JOptionPane.showMessageDialog(null, "Résultat :\n" +
                            joueurs[0].getNom() + " " + joueurs[0].getScore() + " points en " + tempsReponses[0].toString() + "\n" +
                            joueurs[1].getNom() + " " + joueurs[1].getScore() + " points en " + tempsReponses[1].toString() + "\n" +
                            joueurs[2].getNom() + " " + joueurs[2].getScore() + " points en " + tempsReponses[2].toString() + "\n" +
                            joueurs[3].getNom() + " " + joueurs[3].getScore() + " points en " + tempsReponses[3].toString() + "\n" +
                            "Le joueur éliminé est " +  joueursElimine[0].getNom()
                    , "Résultat de la phase", JOptionPane.INFORMATION_MESSAGE);

        }else{ //sinon departager les joueurs au chrono
            //todo pb car detemrinatiopn du lowest et pas greatest
            Chronometre lowestChrono = Tools.getLowestChronometer(tempsReponses); //recuperation du plus petit chrono
            ArrayList<Joueur> list = new ArrayList<>(Arrays.asList(joueursElimine)); //conversion du tab en list
            for (int i = 0; i < tempsReponses.length ; i++) {
                if (!(tempsReponses[i].equals(lowestChrono))){
                    list.remove(joueurs[i]); //suppression de tout les élèment dont le chrono n'est pas le plus petit
                }
            }
            joueursElimine = list.toArray(new Joueur[list.size()]);
            if(joueursElimine.length == 1){
                System.out.println("departagé au chrono");
                JOptionPane.showMessageDialog(null, "Résultat :\n" +
                                joueurs[0].getNom() + " " + joueurs[0].getScore() + " points en " + tempsReponses[0].toString() + "\n" +
                                joueurs[1].getNom() + " " + joueurs[1].getScore() + " points en " + tempsReponses[1].toString() + "\n" +
                                joueurs[2].getNom() + " " + joueurs[2].getScore() + " points en " + tempsReponses[2].toString() + "\n" +
                                joueurs[3].getNom() + " " + joueurs[3].getScore() + " points en " + tempsReponses[3].toString() + "\n" +
                                "Le joueur éliminé est " +  joueursElimine[0].getNom()
                        , "Résultat de la phase", JOptionPane.INFORMATION_MESSAGE);


            }else{
                //todo lancer phase de departage des joueurs
                System.out.println("departage");
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

    private int getJoueurElimine(Joueur[] joueurs, int[] chrono){
        int index = 0;
        for (int i = 1; i < joueurs.length ; i++) {
            if(joueurs[i].getScore() < joueurs[index].getScore()){
                index = i;
            }
            else if(joueurs[i].getScore() == joueurs[index].getScore()){
                if(chrono[i] > chrono[index]){
                    index = i;
                }
                else if(chrono[i] == chrono[index]){
                    index = -1;
                }
            }
        }
        return index;
    }



}
