package com.projet.Fenetre;

import com.projet.Chronometre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI_Repcourte extends JDialog{

    private JLabel jlQuestion;
    private JTextField jtfRep;
    private JButton jbValider;
    private String answer;
    private Chronometre chronometre = new Chronometre();
    private int[] tempsReponse;

    public GUI_Repcourte(JFrame parent, String enonce){
        super(parent, true);
        setSize(300,200);
        setTitle("Réponse courte");

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

        jtfRep = new JTextField(20);

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

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(jtfRep, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pan.add(jbValider, gbc);

        return pan;
    }

    private void addListenerValider(){
        jbValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = jtfRep.getText();
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
