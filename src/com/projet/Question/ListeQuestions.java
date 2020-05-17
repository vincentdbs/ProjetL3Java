package com.projet.Question;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ListeQuestions {
    private LinkedList<Question> listeQuestion;
    private int indicateur;

    public ListeQuestions(LinkedList<Question> listeQuestion, int indicateur) {
        this.listeQuestion = listeQuestion;
        this.indicateur = indicateur;
    }

    public ListeQuestions(int indicateur) {
        this.indicateur = indicateur;
        this.listeQuestion = new LinkedList<>();
    }

    public ListeQuestions() {
        this.indicateur = 0;
        this.listeQuestion = new LinkedList<>();
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

    //todo à voir si il faut ordonnée par théme
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

    //todo à voir si il faut ordonnée par théme
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
