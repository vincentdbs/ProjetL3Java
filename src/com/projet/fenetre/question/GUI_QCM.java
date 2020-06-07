package com.projet.fenetre.question;

import javax.swing.*;
import java.awt.*;

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
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlQuestion, gbc);

        gbc.gridx++;
        pan.add(jlTheme, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        gbc.anchor = GridBagConstraints.LINE_START;//on fixe la box a gauche
        pan.add(createBox(jlRep1, jbRep1), gbc);

        gbc.gridx++;
        pan.add(jlJoueur, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        pan.add(createBox(jlRep2, jbRep2), gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        pan.add(createBox(jlRep3, jbRep3), gbc);

        return pan;
    }

    private Box createBox(JLabel label, JButton button){
        Box proposition = Box.createHorizontalBox();
        proposition.add(button);
        proposition.add(Box.createRigidArea(new Dimension(20,0)));
        proposition.add(label);
        proposition.setMinimumSize(new Dimension(500,40));
        return proposition;
    }



    private void addListenerRep(JButton btn){
        btn.addActionListener(e -> {
            setAnswer(String.valueOf(Integer.parseInt(btn.getText())-1));
            stopChronometre();
            dispose();
        });
    }
}
