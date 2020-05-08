package com.projet;

public class Themes {
    private String[] arrayTheme;
    private int indicateur;

    public Themes(String[] arrayTheme) {
        this.arrayTheme = arrayTheme;
        indicateur = 0;
    }

    public void modifierTheme(int index, String newTheme){
        arrayTheme[index] = newTheme;
    }

    public int selectionnerTheme(){
        int tempo = getIndicateur();
        setIndicateur((int) (Math.random() * 10));
        return tempo;
    }

    //todo
    public void selectionnerCinqThemes(){

    }

    public void afficher(){
        for (String theme : arrayTheme) {
            System.out.print(theme + " - ");
        }
        System.out.println("Indicateur = " + getIndicateur());
    }

    public int getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(int indicateur) {
        this.indicateur = indicateur;
    }
}
