package com.projet.fenetre.question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_RC extends GUI_Question {

    private JLabel jlQuestion;
    private JTextField jtfRep;
    private JButton jbValider;
    private JLabel jlTheme;
    private JLabel jlJoueur;

    public GUI_RC(JFrame parent, String enonce, String theme, String joueur){
        super(parent);
        setSize(300,200);
        setTitle("Réponse courte");
        initiate(enonce, theme, joueur);
        getContentPane().add(placeAll());
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void initiate(String enonce, String theme, String joueur){

        jlQuestion = new JLabel(enonce);

        jtfRep = new JTextField(20);

        jbValider = new JButton("Valider");

        jlTheme = new JLabel("Thème : " +theme);
        jlJoueur = new JLabel("Joueur : " + joueur);

        addListenerValider();
    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlQuestion, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pan.add(jlTheme, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pan.add(jlJoueur, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(jtfRep, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pan.add(jbValider, gbc);

        return pan;
    }

    private void addListenerValider(){
        jbValider.addActionListener(e -> {
            setAnswer(jtfRep.getText());
            stopChronometre();
            dispose();
        });
    }
}
