package com.projet.fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_AjouterQuestion extends JFrame {
    private JLabel jlType;
    private JButton btnRc, btnVf, btnQcm;

    public GUI_AjouterQuestion() {
        setSize(400,250);
        setTitle("Ajouter Question");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initiate();
        getContentPane().add(placeAll(),BorderLayout.NORTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initiate(){
        btnQcm = new JButton("QCM");
        btnRc = new JButton("RC");
        btnVf = new JButton("VF");


    }

    private JPanel placeAll(){
        JPanel panel = new JPanel();
        panel.add(btnQcm);
        panel.add(btnVf);
        panel.add(btnRc);
        addListenerAjouterQuestion(btnQcm);
        addListenerAjouterQuestion(btnRc);
        addListenerAjouterQuestion(btnVf);
        return panel;
    }

    private void addListenerAjouterQuestion(JButton button){
        button.addActionListener(e -> {
            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints cs = new GridBagConstraints();
            cs.insets = new Insets(5,5,5,5);
            cs.gridx = 0;
            cs.gridy = 0;
            panel.add(new JLabel("Enonce de la question"),cs);
            cs.gridx++;
            panel.add(new JTextField(10), cs);
            if(button.getText().equals("QCM")){
                cs.gridy++;
                cs.gridx = 0;
                panel.add(new JLabel("Reponse 1 :"),cs);
                cs.gridx++;
                panel.add(new JTextField(10), cs);
                cs.gridy++;
                cs.gridx = 0;
                panel.add(new JLabel("Reponse 2 :"),cs);
                cs.gridx++;
                panel.add(new JTextField(10), cs);
                cs.gridy++;
                cs.gridx = 0;
                panel.add(new JLabel("Reponse 3 :"),cs);
                cs.gridx++;
                panel.add(new JTextField(10), cs);
            }
            cs.gridy++;
            panel.add(new JButton("Ajouter"), cs);
            if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
                getContentPane().remove(1);
            }
            getContentPane().add(panel, BorderLayout.CENTER);
            setVisible(true);
        });
    }
}
