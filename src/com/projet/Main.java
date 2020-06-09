package com.projet;

import com.projet.fenetre.*;
import com.projet.question.ListeQuestions;

public class Main {

    public static void main(String[] args) {
        //lancement du menu principal
//        GUI_MenuPrincipal menuPrincipal = new GUI_MenuPrincipal();


        ListeQuestions listeQuestions = new ListeQuestions();
        System.out.println(listeQuestions.getListeQuestion().toString());
        listeQuestions.serialize();
        ListeQuestions listeQuestions1 = ListeQuestions.deserialize();
        System.out.println(listeQuestions1.getListeQuestion().toString());
    }

}
