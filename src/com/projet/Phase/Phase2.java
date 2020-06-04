package com.projet.Phase;

import com.projet.Chronometre;
import com.projet.Fenetre.GUI_Theme1;
import com.projet.Fenetre.GUI_ThemeSelection;
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

public class Phase2 implements Phase{

    private Themes themeListe;
    private static ListeQuestions listeQuestionsAll;
    private  static Themes themeActuel;
    private static HashMap<String, ArrayList<String>> ThemeParParticipant;
    private static List<Question> listeQuestions;
    private static HashMap<String, List<Question>> listeQuestionstest = new HashMap<>();
    private static Joueur[] joueurs;
    private static JFrame parent;

    public Phase2(Themes theme, ListeQuestions listeQuestions, Joueur[] joueurs) {


        this.themeListe = new Themes(selectionThemes(theme));
        listeQuestionsAll = listeQuestions;
        for(int i=0; i<6; i++){
        listeQuestionstest.put(themeListe.getArrayTheme()[i], listeQuestions.getQuestionByThemeLevel(themeListe.getArrayTheme()[i], 2));
        }


        this.joueurs = joueurs;
        this.parent = null;
    }


    @Override
    public void selectionnerJoueur() {

    }

    public String[] selectionThemes(Themes theme){
        int[] indic = theme.selectionnerSixThemes();
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
        GUI_ThemeSelection theme = new GUI_ThemeSelection(themeListe, joueurs);
        
    }

    public static void questions(TreeMap<String, ArrayList<String>> listeTheme){

        int nbQuestion = 2; //Modification nécessaire quand on aura plus de questions

        Chronometre[]tempsReponses = new Chronometre[3];
        ArrayList<String> theme = new ArrayList<>();

        //affichage des questions
        for(int j=0; j<2; j++){ //Deux tours dans la phase 2
            for (int i = 0; i < joueurs.length ; i++) {
                int numQuestionSelected = (int) ((Math.random() * nbQuestion)%nbQuestion);

                for (Map.Entry<String, List<Question>> parcours : listeQuestionstest.entrySet()) {

                    String key = parcours.getKey();
                    theme = listeTheme.get(listeTheme.keySet().toArray()[i]);

                    if(key.equals(theme.get(j))){

                        List<Question> values = parcours.getValue();
                        listeQuestions = values;
                    }

                }



                Question<?> q = listeQuestions.get(numQuestionSelected);
                switch (Tools.getQuestionType(q)){
                    case "QCM" :
                        GUI_QCM qcm = new GUI_QCM(parent,((QCM) q.getEnonce()).getTexte(), theme.get(j), joueurs[i].getNom(), ((QCM) q.getEnonce()).getReponses());
                        if (q.saisir(qcm.getAnswer())){
                            joueurs[i].majScore(3);
                        }
                        tempsReponses[i] = qcm.getChronometre();
                        break;
                    case "VF":
                        GUI_VF vf = new GUI_VF(parent, ((VF) q.getEnonce()).getTexte(),  theme.get(j), joueurs[i].getNom());
                        if (q.saisir(vf.getAnswer())){
                            joueurs[i].majScore(3);
                        }
                        tempsReponses[i] = vf.getChronometre();
                        break;
                    case "RC":
                        GUI_RC rc = new GUI_RC(parent,((RC) q.getEnonce()).getTexte(), theme.get(j), joueurs[i].getNom());
                        if (q.saisir(rc.getAnswer())){
                            joueurs[i].majScore(3);
                        }
                        tempsReponses[i] = rc.getChronometre();
                        break;
                }
            }
        }

        Joueur[] joueursElimine = Tools.getJoueursLowestScore(joueurs);
        if(joueursElimine.length == 1){ //si il y en a un seul => fin de la phase
            lancementPhase3(tempsReponses, joueursElimine[0]);
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
                lancementPhase3(tempsReponses, joueursElimine[0]);
            }else{
                //Phase de départage
            }
        }

    }

    private static void displayMessageJoueurElimine(String elimine, Chronometre[] tempsReponses){
        JOptionPane.showMessageDialog(null, "Résultat :\n" +
                        joueurs[0].getNom() + " " + joueurs[0].getScore() + " points en " + tempsReponses[0].toString() + "\n" +
                        joueurs[1].getNom() + " " + joueurs[1].getScore() + " points en " + tempsReponses[1].toString() + "\n" +
                        joueurs[2].getNom() + " " + joueurs[2].getScore() + " points en " + tempsReponses[2].toString() + "\n" +
                        "Le joueur éliminé est " +  elimine
                , "Résultat de la phase", JOptionPane.INFORMATION_MESSAGE);
    }


    private static void lancementPhase3(Chronometre[] tempsReponses, Joueur jElimine) {
        displayMessageJoueurElimine(jElimine.getNom(), tempsReponses);
        int k=0;
        Joueur[] listeJoueurPhase3 = new Joueur[2];
        for(int i=0;i<3;i++){
            if(!jElimine.getNom().equals(joueurs[i].getNom())){
                listeJoueurPhase3[k] = joueurs[i];
                k++;
            }
        }
        ListeQuestions ListePhase3 = new ListeQuestions();
        Phase3 phase3 = new Phase3(ListePhase3, listeJoueurPhase3);
        phase3.phaseDeJeu();
        //Phase 3 à lancer
    }

}
