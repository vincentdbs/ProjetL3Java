package com.projet.Fenetre;

import com.projet.Joueur.EnsJoueurs;
import com.projet.Joueur.Joueur;
import com.projet.Phase.Phase1;
import com.projet.Phase.Phase2;
import com.projet.Phase.Phase3;
import com.projet.Question.ListeQuestions;
import com.projet.Themes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_MenuPrincipal extends JFrame {
    //todo placer les bouttons et implementer les fonctionnalités du sujet, respectivement Point n° 1,3,4,5,6,7,8 dans le sujet page 6
    private JButton btnTheme, btnQuestion, btnAjouteQuestion, btnSupprimerQuestion, btnAfficherJoueurs, btnStart, btnQuitter;
    private JButton btnGrandJeu;
    private ListeQuestions listeQuestions;
    private Themes themes;


    public GUI_MenuPrincipal() {
        //creation de la liste des questions du jeu
        listeQuestions = new ListeQuestions();

        //creation des themes + selection du premier theme
        themes = new Themes();




        setSize(300,200);
        setTitle("Menu principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initiate();
        getContentPane().add(placeAll());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initiate(){
        btnStart = new JButton("Jouer");
        btnTheme = new JButton("Afficher thèmes");
        btnAfficherJoueurs = new JButton("Afficher joueurs");
        btnGrandJeu = new JButton("Grand Jeu");
        btnQuitter = new JButton("Quitter");
        addListenerStart();
        addListenerTheme();
        addListenerJoueur();
        addListenerQuitter();
        addListenerGrandJeu();

    }

    private JPanel placeAll(){
        JPanel panel = new JPanel();
        panel.add(btnStart);
        panel.add(btnGrandJeu);
        panel.add(btnTheme);
        panel.add(btnAfficherJoueurs);
        panel.add(btnQuitter);
        return panel;
    }

    private void addListenerStart(){
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Phase1 phase1 = new Phase1(themes, listeQuestions, createEnsJoueur().getJoueurSelectionne(), GUI_MenuPrincipal.this);
                phase1.phaseDeJeu();
            }
        });
    }

    private void addListenerTheme(){
        btnTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_Theme1 theme = new GUI_Theme1(themes);
            }
        });
    }

    private void addListenerJoueur(){
        btnAfficherJoueurs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_Scores scores = new GUI_Scores(createEnsJoueur());
            }
        });
    }

    private void addListenerQuitter(){
        btnQuitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void addListenerGrandJeu(){
        btnGrandJeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnsJoueurs ensJoueurs = new EnsJoueurs();
                ensJoueurs.creer();
                for (int i = 0; i < 3 ; i++) {
                    System.out.println("Partie " + i);
                    for (Joueur s: getFourJoueur(ensJoueurs)) {
                        System.out.println(s);
                    }
                }

//                EnsJoueurs ensJoueurs = new EnsJoueurs();
//                ensJoueurs.creer();
//                Joueur[] vainqueurs = new Joueur[3];
//                //lancement de 4 parties
//                for (int i = 0; i < 3; i++) {
//                    Phase1 phase1 = new Phase1(themes, listeQuestions, getFourJoueur(ensJoueurs), GUI_MenuPrincipal.this);
//                    phase1.phaseDeJeu();
//                    Phase2 phase2 = new Phase2();
//                    phase2.phaseDeJeu();
//                    Phase3 phase3 = new Phase3();
//                    phase3.phaseDeJeu();
//                    vainqueurs[i] = phase3.getVainqueur();
//                }
//                //lancement d'une phase 2 puis 3 à partir des gagnants
//                Phase2 phase2 = new Phase2();
//                phase2.phaseDeJeu();
//                Phase3 phase3 = new Phase3();
//                phase3.phaseDeJeu();
//                JOptionPane.showMessageDialog(null, "Le gagnant du grand Jeu est " + phase3.getVainqueur().getNom(),"Grand Gagnant" , JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private EnsJoueurs createEnsJoueur(){
        //creation de la liste des joueurs
        EnsJoueurs ensJoueurs = new EnsJoueurs();
        ensJoueurs.creer();
        //selection des 4 joueurs de la partie
        for (int i = 0; i < 4; i++) {
            ensJoueurs.selectionnerJoueur();
        }
        return ensJoueurs;
    }

    private Joueur[] getFourJoueur(EnsJoueurs ensJoueurs){
        Joueur[] joueur = new Joueur[4];
        //selection des 4 joueurs de la partie
        for (int i = 0; i < 4; i++) {
            ensJoueurs.selectionnerJoueur();
            joueur[i] = ensJoueurs.getLastJoueurSelectionne();
        }
        return joueur;
    }
}
