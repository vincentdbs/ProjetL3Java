package com.projet.fenetre;

import com.projet.joueur.EnsJoueurs;
import com.projet.joueur.Joueur;
import com.projet.phase.Phase1;
import com.projet.phase.Phase2;
import com.projet.phase.Phase2GrandJeu;
import com.projet.phase.Phase3;
import com.projet.question.ListeQuestions;
import com.projet.Themes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_MenuPrincipal extends JFrame {
    //todo placer les bouttons et implementer les fonctionnalités du sujet, respectivement Point n° 1,3,4 (à faire),5 (à faire),6,7,8 dans le sujet page 6
    //  pour ajouter et supprimer => faire l'action correspondante et récrire dans le fichier
    private JButton btnTheme, btnQuestion, btnAjouteQuestion, btnSupprimerQuestion, btnAfficherJoueurs, btnStart, btnQuitter;
    private JButton btnGrandJeu;
    private ListeQuestions listeQuestions;
    private Themes themes;


    public GUI_MenuPrincipal() {
        //creation de la liste des questions du jeu
        listeQuestions = new ListeQuestions();

        //creation des themes + selection du premier theme
        themes = new Themes();




        setSize(300,150);
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
        btnQuestion = new JButton("Afficher questions");
        btnAfficherJoueurs = new JButton("Afficher joueurs");
        btnGrandJeu = new JButton("Grand Jeu");
        btnQuitter = new JButton("Quitter");
        addListenerStart();
        addListenerTheme();
        addListenerJoueur();
        addListenerQuitter();
        addListenerGrandJeu();
        addListenerAfficherQuestion();

    }

    private JPanel placeAll(){
        JPanel panel = new JPanel();
        panel.add(btnStart);
        panel.add(btnGrandJeu);
        panel.add(btnTheme);
        panel.add(btnQuestion);
        panel.add(btnAfficherJoueurs);
        panel.add(btnQuitter);
        return panel;
    }

    private void addListenerStart(){
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnsJoueurs ensJoueurs = new EnsJoueurs();
                ensJoueurs.creer();
                Phase1 phase1 = new Phase1(themes, listeQuestions, getFourJoueur(ensJoueurs), GUI_MenuPrincipal.this);
                phase1.phaseDeJeu();
                Phase2 phase2 = new Phase2(themes, listeQuestions, phase1.getVainqueurs());
                phase2.phaseDeJeu();
                Phase3 phase3 = new Phase3(listeQuestions, phase2.getVainqueurs());
                phase3.phaseDeJeu();
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
                Joueur[] vainqueurs = new Joueur[3];
                //lancement de 4 parties
                for (int i = 0; i < 3; i++) {
                    JOptionPane.showMessageDialog(null, "Partie n°" + (i+1), "Partie n°" + (i+1), JOptionPane.INFORMATION_MESSAGE);
                    Phase1 phase1 = new Phase1(themes, listeQuestions, getFourJoueur(ensJoueurs), GUI_MenuPrincipal.this);
                    phase1.phaseDeJeu();
                    Phase2 phase2 = new Phase2(themes, listeQuestions, phase1.getVainqueurs());
                    phase2.phaseDeJeu();
                    Phase3 phase3 = new Phase3(listeQuestions, phase2.getVainqueurs());
                    phase3.phaseDeJeu();
                    vainqueurs[i] = phase3.getVainqueur();
                }
                JOptionPane.showMessageDialog(null, "Grande finale parmi les vainqueurs des 3 premières parties", "Grande Finale", JOptionPane.INFORMATION_MESSAGE);
                //lancement d'une phase 2 puis 3 à partir des gagnants
                Phase2GrandJeu phase2GrandJeu = new Phase2GrandJeu(themes, listeQuestions, vainqueurs);
                phase2GrandJeu.phaseDeJeu();
                Phase3 phase3 = new Phase3(listeQuestions, phase2GrandJeu.getVainqueurs());
                phase3.phaseDeJeu();
                JOptionPane.showMessageDialog(null, "Le gagnant du grand Jeu est " + phase3.getVainqueur().getNom(),"Grand Gagnant" , JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void addListenerAfficherQuestion(){
        btnQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUI_AfficherQuestion gui_afficherQuestion = new GUI_AfficherQuestion();
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
