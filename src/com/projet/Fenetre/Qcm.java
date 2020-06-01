package com.projet.Fenetre;

import javax.swing.*;
import java.awt.*;

public class Qcm extends JDialog{

    private JLabel jlQuestion;
    private JLabel jlRep1, jlRep2, jlRep3;
    private JButton jbRep1, jbRep2, jbRep3;

    public Qcm(JFrame parent){
        super(parent, true);
        setSize(300,200);
        setTitle("QCM");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initiate();
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(){

        jlQuestion = new JLabel("la question est blablabla");

        jlRep1 = new JLabel("Réponse 1");
        jlRep2 = new JLabel("Réponse 2");
        jlRep3 = new JLabel("Réponse 3");

        jbRep1 = new JButton("1");
        jbRep2 = new JButton("2");
        jbRep3 = new JButton("3");

    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pan.add(jlQuestion, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pan.add(jlRep1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pan.add(jlRep2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pan.add(jlRep3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pan.add(jbRep1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pan.add(jbRep2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        pan.add(jbRep3, gbc);

        return pan;
    }
}
