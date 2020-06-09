package com.projet.fenetre;

import com.projet.Tools;
import com.projet.question.ListeQuestions;
import com.projet.question.Question;
import com.projet.Themes;
import com.projet.question.type.QCM;
import com.projet.question.type.RC;
import com.projet.question.type.VF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GUI_AfficherQuestion extends JFrame {
    private JComboBox<String> jcbTheme;
    private JButton jbNiveau1, jbNiveau2, jbNiveau3;
    private JLabel jlNiveau, jlTheme;
    private ListeQuestions listeQuestions;
    private JButton jbSupprimer;

    public GUI_AfficherQuestion(){
        listeQuestions = ListeQuestions.deserialize();
        setSize(600,400);
        setTitle("Afficher questions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initiate();
        getContentPane().add(placeAll(), BorderLayout.NORTH);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void initiate(){
        jcbTheme = new JComboBox<>(new Themes().getArrayTheme());
        jlNiveau = new JLabel("Choix du niveau");
        jlTheme = new JLabel("Choix du theme");
        jbNiveau1 = new JButton("1");
        addListenerOnButton(jbNiveau1);
        jbNiveau2 = new JButton("2");
        addListenerOnButton(jbNiveau2);
        jbNiveau3 = new JButton("3");
        addListenerOnButton(jbNiveau3);
        jbSupprimer = new JButton("Supprimer");

    }

    private JPanel placeAll(){
        JPanel panel = new JPanel();
        panel.add(jlTheme);
        panel.add(jcbTheme);
        panel.add(jlNiveau);
        panel.add(jbNiveau1);
        panel.add(jbNiveau2);
        panel.add(jbNiveau3);
        return panel;
    }

    private void addListenerOnButton(JButton button){
        button.addActionListener(e -> {
            String theme = jcbTheme.getSelectedItem().toString();
            if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
                getContentPane().remove(1);
            }
            JScrollPane scrollPane = new JScrollPane(placeList(theme, button.getText()), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            getContentPane().add(scrollPane);
            pack();
        });
    }

    private JPanel placeList(String theme, String niveau){
        List<Question> list =  listeQuestions.getQuestionByThemeLevel(theme, Integer.parseInt(niveau));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        GridBagConstraints gbc = new GridBagConstraints();
        cs.insets = new Insets(5,5,5,5);
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.gridx = 0;
        cs.gridy = 0;
        gbc.gridx = 1;
        gbc.gridy = 0;
        for (Question<?> q: list) {
            JTextArea textArea = new JTextArea(q.toString(), 4,10);
            textArea.setEditable(false);
            textArea.setBackground(panel.getBackground());
            panel.add(textArea, cs);
            jbSupprimer = new JButton("Supprimer");
            panel.add(jbSupprimer, gbc);
            addListenerOnSupprimer(q, theme, niveau);
            cs.gridy++;
            gbc.gridy++;

            //todo ajouter un bouton suppr
            //todo recup l'id grace a la list
            //todo
        }
        return panel;
    }

    private void addListenerOnSupprimer(Question question, String theme, String niveau){
        jbSupprimer.addActionListener(actionEvent -> {
            listeQuestions.supprimerQuestion(question);
            listeQuestions.serialize();

            if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
                getContentPane().remove(1);
            }
            JScrollPane scrollPane = new JScrollPane(placeList(theme, niveau), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            getContentPane().add(scrollPane);
            pack();

        });
    }

    private void addListenerOnSupprimer(int index, List<Question> list, String theme, String niveau){
        jbSupprimer.addActionListener(actionEvent -> {

            List<Question> list1 = listeQuestions.getListeQuestion();

//                for (Question<?> q : list1) {
//                    if (q.getId() == index) {
//                        list1.remove(q);
//                    }
//                }

            for (Iterator<Question> iterator = list1.iterator(); iterator.hasNext();) {
                Question value = iterator.next();
                if (value.getId() == index) {
                    iterator.remove();
                }
            }

//                for (Question<?> q : list1) {
//                    System.out.println(q.getId());
//                    System.out.println(q.getNiveauDifficulte());
//                    System.out.println(q.getTheme());
//                    System.out.println(q.getEnonce());
//                }

            try {
                PrintWriter writer = new PrintWriter("Textfile/questionQCM.txt");
                writer.print("");
                writer.close();

            } catch (Exception e) {
                System.out.print("erreur");
            }

            try {
                PrintWriter writer = new PrintWriter("Textfile/questionRC.txt");
                writer.print("");
                writer.close();

            } catch (Exception e) {
                System.out.print("erreur");
            }

            try {
                PrintWriter writer = new PrintWriter("Textfile/questionVF.txt");
                writer.print("");
                writer.close();

            } catch (Exception e) {
                System.out.print("erreur");
            }

            for (Question<?> question : list1) {
                int niveau1 = question.getNiveauDifficulte();
                String theme1 = question.getTheme();

                switch (Tools.getQuestionType(question)){
                    case "QCM" :

                        int br =((QCM) question.getEnonce()).getBonneReponse();
                        String[] rep = ((QCM) question.getEnonce()).getReponses();
                        String quest = ((QCM) question.getEnonce()).getTexte();

                        try {
                            BufferedWriter ReponseFile = new BufferedWriter(new FileWriter("Textfile/questionQCM.txt", true));
                            ReponseFile.write(String.valueOf(niveau1));
                            ReponseFile.newLine();
                            ReponseFile.write(theme1);
                            ReponseFile.newLine();
                            ReponseFile.write(quest);
                            ReponseFile.newLine();
                            ReponseFile.write(rep[0]);
                            ReponseFile.newLine();
                            ReponseFile.write(rep[1]);
                            ReponseFile.newLine();
                            ReponseFile.write(rep[2]);
                            ReponseFile.newLine();
                            ReponseFile.write(String.valueOf(br));
                            ReponseFile.newLine();
                            ReponseFile.newLine();

                            ReponseFile.close();

                        } catch (Exception e) {
                            System.out.print("erreur");
                        }

                        break;

                    case "RC":

                        String br2 =((RC) question.getEnonce()).getBonneReponse();
                        String quest2 = ((RC) question.getEnonce()).getTexte();

                        try {
                            BufferedWriter ReponseFile = new BufferedWriter(new FileWriter("Textfile/questionRC.txt", true));
                            ReponseFile.write(String.valueOf(niveau1));
                            ReponseFile.newLine();
                            ReponseFile.write(theme1);
                            ReponseFile.newLine();
                            ReponseFile.write(quest2);
                            ReponseFile.newLine();
                            ReponseFile.write(br2);
                            ReponseFile.newLine();
                            ReponseFile.newLine();

                            ReponseFile.close();

                        } catch (Exception e) {
                            System.out.print("erreur");
                        }

                        break;

                    case "VF":

                        Boolean br3 =((VF) question.getEnonce()).isReponse();
                        String quest3 = ((VF) question.getEnonce()).getTexte();

                        try {
                            BufferedWriter ReponseFile = new BufferedWriter(new FileWriter("Textfile/questionVF.txt", true));
                            ReponseFile.write(String.valueOf(niveau1));
                            ReponseFile.newLine();
                            ReponseFile.write(theme1);
                            ReponseFile.newLine();
                            ReponseFile.write(quest3);
                            ReponseFile.newLine();
                            ReponseFile.write(String.valueOf(br3));
                            ReponseFile.newLine();
                            ReponseFile.newLine();

                            ReponseFile.close();

                        } catch (Exception e) {
                            System.out.print("erreur");
                        }

                        break;
                }
            }

            if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
                getContentPane().remove(1);
            }
            JScrollPane scrollPane = new JScrollPane(placeList(theme, niveau), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            getContentPane().add(scrollPane);
            pack();

        });
    }
}
