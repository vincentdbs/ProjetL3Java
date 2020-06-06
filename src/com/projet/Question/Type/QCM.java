package com.projet.Question.Type;

public class QCM {
    private String[] reponses;
    private int bonneReponse;
    private String texte;

    public QCM(String texte, int bonneReponse,  String[] reponses) {
        this.reponses = reponses;
        this.bonneReponse = bonneReponse;
        this.texte = texte;
    }

    public String[] getReponses() {
        return reponses;
    }

    public void setReponses(String[] reponses) {
        this.reponses = reponses;
    }

    public int getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(int bonneReponse) {
        this.bonneReponse = bonneReponse;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public String toString(){
        StringBuilder toDisplay = new StringBuilder(getTexte());
        for (int i = 0; i < reponses.length ; i++) {
            toDisplay.append("\n").append(i).append(") ").append(reponses[i]);
        }
        return toDisplay.toString();
    }
}
