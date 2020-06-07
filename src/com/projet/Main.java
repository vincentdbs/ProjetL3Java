package com.projet;

import com.projet.fenetre.*;

public class Main {

    public static void main(String[] args) {
        //todo supprimer toute les méthodes et attributs non utilisés mais gardé celle demandées dans le sujet
        //todo verifier que tout les switch d'introspection du type de question analyse les bons noms de packages (sans majuscule après commit ceb4127)
        //lancement du menu principal
        GUI_MenuPrincipal menuPrincipal = new GUI_MenuPrincipal();
//        test();
    }

    public static void test(){
        Chronometre c1 = new Chronometre(1200, 50, 24, 2);
        Chronometre c2 = new Chronometre(900,8,10,4);
        System.out.println(c1.toString());
        System.out.println(c2.toString());
        c1.add(c2);
        System.out.println(c1.toString());
    }
}
