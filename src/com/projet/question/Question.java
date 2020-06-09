package com.projet.question;

import com.projet.question.type.QCM;
import com.projet.question.type.RC;
import com.projet.question.type.VF;

import java.io.Serializable;

public class Question<T> implements Serializable {
    private static int numero = 0;
    private int id;
    private String theme;
    private int niveauDifficulte;
    private T enonce;

    public Question(int niveauDifficulte, String theme, T enonce) {
        this.id = numero;
        this.niveauDifficulte = niveauDifficulte;
        this.enonce = enonce;
        this.theme = theme;
        numero++;
    }

    public void afficher(){
        System.out.println("Thème : " + getTheme() + " | Niveau " + getNiveauDifficulte() + "\n" + getEnonce().toString());
    }

    public boolean saisir(String answer){
        switch (getEnonce().getClass().getName()){
            case "com.projet.question.type.QCM":{
                QCM enonce = (QCM) getEnonce();
                if(enonce.getBonneReponse() == Integer.parseInt(answer)){
                    return true;
                }
                break;
            }
            case "com.projet.question.type.VF":{
                VF enonce = (VF) getEnonce();
                if(enonce.isReponse() == Boolean.parseBoolean(answer)){
                    return true;
                }
                break;
            }
            case "com.projet.question.type.RC":{
                RC enonce = (RC) getEnonce();
                if(enonce.getBonneReponse().equals(answer)){
                    return true;
                }
                break;
            }
            default:
                break;
        }
        return false;
    }

    @Override
    public String toString(){
        return "Thème : " + getTheme() + " | Niveau " + getNiveauDifficulte() + "\n" + enonce.toString();
    }

    public int getNiveauDifficulte() {
        return niveauDifficulte;
    }

    public T getEnonce() {
        return enonce;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setId(int id) {
        this.id = id;
    }
}
