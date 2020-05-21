package com.projet;

import com.projet.Joueur.EnsJoueurs;
import com.projet.Joueur.Joueur;
import com.projet.Question.*;
import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //todo fonction d'initialisation des questions et joueurs
        //testTheme();
        testListeQuestion();
//        testJoueur();
        phase1();
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
        listeQuestions.ajouterQuestion(new Question<>(1,"Sport", a));
        listeQuestions.ajouterQuestion(new Question<>(3,"Cinema", b));
        listeQuestions.ajouterQuestion(new Question<>(2,"Art", c));

        listeQuestions.afficherListe();

    }


    public static void testJoueur(){
        Joueur joueur = new Joueur("Vincent", "gagnant", 0);
        Joueur joueur2 = new Joueur("Baptiste", "séléctionné");
        Joueur joueur3 = new Joueur("Tristan");

        joueur.afficher();
        joueur2.afficher();
        joueur3.afficher();
    }

    public static void phase1(){
        //creation de la liste des joueurs
        EnsJoueurs ensJoueurs = new EnsJoueurs();
        ensJoueurs.creer();
        ensJoueurs.afficher();

        //recuperation des 4 joueurs de la partie
        //todo gerer si plusieurs fois le même joueur qui est selectionné
        ArrayList<Joueur> ensJoueurSelectionne = new ArrayList<>();
        for (int i = 0; i < 4 ; i++) {
            ensJoueurSelectionne.add(ensJoueurs.selectionnerJoueur());
            ensJoueurSelectionne.get(i).changerEtat("selectionne");
        }

        //creation des themes + selection du premier theme
        Themes themes = new Themes();
        themes.selectionnerTheme();

        //todo remplir la liste des questions avec nos questions
        ListeQuestions listeQuestions = new ListeQuestions();





    }
}
