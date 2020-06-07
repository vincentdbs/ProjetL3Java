package com.projet.phase;

import com.projet.Chronometre;
import com.projet.fenetre.GUI_ThemeSelection;
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

public class Phase2 implements Phase{

    private Themes themeListe;
    private ListeQuestions listeQuestionsAll;
    private Themes themeActuel;
    private HashMap<String, ArrayList<String>> ThemeParParticipant;
    private List<Question> listeQuestions;
    private HashMap<String, List<Question>> listeQuestionstest = new HashMap<>();
    private Joueur[] joueurs;
    private Joueur[] vainqueurs;
    private JFrame parent;

    public Phase2(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs) {


        this.themeListe = new Themes(selectionThemes(theme));
        listeQuestionsAll = listeQuestions;
        for(int i=0; i<6; i++){
        listeQuestionstest.put(themeListe.getArrayTheme()[i], listeQuestions.getQuestionByThemeLevel(themeListe.getArrayTheme()[i], 2));
        }

        this.vainqueurs = new Joueur[2];
        this.joueurs = joueurs;
        this.parent = null;
    }


    @Override
    public void selectionnerJoueur() {

    }

    public String[] selectionThemes(Themes theme){
        int[] indic = theme.selectionnerNTheme(6);
        String[] sixThemes = new String[6];
        int k = 0;
        for(int i=0; i<6; i++){
            for(int j=0;j<10;j++){
                if(indic[i] == j){
                    sixThemes[k] = theme.getArrayTheme()[j];
                    k++;
                }
            }
        }

        return sixThemes;
    }

    @Override
    public void phaseDeJeu() {

        messagePhase2();
        choixThemesJoueur();

    }

    public void messagePhase2(){
        JOptionPane.showMessageDialog(null, "Phase 2 \nLes joueurs jouent à tour de rôle sur les deux thèmes qu'ils auront choisis parmi 6 proposés."
                +
                "\nChaque bonne réponse rapporte 3 points. Vous aurez 2 questions.\n" +
                "Ordre : " + joueurs[0].getNom() + ", "
                + joueurs[1].getNom() + ", "
                + joueurs[2].getNom(), "Règle de la phase", JOptionPane.INFORMATION_MESSAGE);
    }

    public void choixThemesJoueur(){
        GUI_ThemeSelection theme = new GUI_ThemeSelection(themeListe, joueurs, parent);
        questions(theme.getThemeJoueur());
    }

    public void questions(LinkedHashMap<String, ArrayList<String>> listeTheme){

        int nbQuestion = 2; //Modification nécessaire quand on aura plus de questions

        Chronometre[]tempsReponses = new Chronometre[3];
        for (int i = 0; i < tempsReponses.length ; i++) {
            tempsReponses[i] = new Chronometre();
        }
        ArrayList<String> theme = new ArrayList<>();

        //affichage des questions
        for(int j=0; j<2; j++){ //Deux tours dans la phase 2
            for (int i = 0; i < joueurs.length ; i++) {
                int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);

                for (Map.Entry<String, List<Question>> parcours : listeQuestionstest.entrySet()) {

                    String key = parcours.getKey();
                    theme = listeTheme.get(listeTheme.keySet().toArray()[i]);

                    if(key.equals(theme.get(j))){
                        listeQuestions  = parcours.getValue();
                    }

                }



                Question<?> q = listeQuestions.get(numQuestionSelected);
                switch (Tools.getQuestionType(q)){
                    case "QCM" :
                        GUI_QCM qcm = new GUI_QCM(parent,((QCM) q.getEnonce()).getTexte(), theme.get(j), joueurs[i].getNom(), ((QCM) q.getEnonce()).getReponses());
                        if (q.saisir(qcm.getAnswer())){
                            joueurs[i].majScore(3);
                        }
                        tempsReponses[i].add(qcm.getChronometre());
                        break;
                    case "VF":
                        GUI_VF vf = new GUI_VF(parent, ((VF) q.getEnonce()).getTexte(),  theme.get(j), joueurs[i].getNom());
                        if (q.saisir(vf.getAnswer())){
                            joueurs[i].majScore(3);
                        }
                        tempsReponses[i].add(vf.getChronometre());
                        break;
                    case "RC":
                        GUI_RC rc = new GUI_RC(parent,((RC) q.getEnonce()).getTexte(), theme.get(j), joueurs[i].getNom());
                        if (q.saisir(rc.getAnswer())){
                            joueurs[i].majScore(3);
                        }
                        tempsReponses[i].add(rc.getChronometre());
                        break;
                }
            }
        }

        Joueur[] joueurElimine = Tools.getJoueurElimine(tempsReponses, joueurs);
        if(joueurElimine.length == 1){ //si un seul joueur => phase suivante avec les 3 autres
            lancementPhase3(tempsReponses, joueurElimine[0]);
        }else{ //sinon phase de departages avec les autres
            Themes themedepartage = new Themes();
            PhaseDepartage phaseDepartage = new PhaseDepartage(themedepartage, listeQuestionsAll, parent, 2, joueurElimine);
            phaseDepartage.phaseDeJeu();
            lancementPhase3(tempsReponses, phaseDepartage.getJoueurElimine());
        }

    }

    private void displayMessageJoueurElimine(String elimine, Chronometre[] tempsReponses){
        JOptionPane.showMessageDialog(null, "Résultat :\n" +
                        joueurs[0].getNom() + " " + joueurs[0].getScore() + " points en " + tempsReponses[0].toString() + "\n" +
                        joueurs[1].getNom() + " " + joueurs[1].getScore() + " points en " + tempsReponses[1].toString() + "\n" +
                        joueurs[2].getNom() + " " + joueurs[2].getScore() + " points en " + tempsReponses[2].toString() + "\n" +
                        "Le joueur éliminé est " +  elimine
                , "Résultat de la phase", JOptionPane.INFORMATION_MESSAGE);
    }



    private void lancementPhase3(Chronometre[] tempsReponses, Joueur jElimine) {
        displayMessageJoueurElimine(jElimine.getNom(), tempsReponses);
        int k=0;
        for(int i=0;i<3;i++){
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
