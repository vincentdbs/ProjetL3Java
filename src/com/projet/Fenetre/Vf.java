package com.projet.Fenetre;

import javax.swing.*;
import java.awt.*;

public class Vf extends JFrame {

    private JLabel jlQuestion;
    private JButton jbVrai, jbFaux;

    public Vf(){

        setSize(300,200);
        setTitle("Vrai-Faux");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initiate();
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(){

        jlQuestion = new JLabel("la question est blablabla");

        jbVrai = new JButton("Vrai");
        jbFaux = new JButton("Faux");

    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pan.add(jlQuestion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(jbVrai, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        pan.add(jbFaux, gbc);

        return pan;
    }

}
