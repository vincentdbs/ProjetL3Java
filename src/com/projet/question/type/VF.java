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

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public boolean isReponse() {
        return reponse;
    }

    public void setReponse(boolean reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString(){
        return getTexte() + "\nVrai\nFaux";
    }
}
