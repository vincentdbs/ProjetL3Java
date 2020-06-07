package com.projet.phase;

import com.projet.Chronometre;
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
import java.util.*;

public class Phase3 implements Phase {

    private Themes listeTroisThemes;
    private Joueur[] joueurs;
    private Joueur vainqueur;
    private ListeQuestions listeQuestionsAll;
    private static List<Question> listeQuestions;
    private LinkedHashMap<String, List<Question>> listeQuestionsPhase = new LinkedHashMap<>();

    private JFrame parent = null;

    public Phase3(ListeQuestions listeQuestions, Joueur[] tabJoueurs){

        this.listeTroisThemes = new Themes(selectionThemesPhase3()); //On stocke les trois thèmes qu'on a choisi
        this.joueurs = tabJoueurs;
        this.listeQuestionsAll = listeQuestions;
        for(int i=0; i<3; i++){
          listeQuestionsPhase.put(listeTroisThemes.getArrayTheme()[i], listeQuestions.getQuestionByThemeLevel(listeTroisThemes.getArrayTheme()[i], 3));
          //On stocke les questions associées à chaque thème
        }

    }

    public String[] selectionThemesPhase3(){
        String[] troisThemes = new String[3]; //Sélection des trois thèmes.
        troisThemes[0] = "Histoire";
        troisThemes[1] = "Sciences";
        troisThemes[2] = "Divertissements";

        return troisThemes;
    }

    @Override
    public void selectionnerJoueur() {

    }

    @Override
    public void phaseDeJeu() {
        Chronometre[]tempsReponses = new Chronometre[2];
        for (int i = 0; i < tempsReponses.length ; i++) {
            tempsReponses[i] = new Chronometre();
        }
        displayMessageRulesPhase3();

        for(int j=0;j<3;j++){
        askQuestionToPlayerPhase3(tempsReponses, j);} //Chaque joueur aura une question sur chaque thème. Le j permet de définir le thème actuel

        Joueur[] joueurElimine = Tools.getJoueurElimine(tempsReponses, joueurs);
        if(joueurElimine.length == 1){ //si un seul joueur => phase suivante avec les 3 autres
            annonceGagnant(tempsReponses, joueurElimine[0]);
        }else{ //sinon phase de departages avec les autres
            Themes themedepartage = new Themes();
            PhaseDepartage phaseDepartage = new PhaseDepartage(themedepartage, listeQuestionsAll, parent, 3, joueurElimine);
            phaseDepartage.phaseDeJeu();
            annonceGagnant(tempsReponses, phaseDepartage.getJoueurElimine());
        }
    }

    private void displayMessageRulesPhase3(){
        JOptionPane.showMessageDialog(null, "Phase 1 \nLes joueurs jouent à tour de rôle sur les thèmes suivants : Histoire, Divertissements, Sciences"
                +
                "\nChaque bonne réponse rapporte 5 points.\n" +
                "Ordre : " + joueurs[0].getNom() + ", "
                + joueurs[1].getNom(), "Règles de la phase", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayMessageJoueurEliminePhase3(String elimine, Chronometre[] tempsReponses){
        JOptionPane.showMessageDialog(null, "Résultat :\n" +
                        joueurs[0].getNom() + " " + joueurs[0].getScore() + " points en " + tempsReponses[0].toString() + "\n" +
                        joueurs[1].getNom() + " " + joueurs[1].getScore() + " points en " + tempsReponses[1].toString() + "\n" +
                        "Le joueur éliminé est " +  elimine
                , "Résultat de la phase", JOptionPane.INFORMATION_MESSAGE);
    }

    private void askQuestionToPlayerPhase3(Chronometre[] tempsReponses, int j){
        /* Modification direct de temps de réponse car shallow copy**/
        //affichage des questions
        for (int i = 0; i < joueurs.length ; i++) {





            for (Map.Entry<String, List<Question>> parcours : listeQuestionsPhase.entrySet()) { //On sélectionne les questions du thème en cours : (Histoire puis Sciences puis Divertissements)

                String key = parcours.getKey();
                if(key.equals(listeQuestionsPhase.keySet().toArray()[j])){


                    listeQuestions = parcours.getValue();
                }

            }


            int nbQuestion = listeQuestions.size();
            int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);


            Question<?> q = listeQuestions.get(numQuestionSelected);
            switch (Tools.getQuestionType(q)){
                case "QCM" :
                    GUI_QCM qcm = new GUI_QCM(parent,((QCM) q.getEnonce()).getTexte(),listeTroisThemes.getArrayTheme()[j], joueurs[i].getNom(), ((QCM) q.getEnonce()).getReponses());
                    if (q.saisir(qcm.getAnswer())){
                        joueurs[i].majScore(5);
                    }
                    tempsReponses[i].add(qcm.getChronometre());
                    break;
                case "VF":
                    GUI_VF vf = new GUI_VF(parent, ((VF) q.getEnonce()).getTexte(),listeTroisThemes.getArrayTheme()[j], joueurs[i].getNom());
                    if (q.saisir(vf.getAnswer())){
                        joueurs[i].majScore(5);
                    }
                    tempsReponses[i].add(vf.getChronometre());
                    break;
                case "RC":
                    GUI_RC rc = new GUI_RC(parent,((RC) q.getEnonce()).getTexte(), listeTroisThemes.getArrayTheme()[j], joueurs[i].getNom());
                    if (q.saisir(rc.getAnswer())){
                        joueurs[i].majScore(5);
                    }
                    tempsReponses[i].add(rc.getChronometre());
                    break;
            }
        }
    }

    private void annonceGagnant(Chronometre[] tempsReponses, Joueur jElimine){
        displayMessageJoueurEliminePhase3(jElimine.getNom(), tempsReponses);

        for(int i=0;i<2;i++){
            if(!jElimine.getNom().equals(joueurs[i].getNom())){
                vainqueur = joueurs[i];
                JOptionPane.showMessageDialog(null, "Résultat :\n" +
                                "Le joueur gagnant est " +  joueurs[i].getNom() + " avec un total de " + joueurs[i].getScore() + " points \n"
                        , "Gagnant du jeu", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }
}

