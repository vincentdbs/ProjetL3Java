package com.projet.fenetre;

import javax.swing.*;
import java.awt.*;

public class GUI_ChoixTheme extends JDialog {
    private JLabel jlChoix;
    private JComboBox jcbListTheme;
    private JButton btnValider;

    public GUI_ChoixTheme(Frame owner, String[] themes) {
        super(owner, true);
        jlChoix = new JLabel("Choisissez un thème");
        btnValider = new JButton("Valider");
        jcbListTheme = new JComboBox(themes);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().add(placeComponent());
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

    }

    private JPanel placeComponent(){
        JPanel panel = new JPanel();
        panel.add(jlChoix);
        panel.add(jcbListTheme);
        panel.add(btnValider);
        return panel;
    }

    public String getChosenTheme(){
        return String.valueOf(jcbListTheme.getSelectedItem());
    }
}
