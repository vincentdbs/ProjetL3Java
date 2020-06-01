package com.projet.Fenetre;

import com.projet.Question.Type.VF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Vf extends JDialog {

    private JLabel jlQuestion;
    private JButton jbVrai, jbFaux;
    private String answer;

    public GUI_Vf(JFrame frame, String enonce){
        super(frame, true);
        setSize(300,200);
        setTitle("Vrai-Faux");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
                dispose();
            }
        });
    }

    public void addListenerFalse(){
        jbFaux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = "false";
                dispose();
            }
        });
    }

    public String getAnswer() {
        return answer;
    }
}
