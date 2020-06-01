package com.projet.Fenetre;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    //todo placer les bouttons et implementer les fonctionnalités du sujet, respectivement Point n° 1,3,4,5,6,7,8 dans le sujet page 6
    private JButton btnTheme, btnQuestion, btnAjouteQuestion, btnSupprimerQuestion, btnAfficherJoueurs, btnStart, btnQuitter;

    public MenuPrincipal() {
        setSize(300,200);
        setTitle("Menu principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initiate();
        getContentPane().add(placeAll());
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initiate(){
        btnStart = new JButton("Jouer");
    }

    private JPanel placeAll(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.insets = new Insets(5,5,5,5);

        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(btnStart,cs);

        return panel;
    }
}
