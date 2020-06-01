package com.projet.Fenetre;

import com.projet.Joueur.EnsJoueurs;
import com.projet.Phase.Phase1;
import com.projet.Question.ListeQuestions;
import com.projet.Themes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    //todo placer les bouttons et implementer les fonctionnalités du sujet, respectivement Point n° 1,3,4,5,6,7,8 dans le sujet page 6
    private JButton btnTheme, btnQuestion, btnAjouteQuestion, btnSupprimerQuestion, btnAfficherJoueurs, btnStart, btnQuitter;
    private ListeQuestions listeQuestions;
    private Themes themes;
    private EnsJoueurs ensJoueurs;

    public MenuPrincipal() {
        //creation de la liste des questions du jeu
        listeQuestions = new ListeQuestions();

        //creation des themes + selection du premier theme
        themes = new Themes();
        themes.selectionnerTheme();

        //creation de la liste des joueurs
        ensJoueurs = new EnsJoueurs();
        ensJoueurs.creer();

        //selection des 4 joueurs de la partie
        for (int i = 0; i < 4; i++) {
            ensJoueurs.selectionnerJoueur();
        }


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
        addListenerStart();
    }

    private JPanel placeAll(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.insets = new Insets(5,5,5,5);

        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(btnStart,cs);

        return panel;
    }

    private void addListenerStart(){
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Phase1 phase1 = new Phase1(themes, listeQuestions, ensJoueurs.getJoueurSelectionne(), MenuPrincipal.this);
                phase1.phaseDeJeu();
            }
        });
    }
}
