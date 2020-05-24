package com.projet.Fenetre;

import javax.swing.*;
import java.awt.*;

public class ChoixNom extends JFrame {

    JLabel jlChoixNom;
    JLabel jlNom1, jlNom2, jlNom3, jlNom4;
    JTextField jtfNom1, jtfNom2, jtfNom3, jtfNom4;
    JButton jbValider;

    public ChoixNom(){

        setSize(300,200);
        setTitle("Choix des noms");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initiate();
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(){

        jlChoixNom = new JLabel("Choisissez les joueurs : ");

        jlNom1 = new JLabel("Nom 1 : ");
        jtfNom1 = new JTextField(20);

        jlNom2 = new JLabel("Nom 2 : ");
        jtfNom2 = new JTextField(20);

        jlNom3 = new JLabel("Nom 3 : ");
        jtfNom3 = new JTextField(20);

        jlNom4 = new JLabel("Nom 4 : ");
        jtfNom4 = new JTextField(20);

        jbValider = new JButton("Valider");

    }

    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlChoixNom, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(jlNom1, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        pan.add(jtfNom1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pan.add(jlNom2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        pan.add(jtfNom2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pan.add(jlNom3, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        pan.add(jtfNom3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pan.add(jlNom4, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        pan.add(jtfNom4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pan.add(jbValider, gbc);

        return pan;
    }

}
