package com.projet.question.type;

import java.io.Serializable;

/**
 * DORFFER - DUBOIS - ESTEBAN - GOMEZ CASTELLON - MATHIEN
 */

public class RC implements Serializable {
    private String texte, bonneReponse;

    public RC(String texte, String bonneReponse) {
        this.texte = texte;
        this.bonneReponse = bonneReponse;
    }

    public String getTexte() {
        return texte;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    @Override
    public String toString(){
        return getTexte();
    }
}
