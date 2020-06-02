package com.projet.Question.Type;

public class RC {
    private String texte, bonneReponse;

    public RC(String texte, String bonneReponse) {
        this.texte = texte;
        this.bonneReponse = bonneReponse;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(String bonneReponse) {
        this.bonneReponse = bonneReponse;
    }

    @Override
    public String toString(){
        return getTexte();
    }
}
