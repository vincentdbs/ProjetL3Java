package com.projet.fenetre;

import com.projet.joueur.Joueur;
import com.projet.Themes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;


public class GUI_ThemeSelection extends JDialog{

    private JLabel jlTheme, jlIntro, jlJoueur;
    private JButton[] tabBouton = new JButton[6];
    private JButton jbValider;
    private Joueur[] tabjoueur;
    private Themes theme;
    private int activeValid = 0;
    private LinkedHashMap<String, ArrayList<String>> themeJoueur = new LinkedHashMap<>(); //Garder l'ordre des joueurs

    public GUI_ThemeSelection(Themes themes, Joueur[] joueur, JFrame parent){
        super(parent, true );
        setSize(300,200);
        setTitle("Thème");

        this.tabjoueur = joueur;
        this.theme = themes;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        initiate(joueur);
        getContentPane().add(placeAll(themes, tabjoueur[0].getNom())); //On met le nom du premier joueur à jouer
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }



    private void initiate(Joueur[] joueur){
        jlIntro = new JLabel("Sélection des thèmes :  Joueur à choisir : ");
        jlTheme = new JLabel();
        jlJoueur = new JLabel();
        jlJoueur.setText(joueur[0].getNom());
        jbValider = new JButton("Continuer");
        jbValider.setEnabled(false); //Tant que les joueurs n'ont pas sélectionné leurs thèmes, on ne peut pas continuer
        jbValider.addActionListener(e -> dispose());
    }


    ActionListener listener = new ActionListener() { //Ce qui se passe quand on clique sur le bouton d'un thème
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                int var =  0;
                ArrayList<String> choix = new ArrayList<>();
                String text = ((JButton) e.getSource()).getText(); //On récupère le texte du bouton qui correspond au thème choisi
                choix.add(text);


                themeJoueur.putIfAbsent(jlJoueur.getText(), choix); //On place le nom du joueur et son thème si le nom du joueur n'est pas encore dans la Map
                themeJoueur.get(jlJoueur.getText()).add(((JButton) e.getSource()).getText()); //Si le nom est déjà dans la map, on ajoute le thème associé
                choix.remove(text);

                activeValid += 1; //Compteur pour activé le bouton Continuer

                if(jlJoueur.getText().equals(tabjoueur[0].getNom()) && var == 0){ //Si le premier joueur a cliqué, le choix passe au second e tainsi de suite
                    jlJoueur.setText(tabjoueur[1].getNom());
                    var = 1;
                }
                if(jlJoueur.getText().equals(tabjoueur[1].getNom())  && var == 0){
                    jlJoueur.setText(tabjoueur[2].getNom());
                    var = 1;
                }
                if(jlJoueur.getText().equals(tabjoueur[2].getNom())  && var == 0){
                    jlJoueur.setText(tabjoueur[0].getNom());
                    var = 1;
                }

                ((JButton) e.getSource()).setEnabled(false); //Le thème devient indisponible

                if(activeValid == 6){
                    jbValider.setEnabled(true); //On active le bouton quand les 6 thèmes ont été choisis
                }
            }
        }
    };



    private JPanel placeAll(Themes themes, String currentJoueur){ //Panel pour le choix des thèmes. On affiche le joueur qui choisit
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;

        pan.add(jlIntro, gbc);
        jlJoueur.setText(currentJoueur);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pan.add(jlJoueur, gbc);

        for (int i = 0; i < themes.getArrayTheme().length; i++) {

            gbc.gridx = 0;
            gbc.gridy = i+1;
            jlTheme = new JLabel("Thème " + (i+1));
            pan.add(jlTheme, gbc);

            gbc.gridx = 2;
            gbc.gridy = i+1;
            tabBouton[i] = new JButton(themes.getArrayTheme()[i]); //On a un Array de JButton qui en contient 6
            tabBouton[i].addActionListener(listener); //On ajoute le listener sur le bouton
            pan.add(tabBouton[i], gbc);

        }
        gbc.gridx = 1;
        gbc.gridy = 11;
        pan.add(jbValider, gbc);
        return pan;
    }

    public LinkedHashMap<String, ArrayList<String>> getThemeJoueur() {
        return themeJoueur;
    }
}
