package com.projet.Fenetre;

import com.projet.Chronometre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Qcm extends GUI_Question{

    private JLabel jlQuestion;
    private JLabel jlRep1, jlRep2, jlRep3;
    private JButton jbRep1, jbRep2, jbRep3;

    public GUI_Qcm(JFrame parent, String enonce, String... reponses){
        super(parent);
        setSize(300,200);
        setTitle("QCM");
        initiate(enonce, reponses);
        getContentPane().add(placeAll());
        pack();
        setVisible(true);
    }

    private void initiate(String enonce, String[] reponses){

        jlQuestion = new JLabel(enonce);

        jlRep1 = new JLabel("1 " + reponses[0]);
        jlRep2 = new JLabel("2 " + reponses[1]);
        jlRep3 = new JLabel("3 " + reponses[2]);

        jbRep1 = new JButton("1");
        jbRep2 = new JButton("2");
        jbRep3 = new JButton("3");
        addListenerRep(jbRep1);
        addListenerRep(jbRep2);
        addListenerRep(jbRep3);

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

    private void addListenerRep(JButton btn){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAnswer(String.valueOf(Integer.parseInt(jbRep1.getText())-1));
                stopChronometre();
                dispose();
            }
        });
    }
}
