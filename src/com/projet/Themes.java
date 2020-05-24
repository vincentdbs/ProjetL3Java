package com.projet;

import java.lang.reflect.Array;

public class Themes {
    private String[] arrayTheme;
    private int indicateur;

    public Themes(String[] arrayTheme) {
        this.arrayTheme = arrayTheme;
        indicateur = 0;
    }

    public Themes() {
        indicateur = 0;
        this.arrayTheme = new String[]{"Sport", "Histoire", "Géographie", "Art", "Sciences", "Cinema", "High-tech", "Musique", "Divertissements", "Littérature"};
    }

    /**
     * Remplacement du thème à l'index donné par newTheme
     */
    public void modifierTheme(int index, String newTheme){
        arrayTheme[index] = newTheme;
    }


    /**
     * Remplacement du oldTheme donné par newTheme
     */
    public void modifierTheme(String oldTheme, String newTheme){
        for (int i = 0; i < getArrayTheme().length ; i++) {
            if (arrayTheme[i].equals(oldTheme)){
                arrayTheme[i] = newTheme;
            }
        }
    }

    public int selectionnerTheme(){
        int index;
        do {
            index = (int) (Math.random() * 10);
        }while (index == getIndicateur());
        setIndicateur(index);
        return indicateur;
    }

    public int selectionnerNextTheme(){
        setIndicateur((getIndicateur()+1)%10);
        return getIndicateur();
    }

    public int[] selectionnerCinqThemes(){
        int[] indicateur = new int[5];
        for (int i = 0; i < indicateur.length; i++) {
            indicateur[i] = (int) (Math.random() * 10);
        }
        return indicateur;
    }

    public void afficher(){
        for (String theme : arrayTheme) {
            System.out.print(theme + " - ");
        }
        System.out.println("\nIndicateur = " + getIndicateur());
    }



    /**
     * getter/setter
     * @return
     */
    public int getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(int indicateur) {
        this.indicateur = indicateur;
    }

    public String[] getArrayTheme() {
        return arrayTheme;
    }
}
