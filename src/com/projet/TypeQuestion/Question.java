package com.projet.TypeQuestion;

public class Question<T> {
    private static int numero = 0;
    //todo add Theme
    private int niveauDifficulte;
    private T enonce;

    public Question(int niveauDifficulte, T enonce) {
        this.niveauDifficulte = niveauDifficulte;
        this.enonce = enonce;
        numero++;
    }

    public static int getNumero() {
        return numero;
    }

    public static void setNumero(int numero) {
        Question.numero = numero;
    }

    public int getNiveauDifficulte() {
        return niveauDifficulte;
    }

    public void setNiveauDifficulte(int niveauDifficulte) {
        this.niveauDifficulte = niveauDifficulte;
    }

    public T getEnonce() {
        return enonce;
    }

    public void setEnonce(T enonce) {
        this.enonce = enonce;
    }

}
