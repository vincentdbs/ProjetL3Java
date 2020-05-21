package com.projet.Question;

public class Question<T> {
    private static int numero = 0;
    private String theme;
    private int niveauDifficulte;
    private T enonce;

    public Question(int niveauDifficulte, String theme, T enonce) {
        this.niveauDifficulte = niveauDifficulte;
        this.enonce = enonce;
        this.theme = theme;
        numero++;
    }

    public void afficher(){
        System.out.println("Thème : " + getTheme() + " | Niveau " + getNiveauDifficulte() + "\n" + getEnonce().toString());
    }

    @Override
    public String toString(){
        return "Thème : " + getTheme() + " | Niveau " + getNiveauDifficulte() + "\n" + enonce.toString();
    }

    public static int getNumero() {
        return numero;
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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


}
