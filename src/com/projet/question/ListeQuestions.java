package com.projet.question;

import com.projet.Themes;
import com.projet.question.type.QCM;
import com.projet.question.type.RC;
import com.projet.question.type.VF;

import javax.swing.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListeQuestions implements Serializable {
    private LinkedList<Question> listeQuestion;
    private int indicateur;

    public ListeQuestions(LinkedList<Question> listeQuestion, int indicateur) {
        this.listeQuestion = listeQuestion;
        this.indicateur = indicateur;
        getAllQuestions();
    }

    public ListeQuestions(int indicateur) {
        this.indicateur = indicateur;
        this.listeQuestion = new LinkedList<>();
        getAllQuestions();
    }

    public ListeQuestions() {
        this.indicateur = 0;
        this.listeQuestion = new LinkedList<>();
        getAllQuestions();
    }

    public void afficherListe(){
        for (Question<?> q : listeQuestion) {
            System.out.println(q + "\n");
        }
    }


    public void serialize() {
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Textfile/questions.ser"));
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ListeQuestions deserialize() {
        File file = new File("Textfile/questions.ser");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ListeQuestions liste = (ListeQuestions) objectInputStream.readObject();
            objectInputStream.close();
            return liste;
        } catch(Exception e) {
            //si erreur => récupération d'une liste de question depuis les fichiers originaux
            JOptionPane.showMessageDialog(null, "Attention votre liste de question n'a pu être récupérée !\n La liste contient les questions par défaut.", "Erreur", JOptionPane.ERROR_MESSAGE);
            ListeQuestions l = new ListeQuestions();
            l.serialize();
            return l;
        }
    }

    public void ajouterQuestion(Question<?> question){
        listeQuestion.add(question);
    }

    public void supprimerQuestion(int index){
        listeQuestion.remove(index);
    }

    public void supprimerQuestion(Question<?> question){
        listeQuestion.remove(question);
    }

    public void afficherQuestionByLevel(int level){
        listeQuestion.stream()
                .filter(question -> question.getNiveauDifficulte() == level)
                .forEach(Question::afficher);
    }

    public void afficherQuestionByTheme(String theme){
        listeQuestion.stream()
                .filter(question -> question.getTheme().equals(theme))
                .forEach(Question::afficher);
    }

    public void afficherQuestionByThemeLevel(String theme, int level){
        listeQuestion.stream()
                .filter(question -> question.getTheme().equals(theme))
                .filter(question -> question.getNiveauDifficulte() == level)
                .forEach(Question::afficher);
    }

    public List<Question> getQuestionByLevel(int level){
        return listeQuestion.stream()
                .filter(question -> question.getNiveauDifficulte() == level)
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionByTheme(String theme){
        return listeQuestion.stream()
                .filter(question -> question.getTheme().equals(theme))
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionByThemeLevel(String theme, int level){
        return listeQuestion.stream()
                .filter(question -> question.getTheme().equals(theme))
                .filter(question -> question.getNiveauDifficulte() == level)
                .collect(Collectors.toList());
    }

    public static boolean isThereEnoughQuestion(){
        ListeQuestions listeQuestions = ListeQuestions.deserialize();
        Themes themes = new Themes();
        for (String t : themes.getArrayTheme()) {
            for (int n = 1 ; n <= 3 ; n++) {
                if (listeQuestions.getQuestionByThemeLevel(t, n).size() < (5-n)){
                    JOptionPane.showMessageDialog(null,"Il n'y a pas assez de question dans le thème " + t + " de niveau " + n +" pour jouer une partie.\n" +
                            "Ajoutez des questions ou remettez les paramètres par défaut", "Pas assez de question", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Recuperation des questions depuis les fichiers textes (utilisé en cas de reserialization de la classe)
     */
    private void getAllQuestions(){
        getAllQcmFromFile();
        getAllVfFromFile();
        getAllRcFromFile();
    }

    private void getAllQcmFromFile(){
        try {
            Scanner scanner = new Scanner(new File("Textfile/questionQCM.txt"));
            do {
                int niveau = scanner.nextInt();
                scanner.nextLine();
                String theme = scanner.nextLine();
                String texte = scanner.nextLine();
                String[] answers = new String[3];
                answers[0] = scanner.nextLine();
                answers[1] = scanner.nextLine();
                answers[2] = scanner.nextLine();
                int goodAnswer = scanner.nextInt();
                QCM qcm = new QCM(texte, goodAnswer, answers);
                listeQuestion.add(new Question<>(niveau, theme, qcm));
                scanner.nextLine();
                scanner.nextLine();
            }while (scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            System.out.println("erreur de lecture");
        }
    }

    private void getAllVfFromFile(){
        try {
            Scanner scanner = new Scanner(new File("Textfile/questionVF.txt"));
            do {
                int niveau = scanner.nextInt();
                scanner.nextLine();
                String theme =  scanner.nextLine();
                String texte = scanner.nextLine();
                boolean goodAnswer = Boolean.parseBoolean(scanner.nextLine());
                VF vf = new VF(texte, goodAnswer);
                listeQuestion.add(new Question<>(niveau, theme, vf));
                scanner.nextLine();
            } while (scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            System.out.println("erreur de lecture");
        }
    }


    private void getAllRcFromFile(){
        try {
            Scanner scanner = new Scanner(new File("Textfile/questionRC.txt"));
            do{
                int niveau = scanner.nextInt();
                scanner.nextLine();
                String theme = scanner.nextLine();
                String texte = scanner.nextLine();
                String goodAnswer = scanner.nextLine();
                RC rc = new RC(texte, goodAnswer);
                listeQuestion.add(new Question<>(niveau, theme, rc));
                scanner.nextLine();
            } while (scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            System.out.println("erreur de lecture");
        }
    }


    /**
     * Getter et setter
     */
    public LinkedList<Question> getListeQuestion() {
        return listeQuestion;
    }
}
