package com.projet;

import com.projet.Joueur.EnsJoueurs;
import com.projet.Joueur.Joueur;
import com.projet.Phase.Phase;
import com.projet.Phase.Phase1;
import com.projet.Question.*;
import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //todo fonction d'initialisation des questions et joueurs
//        testQcm();
//        testVf();
//        testRc();
//        testQuestion();
//        testListeQuestion();
//        testTheme();
//        testJoueur();
        jeu();
    }

    public static void testQcm(){
        QCM a = new QCM("Question QCM ", 2, new String[]{"bonjour", "hello", "hola"});
        System.out.println(a.toString());
        System.out.println("La bonne réponse est : " + a.getBonneReponse() + "\n");
    }

    public static void testVf(){
        VF b = new VF("Question vrai/faux", true);
        System.out.println(b.toString());
        System.out.println("La bonne réponse est : " + b.isReponse() + "\n" );
    }

    public static void testRc(){
        RC c = new RC("Question courte", "ceci est la vonne réponse");
        System.out.println(c.toString());
        System.out.println("La bonne réponse est : " + c.getBonneReponse() + "\n");
    }

    public static void testQuestion(){
        // exemple affichage + utilisation de la classe question
        QCM a = new QCM("Question de test ", 2, new String[]{"vrai", "faux", "je sais pas"});
        VF b = new VF("Jelloh", true);
        RC c = new RC("Question courte", "ceci est la vonne réponse");

        ArrayList<Question<?>> list = new ArrayList<>();
        list.add(new Question<>(1, "sport", a));
        list.add(new Question<>(2, "culture", b));
        list.add(new Question<>(3, "divertissement", c));
        for (Question<?> t: list) {
            t.afficher();
            System.out.println();
        }
    }

    public static void testTheme(){
        Themes themes = new Themes();
        themes.afficher();
        for (int i = 0; i < 5 ; i++) {
            themes.selectionnerTheme();
            themes.afficher();
        }
        themes.modifierTheme(4, "test");
        themes.afficher();
        themes.modifierTheme("test", "coucou");
        themes.afficher();

    }

    public static void testListeQuestion(){
        QCM a = new QCM("Question de test ", 2, new String[]{"vrai", "faux", "je sais pas"});
        VF b = new VF("Jelloh", true);
        RC c = new RC("Question courte", "ceci est la vonne réponse");

        ListeQuestions listeQuestions = new ListeQuestions();
        listeQuestions.afficherListe();

    }


    public static void testJoueur(){
        Joueur joueur = new Joueur("Vincent", "gagnant", 0);
        Joueur joueur2 = new Joueur("Baptiste", "séléctionné");
        Joueur joueur3 = new Joueur("Tristan");

        System.out.println();
        joueur.afficher();
        joueur2.afficher();
        joueur3.afficher();
    }


    public static void jeu(){
        //creation de la liste des questions du jeu
        ListeQuestions listeQuestions = new ListeQuestions();


        //creation des themes + selection du premier theme
        Themes themes = new Themes();
        themes.selectionnerTheme();

        //creation de la liste des joueurs
        EnsJoueurs ensJoueurs = new EnsJoueurs();
        ensJoueurs.creer();

        //selection des 4 joueurs de la partie
        for (int i = 0; i < 4; i++) {
            ensJoueurs.selectionnerJoueur();
        }

        Phase1 phase1 = new Phase1(themes, listeQuestions, ensJoueurs.getJoueurSelectionne());
        phase1.phaseDeJeu();



    }

}
