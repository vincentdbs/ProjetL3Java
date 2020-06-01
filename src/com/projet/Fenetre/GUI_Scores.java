package com.projet.Fenetre;

import com.projet.Joueur.EnsJoueurs;

import javax.swing.*;
import java.awt.*;

public class GUI_Scores extends JFrame {

    private String[] entete = {"Nom","Etat"};
    private String[][] arrJoueur = {{"tristan","20"},{"etienne","10"}};
    private JTable jtTable;

    public GUI_Scores(EnsJoueurs joueurs){

        setSize(300,200);
        setTitle("Tableau des résultats");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initiate(joueurs);
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(EnsJoueurs joueurs){
        arrJoueur = new String[joueurs.getVectorJoueur().size()][2];
        for (int i = 0 ; i < arrJoueur.length ; i++){
            arrJoueur[i][0] = joueurs.getVectorJoueur().get(i).getNom();
            arrJoueur[i][1] = joueurs.getVectorJoueur().get(i).getEtat();
        }
        jtTable = new JTable(arrJoueur, entete);
    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        pan.add(new JScrollPane(jtTable), gbc);

        return pan;
    }
}
