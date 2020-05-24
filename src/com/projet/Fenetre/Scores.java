package com.projet.Fenetre;

import javax.swing.*;
import java.awt.*;

public class Scores extends JFrame {

    String[] entete = {"Nom du joueur","Scores du joueur"};
    String[][] joueurs = {{"tristan","20"},{"etienne","10"}};
    JTable jtTable;

    public Scores(){

        setSize(300,200);
        setTitle("Tableau des résultats");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initiate();
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(){
        jtTable = new JTable(joueurs, entete);
    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        pan.add(new JScrollPane(jtTable), gbc);

        return pan;
    }
}
