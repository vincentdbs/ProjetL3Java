package com.projet.fenetre.question;

import javax.swing.*;
import java.awt.*;

/**
 * DORFFER - DUBOIS - ESTEBAN - GOMEZ CASTELLON - MATHIEN
 */

public class GUI_VF extends GUI_Question {

    private JLabel jlQuestion;
    private JButton jbVrai, jbFaux;
    private JLabel jlTheme;
    private JLabel jlJoueur;

    public GUI_VF(JFrame frame, String enonce, String theme, String joueur){
        super(frame);
        setSize(200,200);
        setTitle("Vrai-Faux");
        initiate(enonce, theme, joueur);
        getContentPane().add(placeAll());
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    private void initiate(String enonce, String theme, String joueur){
        jlQuestion = new JLabel(enonce);
        jbVrai = new JButton("Vrai");
        jbFaux = new JButton("Faux");
        jlTheme = new JLabel("Thème : " +theme);
        jlJoueur = new JLabel("Joueur : " + joueur);
        addListenerFalse();
        addListenerTrue();
    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlQuestion, gbc);

        gbc.gridy++;
        pan.add(jbVrai, gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        pan.add(jlTheme, gbc);

        gbc.gridx++;
        pan.add(jlJoueur, gbc);

        gbc.gridy++;
        pan.add(jbFaux, gbc);
        return pan;
    }

    public void addListenerTrue(){
        jbVrai.addActionListener(e -> {
            setAnswer("true");
            stopChronometre();
            dispose();
        });
    }

    public void addListenerFalse(){
        jbFaux.addActionListener(e -> {
            setAnswer("false");
            stopChronometre();
            dispose();
        });
    }
}
