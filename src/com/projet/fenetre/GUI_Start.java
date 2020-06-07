package com.projet.fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Start extends JFrame {

    private JLabel jlBienvenue;
    private JButton jbCommencer;

    public GUI_Start(){

        setSize(300,200);
        setTitle("Start");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initiate();
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

//        pack();
        setVisible(true);

    }

    private void initiate(){

        jlBienvenue = new JLabel("Bienvenue dans Jumanji");
        jbCommencer = new JButton("Commencer");

        AddOnCommencer();
    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlBienvenue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(jbCommencer, gbc);

        return pan;
    }

    private void AddOnCommencer(){
        jbCommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUI_MenuPrincipal menuPrincipal = new GUI_MenuPrincipal();
                dispose();
            }
        });
    }
}
