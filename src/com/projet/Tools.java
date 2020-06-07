package com.projet;

import com.projet.joueur.Joueur;
import com.projet.question.Question;

import java.util.ArrayList;
import java.util.Arrays;

public final class Tools {
    private Tools() {}

    /**
     * Renvoie le nom du type de la Question passée en paramètre
     */
    public static String getQuestionType(Question question){
        switch (question.getEnonce().getClass().getName()){
            case "com.projet.question.type.QCM":{
                return "QCM";
            }
            case "com.projet.question.type.VF":{
                return "VF";
            }
            case "com.projet.question.type.RC":{
                return "RC";
            }
            default:
                break;
        }
        return "";
    }

    /**
     * Renvoie le chronomètre le plus petit parmi le tableau en paramètre
     */
    public static Chronometre getLowestChronometer(Chronometre[] chronometres){
        return Arrays.stream(chronometres)
                .min((o1, o2) -> {
                    if (o1.getHour() < o2.getHour()) {
                        return -1;
                    } else if (o1.getHour() > o2.getHour()) {
                        return 1;
                    } else {
                        if (o1.getMinute() < o2.getMinute()) {
                            return -1;
                        } else if (o1.getMinute() > o2.getMinute()) {
                            return 1;
                        } else {
                            if (o1.getSecond() < o2.getSecond()) {
                                return -1;
                            } else if (o1.getSecond() > o2.getSecond()) {
                                return 1;
                            } else {
                                if (o1.getMilisecond() < o2.getMilisecond()) {
                                    return -1;
                                } else if (o1.getMilisecond() > o2.getMilisecond()) {
                                    return 1;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }
                })
                .get();
    }

    /**
     * Renvoie le chronomètre le plus grand parmi le tableau en paramètre
     */
    public static Chronometre getGreatestChronometer(Chronometre[] chronometres){
        return Arrays.stream(chronometres)
                .min((o1, o2) -> {
                    if (o1.getHour() < o2.getHour()) {
                        return 1;
                    } else if (o1.getHour() > o2.getHour()) {
                        return -1;
                    } else {
                        if (o1.getMinute() < o2.getMinute()) {
                            return 11;
                        } else if (o1.getMinute() > o2.getMinute()) {
                            return -1;
                        } else {
                            if (o1.getSecond() < o2.getSecond()) {
                                return 1;
                            } else if (o1.getSecond() > o2.getSecond()) {
                                return -1;
                            } else {
                                if (o1.getMilisecond() < o2.getMilisecond()) {
                                    return 1;
                                } else if (o1.getMilisecond() > o2.getMilisecond()) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        }
                    }
                })
                .get();
    }

    /**
     * Renvoie le ou les joueurs ayant le plus petit score parmi le tableau en paramètre
     */
    public static Joueur[] getJoueursLowestScore(Joueur[] joueurs){
        //récupération du score le plus bas
        int scoreMin = joueurs[0].getScore();
        for (int i = 1; i < joueurs.length; i++) {
            if(joueurs[i].getScore() < scoreMin){
                scoreMin = joueurs[i].getScore();
            }
        }
        //récupération des joueurs ayant le score le plus bas
        int finalScoreMin = scoreMin;
        return Arrays.stream(joueurs)
                .filter(j -> j.getScore() == finalScoreMin)
                .toArray(Joueur[]::new);
    }

    public static int getLowestScore(Joueur[] joueurs){
        int scoreMin = joueurs[0].getScore();
        for (int i = 1; i < joueurs.length; i++) {
            if(joueurs[i].getScore() < scoreMin){
                scoreMin = joueurs[i].getScore();
            }
        }
        return scoreMin;
    }

    public static Joueur[] getJoueurElimine(Chronometre[] chrono, Joueur[] joueurs){
        ArrayList<Joueur> list = new ArrayList<>();
        Chronometre highestChrono = new Chronometre();

        int lowestScore = Tools.getLowestScore(joueurs);

        ArrayList<Chronometre> listeChrono = new ArrayList<>();

        //Il faut récupérer le plus grand chrono parmi les joueurs qui ont le plus petit score
        for(int i=0; i<joueurs.length; i++){

            if(joueurs[i].getScore() == lowestScore){

               listeChrono.add(chrono[i]); //On ajoute à la liste des chronos à comparer tous ceux des joueurs qui ont le plus petit score

            }

        }

        Chronometre[] chronoComparer = new Chronometre[listeChrono.size()];
        for(int i=0; i<listeChrono.size(); i++){
            chronoComparer[i] = listeChrono.get(i); //On met les chronos à comparer dans un nouveau tableau de Chronometres
        }

        highestChrono = getGreatestChronometer(chronoComparer);


        for (int i = 0; i < joueurs.length ; i++) { //ajout des joueurs dont le score est le plus bas et le chrono le plus haut
            if((chrono[i].equals(highestChrono)) && (joueurs[i].getScore() == lowestScore)){
                list.add(joueurs[i]);
            }
        }

        return list.toArray(new Joueur[list.size()]);
    }

}
