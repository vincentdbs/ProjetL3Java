package com.projet.fenetre;

import com.projet.joueur.EnsJoueurs;

import javax.swing.*;
import java.awt.*;

public class GUI_Scores extends JFrame {

    private String[] entete = {"Nom","Etat"};
    private String[][] arrJoueur;
    private JTable jtTable;
    private JButton jbRetour;

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

        jbRetour = new JButton("Retour");
        jbRetour.addActionListener(e-> dispose());
    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        pan.add(new JScrollPane(jtTable), gbc);

        gbc.gridy = 1;
        pan.add(jbRetour, gbc);

        return pan;
    }
}
