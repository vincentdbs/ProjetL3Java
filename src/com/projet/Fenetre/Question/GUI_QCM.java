package com.projet.Fenetre.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_QCM extends GUI_Question {

    private JLabel jlQuestion;
    private JLabel jlRep1, jlRep2, jlRep3;
    private JButton jbRep1, jbRep2, jbRep3;
    private JLabel jlTheme;
    private JLabel jlJoueur;

    public GUI_QCM(JFrame parent, String enonce, String theme, String joueur, String... reponses){
        super(parent);
        setSize(300,200);
        setTitle("QCM");
        initiate(enonce, reponses, theme, joueur);
        getContentPane().add(placeAll());
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void initiate(String enonce, String[] reponses, String theme, String joueur){

        jlQuestion = new JLabel(enonce);

        jlRep1 = new JLabel(reponses[0]);
        jlRep2 = new JLabel(reponses[1]);
        jlRep3 = new JLabel(reponses[2]);

        jbRep1 = new JButton("1");
        jbRep2 = new JButton("2");
        jbRep3 = new JButton("3");

        jlTheme = new JLabel("Thème : " + theme);
        jlJoueur = new JLabel("Joueur : " + joueur);

        addListenerRep(jbRep1);
        addListenerRep(jbRep2);
        addListenerRep(jbRep3);

    }

    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        JPanel pan2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlQuestion, gbc);

        gbc.gridx++;
        pan.add(jlTheme, gbc);



        gbc.gridx = 0;
        gbc.gridy++;

        Box proposition1 = Box.createHorizontalBox(); //On met le bouton et la proposition à côté
        proposition1.add(jbRep1);
        proposition1.add(Box.createRigidArea(new Dimension(20,0)));
        proposition1.add(jlRep1);


        proposition1.setMinimumSize(new Dimension(500,40));
        gbc.anchor = GridBagConstraints.LINE_START;//on fixe la box a gauche
        pan.add(proposition1, gbc);



        gbc.gridx++;
        pan.add(jlJoueur, gbc);



        gbc.gridx = 0;
        gbc.gridy++;

        Box proposition2 = Box.createHorizontalBox();
        proposition2.add(jbRep2);
        proposition2.add(Box.createRigidArea(new Dimension(20,0)));
        proposition2.add(jlRep2);

        proposition2.setMinimumSize(new Dimension(500,40));
        gbc.anchor = GridBagConstraints.LINE_START;
        pan.add(proposition2, gbc);



        gbc.gridx = 0;
        gbc.gridy++;

        Box proposition3 = Box.createHorizontalBox();
        proposition3.add(jbRep3);
        proposition3.add(Box.createRigidArea(new Dimension(20,0)));
        proposition3.add(jlRep3);

        proposition3.setMinimumSize(new Dimension(500,40));
        gbc.anchor = GridBagConstraints.LINE_START;
        pan.add(proposition3, gbc);

//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        pan.add(jlQuestion, gbc);
//
//        gbc.gridx = 2;
//        gbc.gridy = 0;
//        pan.add(jlTheme, gbc);
//
//        gbc.gridx = 2;
//        gbc.gridy = 1;
//        pan.add(jlJoueur, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 1;
//        pan.add(jlRep1, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 2;
//        pan.add(jlRep2, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 3;
//        pan.add(jlRep3, gbc);
//
//        gbc.gridx = 0;
//        gbc.gridy = 4;
//        pan.add(jbRep1, gbc);
//
//        gbc.gridx = 1;
//        gbc.gridy = 4;
//        pan.add(jbRep2, gbc);
//
//        gbc.gridx = 2;
//        gbc.gridy = 4;
//        pan.add(jbRep3, gbc);

        return pan;
    }



    private void addListenerRep(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer(String.valueOf(Integer.parseInt(btn.getText())-1));
                stopChronometre();
                dispose();
            }
        });
    }
}
