package com.projet.Fenetre;

import com.projet.Chronometre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

public class GUI_Vf extends JDialog {

    private JLabel jlQuestion;
    private JButton jbVrai, jbFaux;
    private String answer;
    private Chronometre chronometre = new Chronometre();
    private int[] tempsReponse;

    public GUI_Vf(JFrame frame, String enonce){
        super(frame, true);
        setSize(300,200);
        setTitle("Vrai-Faux");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        chronometre.start();

        initiate(enonce);
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(String enonce){

        jlQuestion = new JLabel(enonce);

        jbVrai = new JButton("Vrai");
        jbFaux = new JButton("Faux");
        addListenerFalse();
        addListenerTrue();
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

    public void addListenerTrue(){
        jbVrai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = "true";
                chronometre.setState(false);
                tempsReponse = chronometre.getTime();
                dispose();
            }
        });
    }

    public void addListenerFalse(){
        jbFaux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = "false";
                chronometre.setState(false);
                tempsReponse = chronometre.getTime();
                dispose();
            }
        });
    }

    public String getAnswer() {
        return answer;
    }

    public int[] getTempsReponse() {
        return tempsReponse;
    }

    public Chronometre getChronometre() {
        return chronometre;
    }
}
