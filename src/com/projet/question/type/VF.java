package com.projet.question.type;

import java.io.Serializable;

public class VF implements Serializable {
    private String texte;
    private boolean reponse;

    public VF(String texte, boolean reponse) {
        this.texte = texte;
        this.reponse = reponse;
    }

    public String getTexte() {
        return texte;
    }

    public boolean isReponse() {
        return reponse;
    }

    @Override
    public String toString(){
        return getTexte() + "\nVrai\nFaux";
    }
}
