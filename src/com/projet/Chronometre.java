package com.projet;

import java.util.Date;
import java.util.Objects;

public class Chronometre extends  Thread implements Comparable {
    private int milisecond = 0, second = 0, minute =0, hour =0;
    private boolean state = true;
    private long debut, fin;

    public Chronometre(){
        super();
        debut = System.currentTimeMillis(); //debut de la question
        fin = System.currentTimeMillis(); //fin de la question
    }

    public void run(){
        while (state){
            try {
                sleep(10); //marge d'erreur de 10ms
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            setAllTime();
        }
    }

    private void setAllTime(){
        long diff = fin-debut; //calcul du temps écoulé + conversion ms => h:min:s:ms
        milisecond = (int) (diff % 1000);
        second = (int) (diff / 1000) % 60 ;
        minute = (int) ((diff / (1000*60)) % 60);
        hour   = (int) ((diff / (1000*60*60)) % 24);
    }

    public int getMilisecond() {
        return milisecond;
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int[] getTime(){
        return new int[]{milisecond, second, minute, hour};
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String toString(){
        return hour + "h" + minute + "min" + second + "s" + milisecond + "ms";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chronometre that = (Chronometre) o;
        return milisecond == that.milisecond &&
                second == that.second &&
                minute == that.minute &&
                hour == that.hour &&
                state == that.state;
    }

    @Override
    public int compareTo(Object o) {
        Chronometre c = (Chronometre) o;
        if(hour < c.hour){
            return -1;
        }else if (hour > c.hour){
            return 1;
        }else{
            if (minute < c.minute){
                return  -1;
            }else if(minute > c.minute){
                return 1;
            }else{
                if (second < c.second){
                    return  -1;
                }else if(second > c.second){
                    return 1;
                }else{
                    if (milisecond < c.milisecond){
                        return  -1;
                    }else if(milisecond > c.milisecond){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }
        }
    }
}
