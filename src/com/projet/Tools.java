package com.projet;

import com.projet.Joueur.Joueur;
import com.projet.Question.Question;
import com.projet.Question.Type.QCM;
import com.projet.Question.Type.RC;
import com.projet.Question.Type.VF;

import java.util.Arrays;
import java.util.Comparator;

public final class Tools {
    private Tools() {}

    /**
     * Renvoie le nom du type de la Question passée en paramètre
     */
    public static String getQuestionType(Question question){
        switch (question.getEnonce().getClass().getName()){
            case "com.projet.Question.Type.QCM":{
                return "QCM";
            }
            case "com.projet.Question.Type.VF":{
                return "VF";
            }
            case "com.projet.Question.Type.RC":{
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
                .min(new Comparator<Chronometre>() {
                    @Override
                    public int compare(Chronometre o1, Chronometre o2) {
                        if(o1.getHour() < o2.getHour()){
                            return -1;
                        }else if (o1.getHour() > o2.getHour()){
                            return 1;
                        }else{
                            if (o1.getMinute() < o2.getMinute()){
                                return  -1;
                            }else if(o1.getMinute() > o2.getMinute()){
                                return 1;
                            }else{
                                if (o1.getSecond()< o2.getSecond()){
                                    return  -1;
                                }else if(o1.getSecond() > o2.getSecond()){
                                    return 1;
                                }else{
                                    if (o1.getMilisecond() < o2.getMilisecond()){
                                        return  -1;
                                    }else if(o1.getMilisecond()> o2.getMilisecond()){
                                        return 1;
                                    }else{
                                        return 0;
                                    }
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
                .min(new Comparator<Chronometre>() {
                    @Override
                    public int compare(Chronometre o1, Chronometre o2) {
                        if(o1.getHour() < o2.getHour()){
                            return 1;
                        }else if (o1.getHour() > o2.getHour()){
                            return -1;
                        }else{
                            if (o1.getMinute() < o2.getMinute()){
                                return  11;
                            }else if(o1.getMinute() > o2.getMinute()){
                                return -1;
                            }else{
                                if (o1.getSecond()< o2.getSecond()){
                                    return  1;
                                }else if(o1.getSecond() > o2.getSecond()){
                                    return -1;
                                }else{
                                    if (o1.getMilisecond() < o2.getMilisecond()){
                                        return  1;
                                    }else if(o1.getMilisecond()> o2.getMilisecond()){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
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
//        ArrayList<Integer> joueursEliminie = new ArrayList<>();
//        int index = 0;
//        for (int i = 1; i < joueurs.length ; i++) {
//            if(joueurs[i].getScore() < joueurs[index].getScore()){
//                index = i;
//            }
//            else if(joueurs[i].getScore() == joueurs[index].getScore()){
//                if(chrono[i].compareTo(chrono[index]) > 0){
//                    index = i;
//                }
//                else if(chrono[i].compareTo(chrono[index]) == 0){
//                    joueursEliminie.add(i);
//                }
//            }
//        }


        //récupération du score le plus bas
        int scoreMin = joueurs[0].getScore();
        for (int i = 1; i < joueurs.length; i++) {
            if(joueurs[i].getScore() < scoreMin){
                scoreMin = joueurs[i].getScore();
            }
        }
        //récupération des joueurs ayant le score le plus bas
        int finalScoreMin = scoreMin;
        return (Joueur[]) Arrays.stream(joueurs)
                .filter(j -> j.getScore() == finalScoreMin)
                .toArray(Joueur[]::new);
    }

}
