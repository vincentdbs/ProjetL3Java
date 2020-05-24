package com.projet.Question;

import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListeQuestions {
    private LinkedList<Question> listeQuestion;
    private int indicateur;

    public ListeQuestions(LinkedList<Question> listeQuestion, int indicateur) {
        this.listeQuestion = listeQuestion;
        this.indicateur = indicateur;
        getAllVfFromFile();
        getAllQcmFromFile();
    }

    public ListeQuestions(int indicateur) {
        this.indicateur = indicateur;
        this.listeQuestion = new LinkedList<>();
        getAllVfFromFile();
        getAllQcmFromFile();
    }

    public ListeQuestions() {
        this.indicateur = 0;
        this.listeQuestion = new LinkedList<>();
        getAllVfFromFile();
        getAllQcmFromFile();
    }

    public void afficherListe(){
        for (Question<?> q : listeQuestion) {
            System.out.println(q + "\n");
        }
    }

    public void ajouterQuestion(Question question){
        listeQuestion.add(question);
    }

    public void supprimerQuestion(int index){
        listeQuestion.remove(index);
    }

    //  todo à voir si il faut ordonnée par théme
    public void afficherQuestionByLevel(int level){
        listeQuestion.stream()
                .filter(question -> question.getNiveauDifficulte() == level)
                .forEach(question -> question.afficher());
    }

    public void afficherQuestionByTheme(String theme){
        listeQuestion.stream()
                .filter(question -> question.getTheme() == theme)
                .forEach(question -> question.afficher());
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
            } while (scanner.hasNextLine());
        } catch (FileNotFoundException e) {
            System.out.println("erreur de lecture");
        }
    }


    private void getAllRcFromFile(){
        try {
            Scanner scanner = new Scanner(new File("Textfile/questionRC.txt"));
            while (scanner.hasNextLine()){
                int niveau = scanner.nextInt();
                String theme = scanner.nextLine();
                String texte = scanner.nextLine();
                String goodAnswer = scanner.nextLine();
                RC rc = new RC(texte, goodAnswer);
                listeQuestion.add(new Question<>(niveau, theme, rc));
            }
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

    public void setListeQuestion(LinkedList<Question> listeQuestion) {
        this.listeQuestion = listeQuestion;
    }

    public int getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(int indicateur) {
        this.indicateur = indicateur;
    }
}
