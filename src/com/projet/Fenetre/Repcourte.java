package com.projet.Fenetre;

import javax.swing.*;
import java.awt.*;

public class Repcourte extends JFrame{

    private JLabel jlQuestion;
    private JTextField jtfRep;
    private JButton jbValider;

    public Repcourte(){

        setSize(300,200);
        setTitle("Réponse courte");

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

        jtfRep = new JTextField(20);

        jbValider = new JButton("Valider");

    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlQuestion, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(jtfRep, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pan.add(jbValider, gbc);

        return pan;
    }

}
