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
import java.util.*;

public class Phase1 implements Phase {
    private Themes theme;
    private ListeQuestions listeQuestionsAll;
    private List<Question> listeQuestionsPhase;
    private Joueur[] joueurs;
    private JFrame parent;

    public Phase1(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs, JFrame parent) {
        this.theme = theme;
        theme.selectionnerTheme();
        listeQuestionsAll = listeQuestions;
        this.listeQuestionsPhase = listeQuestions.getQuestionByThemeLevel(theme.getArrayTheme()[theme.getIndicateur()], 1);
        this.joueurs = joueurs;
        this.parent = parent;
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {
        Chronometre[]tempsReponses = new Chronometre[4];
        displayMessageRules();

        //affichage des questions aux joueurs
        askQuestionToPlayer(listeQuestionsPhase.size(),tempsReponses);

        //Récuperation des joueurs avec le plus petit score
        Joueur[] joueursElimine = Tools.getJoueursLowestScore(joueurs);
        if(joueursElimine.length == 1){ //si il y en a un seul => fin de la phase
            lancementPhase2(tempsReponses, joueursElimine[0]);
        }else{ //sinon departager les joueurs au chrono
            Chronometre lowestChrono = Tools.getGreatestChronometer(tempsReponses); //recuperation du plus petit chrono
            ArrayList<Joueur> list = new ArrayList<>(Arrays.asList(joueursElimine)); //conversion du tab en list
            for (int i = 0; i < tempsReponses.length ; i++) {
                if (!(tempsReponses[i].equals(lowestChrono))){
                    list.remove(joueurs[i]); //suppression de tout les élèment dont le chrono n'est pas le plus petit
                }
            }
            joueursElimine = list.toArray(new Joueur[list.size()]);
            if(joueursElimine.length == 1){
                lancementPhase2(tempsReponses, joueursElimine[0]);
            }else{
               PhaseDepartage phaseDepartage = new PhaseDepartage(theme, listeQuestionsAll, parent, 1, joueursElimine);
               phaseDepartage.phaseDeJeu();
               lancementPhase2(tempsReponses, phaseDepartage.getJoueurElimine());

            }
        }
    }

    private void displayMessageRules(){
        JOptionPane.showMessageDialog(null, "Phase 1 \nLes joueurs jouent à tour de rôle sur le thème : "
                + theme.getArrayTheme()[theme.getIndicateur()] +
                "\nChaque bonne réponse rapporte 2 points.\n" +
                "Ordre : " + joueurs[0].getNom() + ", "
                + joueurs[1].getNom() + ", "
                + joueurs[2].getNom() + ", "
                + joueurs[3].getNom(), "Régle de la phase", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayMessageJoueurElimine(String elimine, Chronometre[] tempsReponses){
        JOptionPane.showMessageDialog(null, "Résultat :\n" +
                        joueurs[0].getNom() + " " + joueurs[0].getScore() + " points en " + tempsReponses[0].toString() + "\n" +
                        joueurs[1].getNom() + " " + joueurs[1].getScore() + " points en " + tempsReponses[1].toString() + "\n" +
                        joueurs[2].getNom() + " " + joueurs[2].getScore() + " points en " + tempsReponses[2].toString() + "\n" +
                        joueurs[3].getNom() + " " + joueurs[3].getScore() + " points en " + tempsReponses[3].toString() + "\n" +
                        "Le joueur éliminé est " +  elimine
                , "Résultat de la phase", JOptionPane.INFORMATION_MESSAGE);
    }

    private void askQuestionToPlayer(int nbQuestion, Chronometre[] tempsReponses){
        //todo delete les system.out
        /** Modification direct de temps de réponse car shallow copy**/
        //affichage des questions
        for (int i = 0; i < joueurs.length ; i++) {
            int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);
            Question<?> q = listeQuestionsPhase.get(numQuestionSelected);
            switch (Tools.getQuestionType(q)){
                case "QCM" :
                    GUI_QCM qcm = new GUI_QCM(parent,((QCM) q.getEnonce()).getTexte(),theme.getArrayTheme()[theme.getIndicateur()], joueurs[i].getNom(), ((QCM) q.getEnonce()).getReponses());
                    if (Tools.isGoodAnswer(q, qcm.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = qcm.getChronometre();
                    break;
                case "VF":
                    GUI_VF vf = new GUI_VF(parent, ((VF) q.getEnonce()).getTexte(),theme.getArrayTheme()[theme.getIndicateur()], joueurs[i].getNom());
                    if (Tools.isGoodAnswer(q, vf.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = vf.getChronometre();
                    break;
                case "RC":
                    GUI_RC rc = new GUI_RC(parent,((RC) q.getEnonce()).getTexte(), theme.getArrayTheme()[theme.getIndicateur()], joueurs[i].getNom());
                    if (Tools.isGoodAnswer(q, rc.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = rc.getChronometre();
                    break;
            }
        }
    }

    private void lancementPhase2(Chronometre[] tempsReponses, Joueur jElimine) {
        displayMessageJoueurElimine(jElimine.getNom(), tempsReponses);
        int k=0;
        Joueur[] listeJoueurPhase2 = new Joueur[3];
        for(int i=0;i<4;i++){
            if(!jElimine.getNom().equals(joueurs[i].getNom())){
                listeJoueurPhase2[k] = joueurs[i];
                k++;
            }
        }
        ListeQuestions ListePhase2 = new ListeQuestions();
        Phase2 phase2 = new Phase2(theme, ListePhase2, listeJoueurPhase2);
        phase2.phaseDeJeu();
    }
}
