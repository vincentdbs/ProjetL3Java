package com.projet.fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class GUI_AjouterQuestion extends JFrame {

    private JLabel jlNiveau;
    private JTextField jtfNiveau;
    private JLabel jlTheme;
    private String[] theme = {"Sport","Histoire","Géographie","Art","Sciences","Cinema","High-tech","Musique","Divertissements","Littérature"};
    private JComboBox jcbTheme;
    private JLabel jlQuestion;
    private JTextField jtfQuestion;
    private JLabel jlRep1, jlRep2, jlRep3;
    private JTextField jtfRep1, jtfRep2, jtfRep3;
    private JLabel jlRepCorrecte;
    private JTextField jtfRepCorrecte;
    private JButton jbQcm, jbRepCourte, jbVf, jbRetour;

    public GUI_AjouterQuestion(){

        setSize(300,200);
        setTitle("Ajouter une question");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initiate();
        getContentPane().add(placeAll());

        setResizable(false);
        setLocationRelativeTo(null);

        pack();
        setVisible(true);

    }

    private void initiate(){
        jlNiveau = new JLabel("Niveau ");
        jtfNiveau = new JTextField(20);
        jlTheme = new JLabel("Thème ");
        jcbTheme = new JComboBox(theme);
        jlQuestion = new JLabel("Question ");
        jtfQuestion = new JTextField(20);
        jlRep1 = new JLabel("Réponse 1 ");
        jtfRep1 = new JTextField(20);
        jlRep2 = new JLabel("Réponse 2 ");
        jtfRep2 = new JTextField(20);
        jlRep3 = new JLabel("Réponse 3 ");
        jtfRep3 = new JTextField(20);
        jlRepCorrecte = new JLabel("Réponse correcte ");
        jtfRepCorrecte = new JTextField(20);
        jbQcm = new JButton("QCM");
        jbRepCourte = new JButton("Réponse courte");
        jbVf = new JButton("Vrai Faux");
        jbRetour = new JButton("Retour");

        addListenerOnQcm();
        addListenerOnRepCourte();
        addListenerOnVf();
        addListenerOnRetour();

    }



    private JPanel placeAll(){
        JPanel pan = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(jlNiveau,gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pan.add(jtfNiveau,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(jlTheme,gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        pan.add(jcbTheme,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pan.add(jlQuestion,gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pan.add(jtfQuestion,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pan.add(jlRep1,gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pan.add(jtfRep1,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pan.add(jlRep2,gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pan.add(jtfRep2,gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pan.add(jlRep3,gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pan.add(jtfRep3,gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        pan.add(jlRepCorrecte,gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        pan.add(jtfRepCorrecte,gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        pan.add(jbQcm,gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        pan.add(jbRepCourte,gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        pan.add(jbVf,gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;
        pan.add(jbRetour,gbc);

        return pan;
    }

    private void addListenerOnQcm(){
        jbQcm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUI_AjouterQuestion ajouterQuestion = new GUI_AjouterQuestion();

                String questionNiveau = jtfNiveau.getText();
                String questionTheme = jcbTheme.getSelectedItem().toString();
                String question = jtfQuestion.getText();
                String rep1 = jtfRep1.getText();
                String rep2 = jtfRep2.getText();
                String rep3 = jtfRep3.getText();
                String repCorrecte = jtfRepCorrecte.getText();

                try {
                    BufferedWriter QcmFile = new BufferedWriter(new FileWriter("Textfile/questionQCM.txt", true)); // pour bien mettre les bonnes infos
                    QcmFile.newLine();
                    QcmFile.write(questionNiveau);
                    QcmFile.newLine(); // retour ligne
                    QcmFile.write(questionTheme);
                    QcmFile.newLine();
                    QcmFile.write(question);
                    QcmFile.newLine();
                    QcmFile.write(rep1);
                    QcmFile.newLine();
                    QcmFile.write(rep2);
                    QcmFile.newLine();
                    QcmFile.write(rep3);
                    QcmFile.newLine();
                    QcmFile.write(repCorrecte);
                    QcmFile.newLine();

                    QcmFile.close();

                } catch (Exception e) {
                    System.out.print("erreur");
                }
                dispose();
            }
        });
    }

    private void addListenerOnRepCourte(){
        jbRepCourte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUI_AjouterQuestion ajouterQuestion = new GUI_AjouterQuestion();

                String questionNiveau = jtfNiveau.getText();
                String questionTheme = jcbTheme.getSelectedItem().toString();
                String question = jtfQuestion.getText();
                String repCorrecte = jtfRepCorrecte.getText();

                try {
                    BufferedWriter ReponseFile = new BufferedWriter(new FileWriter("Textfile/questionRC.txt", true)); // pour bien mettre les bonnes infos
                    ReponseFile.newLine();
                    ReponseFile.write(questionNiveau);
                    ReponseFile.newLine(); // retour ligne
                    ReponseFile.write(questionTheme);
                    ReponseFile.newLine();
                    ReponseFile.write(question);
                    ReponseFile.newLine();
                    ReponseFile.write(repCorrecte);
                    ReponseFile.newLine();

                    ReponseFile.close();

                } catch (Exception e) {
                    System.out.print("erreur");
                }

                dispose();
            }
        });
    }

    private void addListenerOnVf(){
        jbVf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUI_AjouterQuestion ajouterQuestion = new GUI_AjouterQuestion();

                String questionNiveau = jtfNiveau.getText();
                String questionTheme = jcbTheme.getSelectedItem().toString();
                String question = jtfQuestion.getText();
                String repCorrecte = jtfRepCorrecte.getText();

                try {
                    BufferedWriter VfFile = new BufferedWriter(new FileWriter("Textfile/questionVF.txt", true)); // pour bien mettre les bonnes infos
                    VfFile.newLine();
                    VfFile.write(questionNiveau);
                    VfFile.newLine(); // retour ligne
                    VfFile.write(questionTheme);
                    VfFile.newLine();
                    VfFile.write(question);
                    VfFile.newLine();
                    VfFile.write(repCorrecte);
                    VfFile.newLine();

                    VfFile.close();

                } catch (Exception e) {
                    System.out.print("erreur");
                }

                dispose();
            }
        });
    }

    private void addListenerOnRetour(){
        jbRetour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                GUI_MenuPrincipal menuPrincipal = new GUI_MenuPrincipal();
                dispose();
            }
        });
    }
}
