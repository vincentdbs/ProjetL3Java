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
import java.awt.*;

public class GUI_MenuPrincipal extends JFrame {
    private JButton btnTheme, btnQuestion, btnAjouterQuestion, btnAfficherJoueurs, btnStart, btnQuitter;
    private JButton btnGrandJeu;
    private ListeQuestions listeQuestions;
    private Themes themes;


    public GUI_MenuPrincipal() {
        //creation de la liste des questions du jeu
        listeQuestions = ListeQuestions.deserialize();

        //creation des themes + selection du premier theme
        themes = new Themes();

        setSize(350,200);
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
        sizeButton(btnStart);

        btnTheme = new JButton("Afficher thèmes");
        sizeButton(btnTheme);

        btnQuestion = new JButton("Afficher questions");
        sizeButton(btnQuestion);

        btnAfficherJoueurs = new JButton("Afficher joueurs");
        sizeButton(btnAfficherJoueurs);

        btnGrandJeu = new JButton("Grand Jeu");
        sizeButton(btnGrandJeu);

        btnAjouterQuestion = new JButton("Ajouter question");
        sizeButton(btnAjouterQuestion);

        btnQuitter = new JButton("Quitter");
        sizeButton(btnQuitter);

        addListenerStart();
        addListenerTheme();
        addListenerJoueur();
        addListenerQuitter();
        addListenerGrandJeu();
        addListenerAfficherQuestion();
        addListenerAjouterQuestion();
    }

    private void sizeButton(JButton bouton){
        bouton.setPreferredSize(new Dimension(150,30));
    }

    private JPanel placeAll(){
        JPanel panel = new JPanel();
        panel.add(btnStart);
        panel.add(btnGrandJeu);
        panel.add(btnTheme);
        panel.add(btnQuestion);
        panel.add(btnAfficherJoueurs);
        panel.add(btnAjouterQuestion);
        panel.add(btnQuitter);
        return panel;
    }

    /**
     * Listener des bouttons
     */
    private void addListenerStart(){
        btnStart.addActionListener(e -> {
            if(ListeQuestions.isThereEnoughQuestion()){
                EnsJoueurs ensJoueurs = new EnsJoueurs();
                ensJoueurs.creer();
                playAGame(ensJoueurs);
            }
        });
    }

    private void addListenerTheme(){
        btnTheme.addActionListener(e -> {
            GUI_Theme theme = new GUI_Theme(themes);
        });
    }

    private void addListenerJoueur(){
        btnAfficherJoueurs.addActionListener(e -> {
            GUI_Scores scores = new GUI_Scores(createEnsJoueur());
        });
    }

    private void addListenerQuitter(){
        btnQuitter.addActionListener(e -> dispose());
    }

    private void addListenerGrandJeu(){
        btnGrandJeu.addActionListener(e -> {
            if (ListeQuestions.isThereEnoughQuestion()){
                EnsJoueurs ensJoueurs = new EnsJoueurs();
                ensJoueurs.creer();
                Joueur[] vainqueurs = new Joueur[3];
                //lancement de 4 parties
                for (int i = 0; i < 3; i++) {
                    JOptionPane.showMessageDialog(null, "Partie n°" + (i+1), "Partie n°" + (i+1), JOptionPane.INFORMATION_MESSAGE);
                    vainqueurs[i] = playAGame(ensJoueurs);
                }
                JOptionPane.showMessageDialog(null, "Grande finale parmi les vainqueurs des 3 premières parties", "Grande Finale", JOptionPane.INFORMATION_MESSAGE);
                //lancement d'une phase 2 à 3 thèmes puis phase 3 à partir des gagnants des parties précédentes
                Phase2GrandJeu phase2GrandJeu = new Phase2GrandJeu(themes, listeQuestions, vainqueurs);
                phase2GrandJeu.phaseDeJeu();
                Phase3 phase3 = new Phase3(listeQuestions, phase2GrandJeu.getVainqueurs());
                phase3.phaseDeJeu();
                JOptionPane.showMessageDialog(null, "Le gagnant du grand Jeu est " + phase3.getVainqueur().getNom(),"Grand Gagnant" , JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void addListenerAfficherQuestion(){
        btnQuestion.addActionListener(e -> {
            GUI_AfficherQuestion gui_afficherQuestion = new GUI_AfficherQuestion();
        });
    }

    private void addListenerAjouterQuestion(){
        btnAjouterQuestion.addActionListener(e->{
            GUI_AjouterQuestion gui_ajouterQuestion = new GUI_AjouterQuestion();
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

    /**
     * Deroulement d'une partie classique
     */
    private Joueur playAGame(EnsJoueurs ensJoueurs){
        Phase1 phase1 = new Phase1(themes, listeQuestions, getFourJoueur(ensJoueurs), GUI_MenuPrincipal.this);
        phase1.phaseDeJeu();
        Phase2 phase2 = new Phase2(themes, listeQuestions, phase1.getVainqueurs());
        phase2.phaseDeJeu();
        Phase3 phase3 = new Phase3(listeQuestions, phase2.getVainqueurs());
        phase3.phaseDeJeu();
        return phase3.getVainqueur();
    }
}
