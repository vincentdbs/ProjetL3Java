package com.projet.fenetre;

import com.projet.Themes;

import javax.swing.*;
import java.awt.*;

public class GUI_Theme1 extends JFrame {

    private JLabel jlTheme, jlNomTheme, jlIntro;
    private JButton jbValider;

    public GUI_Theme1(Themes themes){

        setSize(300,200);
        setTitle("Thème");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initiate();
        getContentPane().add(placeAll(themes));

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(){

        jlIntro = new JLabel("Total des thèmes abordés : ");
        jlTheme = new JLabel();
        jlNomTheme = new JLabel();
        jbValider = new JButton("Retour");
        jbValider.addActionListener(e -> dispose());

    }



    private JPanel placeAll(Themes themes){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlIntro, gbc);

        for (int i = 0; i < themes.getArrayTheme().length; i++) {

            gbc.gridx = 0;
            gbc.gridy = i+1;
            jlTheme = new JLabel("Thème " + (i+1));
            pan.add(jlTheme, gbc);

            gbc.gridx = 2;
            gbc.gridy = i+1;
            jlNomTheme = new JLabel(themes.getArrayTheme()[i]);
            pan.add(jlNomTheme, gbc);

        }

        gbc.gridx = 1;
        gbc.gridy = 11;
        pan.add(jbValider, gbc);

        return pan;
    }
}
