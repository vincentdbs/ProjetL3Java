package com.projet.phase;

import com.projet.Chronometre;
import com.projet.fenetre.question.GUI_QCM;
import com.projet.fenetre.question.GUI_RC;
import com.projet.fenetre.question.GUI_VF;
import com.projet.joueur.Joueur;
import com.projet.question.ListeQuestions;
import com.projet.question.Question;
import com.projet.question.Type.QCM;
import com.projet.question.Type.RC;
import com.projet.question.Type.VF;
import com.projet.Themes;
import com.projet.Tools;

import javax.swing.*;
import java.util.*;

public class Phase1 implements Phase {
    private Themes theme;
    private ListeQuestions listeQuestionsAll;
    private Joueur[] joueurs;
    private Joueur[] vainqueurs;
    private JFrame parent;

    public Phase1(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs, JFrame parent) {
        this.theme = theme;
        theme.selectionnerTheme();
        listeQuestionsAll = listeQuestions;
        this.joueurs = joueurs;
        this.parent = parent;
        vainqueurs = new Joueur[3];
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {
        Chronometre[]tempsReponses = new Chronometre[4];
        displayMessageRules();

        //affichage des questions aux joueurs
        askQuestionToPlayer(tempsReponses);

        //Récuperation des joueurs avec le plus petit score
        Joueur[] joueurElimine = Tools.getJoueurElimine(tempsReponses, joueurs);
        if(joueurElimine.length == 1){ //si un seul joueur => phase suivante avec les 3 autres
            lancementPhase2(tempsReponses, joueurElimine[0]);
        }else{ //sinon phase de departages avec les autres
            PhaseDepartage phaseDepartage = new PhaseDepartage(theme, listeQuestionsAll, parent, 1, joueurElimine);
            phaseDepartage.phaseDeJeu();
            lancementPhase2(tempsReponses, phaseDepartage.getJoueurElimine());
        }
    }

    /**
     * affichage du message de présentation des régles de la phase1
     */
    private void displayMessageRules(){
        JOptionPane.showMessageDialog(null, "Phase 1 :\nQuestion de niveau 1." +
                "\nChaque bonne réponse rapporte 2 points.\n" +
                "Ordre : " + joueurs[0].getNom() + ", "
                + joueurs[1].getNom() + ", "
                + joueurs[2].getNom() + ", "
                + joueurs[3].getNom(), "Régle de la phase", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * affichage du message de récapitulation des régles de la phase1
     */
    private void displayMessageJoueurElimine(String elimine, Chronometre[] tempsReponses){
        JOptionPane.showMessageDialog(null, "Résultat :\n" +
                        joueurs[0].getNom() + " " + joueurs[0].getScore() + " points en " + tempsReponses[0].toString() + "\n" +
                        joueurs[1].getNom() + " " + joueurs[1].getScore() + " points en " + tempsReponses[1].toString() + "\n" +
                        joueurs[2].getNom() + " " + joueurs[2].getScore() + " points en " + tempsReponses[2].toString() + "\n" +
                        joueurs[3].getNom() + " " + joueurs[3].getScore() + " points en " + tempsReponses[3].toString() + "\n" +
                        "Le joueur éliminé est " +  elimine
                , "Résultat de la phase", JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Affichage des questions pour chaque joueur + remplissage du chronometre associé du joueur
     */
    private void askQuestionToPlayer(Chronometre[] tempsReponses){
        /** Modification direct de temps de réponse car shallow copy**/
        //affichage des questions
        for (int i = 0; i < joueurs.length ; i++) {
            List<Question> listeQuestionsJoueuri = listeQuestionsAll.getQuestionByThemeLevel(theme.getArrayTheme()[theme.selectionnerNextTheme()], 1); //recuperation de la liste des questions de niveau 1 du théme donné
            Question<?> q = listeQuestionsJoueuri.get((int) ((Math.random() * listeQuestionsJoueuri.size())%listeQuestionsJoueuri.size())); //récupération d'une question aléatoirement parmi les questions
            switch (Tools.getQuestionType(q)){
                case "QCM" :
                    GUI_QCM qcm = new GUI_QCM(parent,((QCM) q.getEnonce()).getTexte(),theme.getArrayTheme()[theme.getIndicateur()], joueurs[i].getNom(), ((QCM) q.getEnonce()).getReponses());
                    if (q.saisir(qcm.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = qcm.getChronometre();
                    break;
                case "VF":
                    GUI_VF vf = new GUI_VF(parent, ((VF) q.getEnonce()).getTexte(),theme.getArrayTheme()[theme.getIndicateur()], joueurs[i].getNom());
                    if (q.saisir(vf.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = vf.getChronometre();
                    break;
                case "RC":
                    GUI_RC rc = new GUI_RC(parent,((RC) q.getEnonce()).getTexte(), theme.getArrayTheme()[theme.getIndicateur()], joueurs[i].getNom());
                    if (q.saisir(rc.getAnswer())){
                        joueurs[i].majScore(2);
                    }
                    tempsReponses[i] = rc.getChronometre();
                    break;
            }
        }
    }

    /**
     * Lancement de la phase 2
     */
    private void lancementPhase2(Chronometre[] tempsReponses, Joueur jElimine) {
        displayMessageJoueurElimine(jElimine.getNom(), tempsReponses);
        int k=0;
        for(int i=0;i<4;i++){ //recuperation des 3 joueurs pour la phase 2
            if(!jElimine.getNom().equals(joueurs[i].getNom())){
                vainqueurs[k] = joueurs[i];
                k++;
            }
        }
    }

    public Joueur[] getVainqueurs() {
        return vainqueurs;
    }
}
