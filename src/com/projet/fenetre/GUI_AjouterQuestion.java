package com.projet.fenetre;

import com.projet.question.ListeQuestions;
import com.projet.question.Question;
import com.projet.question.type.QCM;
import com.projet.question.type.RC;
import com.projet.question.type.VF;

import javax.swing.*;
import java.awt.*;

public class GUI_AjouterQuestion extends JFrame {

    private JLabel jlNiveau, jlTheme, jlQuestion, jlRep1, jlRep2, jlRep3, jlRepCorrecte;
    private JTextField jtfQuestion, jtfRep1, jtfRep2, jtfRep3, jtfRepCorrecte;
    private JButton jbQcm, jbRepCourte, jbVf, jbValider, jbRetour;
    private JComboBox<Integer> jcbNiveau;
    private JComboBox<String> jcbTheme, jcbVF, jcbBonneRepQCM;
    private String questionType;


    public GUI_AjouterQuestion() {

        setTitle("Ajouter une question");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initiate();
        getContentPane().add(placeTypeQuestion(), BorderLayout.NORTH);

        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);

    }

    private void initiate() {
        jcbBonneRepQCM = new JComboBox<>(new String[]{"Réponse 1", "Réponse 2", "Réponse 3"});
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
        jbValider = new JButton("Valider");

        addListenerQCM();
        addListenerRC();
        addListenerVF();
        addListenerValider();

        jbRetour = new JButton("Retour");
        jbRetour.addActionListener(e->dispose());

    }

    /**
     * Place les bouttons du type de questions
     */
    private JPanel placeTypeQuestion() {
        JPanel pan = new JPanel();
        pan.add(jbQcm);
        pan.add(jbRepCourte);
        pan.add(jbVf);
        pan.add(jbRetour);
        return pan;
    }

    /**
     * Listener sur les bouttons de type de questions et valider
     */
    private void addListenerValider(){
        jbValider.addActionListener(e->{
            int questionNiveau = jcbNiveau.getSelectedIndex()+1;
            String questionTheme = jcbTheme.getSelectedItem().toString();
            switch (questionType){
                case "RC":
                    if (dataValidRC()){
                        ajouterQuestion(new Question<>(questionNiveau, questionTheme, createQuestionRC(jtfRepCorrecte.getText())));
                    }else {
                        displayErrorMessage();
                    }
                    break;
                case "VF":
                    if (dataValidVF()){
                        ajouterQuestion(new Question<>(questionNiveau, questionTheme, createQuestionVF(jtfRepCorrecte.getText())));
                    }else {
                        displayErrorMessage();
                    }
                    break;
                case "QCM":
                    if (dataValidQCM()){
                        ajouterQuestion(new Question<>(questionNiveau, questionTheme, createQuestionQCM()));
                    }else {
                        displayErrorMessage();
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Veuillez choisir un type de question !", "Erreur", JOptionPane.ERROR_MESSAGE); //cas impossible
                    break;
            }
            clearAll();
        });
    }

    private void addListenerQCM() {
        jbQcm.addActionListener(e -> {
            clearAll();
            questionType = "QCM";
            placeFormQuestion();
        });
    }

    private void addListenerRC() {
        jbRepCourte.addActionListener(e -> {
            clearAll();
            questionType = "RC";
            placeFormQuestion();
        });
    }

    private void addListenerVF() {
        jbVf.addActionListener(e -> {
            clearAll();
            questionType = "VF";
            placeFormQuestion();
        });
    }

    /**
     * Place le panneau du formulaire des question en fonction du type de question à ajouter
     */
    private void placeFormQuestion(){
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
        switch (questionType){
            case "VF":
                cs.gridx=0;
                cs.gridy++;
                panel.add(jlRepCorrecte, cs);
                cs.gridx++;
                panel.add(jcbVF, cs);
                break;
            case "RC":
                cs.gridx=0;
                cs.gridy++;
                panel.add(jlRepCorrecte, cs);
                cs.gridx++;
                panel.add(jtfRepCorrecte, cs);
                break;
            case "QCM":
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
                break;
            default:
                break;
        }
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
        setSize(450, 350);
        setVisible(true);
    }

    /**
     * Creation des questions pour l'ajout à la liste
     */
    private QCM createQuestionQCM(){
        String question = jtfQuestion.getText();
        String rep1 = jtfRep1.getText();
        String rep2 = jtfRep2.getText();
        String rep3 = jtfRep3.getText();
        int repCorrecte =  jcbBonneRepQCM.getSelectedIndex();
        return new QCM(question, repCorrecte, rep1,rep2,rep3);
    }

    private VF createQuestionVF(String goodAnswer){
        String question = jtfQuestion.getText();
        if (goodAnswer.equals("Vrai")){
            return new VF(question, true);
        }else {
            return new VF(question, false);
        }
    }

    private RC createQuestionRC(String goodAnswer){
        String question = jtfQuestion.getText();
        return new RC(question, goodAnswer);
    }

    private void ajouterQuestion(Question<?> question){
        //recuperation de la liste
        ListeQuestions q = ListeQuestions.deserialize();
        question.setId(q.getListeQuestion().size());
        //ajout de la question à la liste
        q.ajouterQuestion(question);
        //serialization du fichier
        q.serialize();
        JOptionPane.showMessageDialog(null, "La question a bien été ajoutée", "Ajout", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Verification des données saisies
     */
    private boolean dataValidRC(){
        if(jtfQuestion.getText().isEmpty()){
            return false;
        }
        return !jtfRepCorrecte.getText().isEmpty();
    }

    private boolean dataValidVF(){
        return !jtfQuestion.getText().isEmpty();
    }

    private boolean dataValidQCM(){
        if(jtfQuestion.getText().isEmpty()){
            return false;
        }
        if(jtfRep1.getText().isEmpty()){
            return false;
        }
        if(jtfRep2.getText().isEmpty()){
            return false;
        }
        return !jtfRep3.getText().isEmpty();
    }

    /**
     * Affichae du message d'erreur en cas de mauvaise saisies
     */
    private void displayErrorMessage(){
        JOptionPane.showMessageDialog(null, "Remplissez tout les champs !" ,"Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Remise à 0 des champs de texte
     */
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
