package com.projet.Question;

import java.util.LinkedList;

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
