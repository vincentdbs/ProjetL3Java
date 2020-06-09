package com.projet.fenetre.question;

import javax.swing.*;
import java.awt.*;

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
        jtfRep = new JTextField(20);
        jlTheme = new JLabel("Thème : " +theme);
        jlJoueur = new JLabel("Joueur : " + joueur);
        jlQuestion = new JLabel(enonce);
        jbValider = new JButton("Valider");
        addListenerValider();
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

        gbc.gridy++;
        pan.add(jlJoueur, gbc);

        gbc.gridx = 0;
        pan.add(jtfRep, gbc);

        gbc.gridy++;
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
