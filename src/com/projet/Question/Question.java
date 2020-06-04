package com.projet.Question;

import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;

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

    public boolean saisir(String answer){
        switch (getEnonce().getClass().getName()){
            case "com.projet.Question.Type.QCM":{
                QCM enonce = (QCM) getEnonce();
                if(enonce.getBonneReponse() == Integer.parseInt(answer)){
                    return true;
                }
                break;
            }
            case "com.projet.Question.Type.VF":{
                VF enonce = (VF) getEnonce();
                if(enonce.isReponse() == Boolean.parseBoolean(answer)){
                    return true;
                }
                break;
            }
            case "com.projet.Question.Type.RC":{
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
