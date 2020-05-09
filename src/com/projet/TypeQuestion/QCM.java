package com.projet.TypeQuestion;

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

    //methode afficher dans sujet
    // todo a voir si il faut interface graphique ailleurs
    @Override
    public String toString(){
        String toDisplay = "";
        toDisplay += getTexte() + '\n';
        for (int i = 0; i < reponses.length ; i++) {
            toDisplay += i + " " + reponses[i] + "\n";
        }
        return toDisplay;
    }
}
