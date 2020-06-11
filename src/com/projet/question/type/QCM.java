package com.projet.question.type;

import java.io.Serializable;

/**
 * DORFFER - DUBOIS - ESTEBAN - GOMEZ CASTELLON - MATHIEN
 */

public class QCM implements Serializable {
    private String[] reponses;
    private int bonneReponse;
    private String texte;

    public QCM(String texte, int bonneReponse,  String... reponses) {
        this.reponses = reponses;
        this.bonneReponse = bonneReponse;
        this.texte = texte;
    }

    public String[] getReponses() {
        return reponses;
    }

    public int getBonneReponse() {
        return bonneReponse;
    }

    public String getTexte() {
        return texte;
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
