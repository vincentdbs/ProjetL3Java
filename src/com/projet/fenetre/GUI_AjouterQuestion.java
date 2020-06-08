package com.projet.fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class GUI_AjouterQuestion extends JFrame {

    private JLabel jlNiveau, jlTheme, jlQuestion, jlRep1, jlRep2, jlRep3, jlRepCorrecte;
    private JTextField jtfQuestion, jtfRep1, jtfRep2, jtfRep3, jtfRepCorrecte;
    private JButton jbQcm, jbRepCourte, jbVf, jbRetour, jbValider;
    private JComboBox<Integer> jcbBonneRepQCM, jcbNiveau;
    private JComboBox<String> jcbTheme, jcbVF;
    private String questionType;

    public GUI_AjouterQuestion() {

        setSize(500, 400);
        setTitle("Ajouter une question");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initiate();
        getContentPane().add(placeTypeQuestion(), BorderLayout.NORTH);

        setResizable(false);
        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void initiate() {
        jcbBonneRepQCM = new JComboBox<>(new Integer[]{1, 2, 3});
        jcbNiveau = new JComboBox<>(new Integer[]{1, 2, 3});
        jcbTheme = new JComboBox<>(new String[]{"Sport", "Histoire", "Géographie", "Art", "Sciences", "Cinema", "High-tech", "Musique", "Divertissements", "Littérature"});
        jcbVF = new JComboBox<>(new String[]{"Vrai", "Faux"});
        jlNiveau = new JLabel("Niveau ");
        jlTheme = new JLabel("Thème ");
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
        jbVf = new JButton("Vrai/Faux");
        jbRetour = new JButton("Retour");
        jbValider = new JButton("Valider");

        addListenerQCM();
        addListenerRC();
        addListenerVF();
        addListenerOnRetour();
        addListenerValider();

    }

    private JPanel placeTypeQuestion() {
        JPanel pan = new JPanel();
        pan.add(jbQcm);
        pan.add(jbRepCourte);
        pan.add(jbVf);
        return pan;
    }

    private void addListenerQCM() {
        jbQcm.addActionListener(e -> {
            clearAll();
            questionType = "QCM";
            placeQCM();
        });
    }

    private void addListenerRC() {
        jbRepCourte.addActionListener(e -> {
            clearAll();
            questionType = "RC";
            placeRC();
        });
    }

    private void addListenerVF() {
        questionType = "VF";
        jbVf.addActionListener(e -> {
            clearAll();
            questionType = "VF";
            placeVF();
        });
    }

    private void placeQCM(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(5,5,5,5);
        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(jlQuestion ,cs);
        cs.gridx++;
        panel.add(jtfQuestion, cs);
        cs.gridy++;
        cs.gridx = 0;
        panel.add(jlRep1 ,cs);
        cs.gridx++;
        panel.add(jtfRep1 , cs);
        cs.gridy++;
        cs.gridx = 0;
        panel.add(jlRep2 ,cs);
        cs.gridx++;
        panel.add(jtfRep2 , cs);
        cs.gridy++;
        cs.gridx = 0;
        panel.add(jlRep3 ,cs);
        cs.gridx++;
        panel.add(jtfRep3 , cs);
        cs.gridx=0;
        cs.gridy++;
        panel.add(jlRepCorrecte, cs);
        cs.gridx++;
        panel.add(jcbBonneRepQCM, cs);
        cs.gridx = 0;
        cs.gridy++;
        panel.add(jlNiveau, cs);
        cs.gridx++;
        panel.add(jcbNiveau, cs);
        cs.gridx = 0;
        cs.gridy++;
        panel.add(jlTheme, cs);
        cs.gridx++;
        panel.add(jcbTheme,cs);

        cs.gridy++;
        panel.add(jbValider, cs);
        if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
            getContentPane().remove(1);
        }
        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void placeRC(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(5,5,5,5);
        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(jlQuestion ,cs);
        cs.gridx++;
        panel.add(jtfQuestion, cs);
        cs.gridx=0;
        cs.gridy++;
        panel.add(jlRepCorrecte, cs);
        cs.gridx++;
        panel.add(jtfRepCorrecte, cs);
        cs.gridx = 0;
        cs.gridy++;
        panel.add(jlNiveau, cs);
        cs.gridx++;
        panel.add(jcbNiveau, cs);
        cs.gridx = 0;
        cs.gridy++;
        panel.add(jlTheme, cs);
        cs.gridx++;
        panel.add(jcbTheme,cs);

        cs.gridy++;
        panel.add(jbValider, cs);
        if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
            getContentPane().remove(1);
        }
        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void placeVF(){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(5,5,5,5);
        cs.gridx = 0;
        cs.gridy = 0;
        panel.add(jlQuestion ,cs);
        cs.gridx++;
        panel.add(jtfQuestion, cs);
        cs.gridx=0;
        cs.gridy++;
        panel.add(jlRepCorrecte, cs);
        cs.gridx++;
        panel.add(jcbVF, cs);
        cs.gridx = 0;
        cs.gridy++;
        panel.add(jlNiveau, cs);
        cs.gridx++;
        panel.add(jcbNiveau, cs);
        cs.gridx = 0;
        cs.gridy++;
        panel.add(jlTheme, cs);
        cs.gridx++;
        panel.add(jcbTheme,cs);

        cs.gridy++;
        panel.add(jbValider, cs);
        if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
            getContentPane().remove(1);
        }
        getContentPane().add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addListenerValider(){
        jbValider.addActionListener(e->{
            switch (questionType){
                case "RC":
                    ajouterRCouVf("RC", jtfRepCorrecte.getText());
                    break;
                case "VF":
                    ajouterRCouVf("VF", jcbVF.getSelectedItem().toString());
                    break;
                case "QCM":
                    ajouterQCM();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Veuillez choisir un type de question !", "Erreur", JOptionPane.ERROR_MESSAGE); //cas impossible
                    break;
            }
            clearAll();
        });
    }

    private void ajouterQCM(){

        String questionNiveau = String.valueOf (jcbNiveau.getSelectedIndex()+1);
        String questionTheme = jcbTheme.getSelectedItem().toString();
        String question = jtfQuestion.getText();
        String rep1 = jtfRep1.getText();
        String rep2 = jtfRep2.getText();
        String rep3 = jtfRep3.getText();
        String repCorrecte =  String.valueOf(jcbBonneRepQCM.getSelectedIndex());

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
        }dispose();
    }

    private void ajouterRCouVf(String type, String goodAnswer){
        String questionNiveau = String.valueOf (jcbNiveau.getSelectedIndex()+1);
        String questionTheme = jcbTheme.getSelectedItem().toString();
        String question = jtfQuestion.getText();
        if(type.equals("VF")){
            if (goodAnswer.equals("Vrai")){
                goodAnswer = "true";
            }else {
                goodAnswer = "false";
            }
        }

        try {
            BufferedWriter ReponseFile = new BufferedWriter(new FileWriter("Textfile/question" + type + ".txt", true)); // pour bien mettre les bonnes infos
            ReponseFile.newLine();
            ReponseFile.write(questionNiveau);
            ReponseFile.newLine(); // retour ligne
            ReponseFile.write(questionTheme);
            ReponseFile.newLine();
            ReponseFile.write(question);
            ReponseFile.newLine();
            ReponseFile.write(goodAnswer);
            ReponseFile.newLine();

            ReponseFile.close();

        } catch (Exception e) {
            System.out.print("erreur");
        }
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

    private void clearAll(){
        jtfQuestion.setText("");
        jtfRep1.setText("");
        jtfRep2.setText("");
        jtfRep3.setText("");
        jtfRepCorrecte.setText("");
        jcbNiveau.setSelectedIndex(0);
        jcbVF.setSelectedIndex(0);
        jcbBonneRepQCM.setSelectedIndex(0);
        jcbTheme.setSelectedIndex(0);

    }
}
