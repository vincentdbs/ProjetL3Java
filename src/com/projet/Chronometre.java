package com.projet;

/**
 * DORFFER - DUBOIS - ESTEBAN - GOMEZ CASTELLON - MATHIEN
 */

public class Chronometre extends  Thread implements Comparable<Chronometre> {
    private int milisecond, second, minute, hour;
    private boolean state;
    private final long debut;
    private long fin;

    public Chronometre(){
        super();
        //initialisation au temps actuel
        debut = System.currentTimeMillis(); //debut de la question
        fin = System.currentTimeMillis(); //fin de la question
        milisecond = 0;
        second = 0;
        minute = 0;
        hour = 0;
        state = true;
    }

    public void run(){
        while (state){
            try {
                sleep(10); //marge d'erreur de 10ms
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            fin = System.currentTimeMillis();
            setAllTime(); //decompte du temps écoulé
        }
    }

    /**
     * calcul du temps écoulé + conversion ms à partir de la différence début/fin => h:min:s:ms
     */
    private void setAllTime(){
        long diff = fin-debut;
        milisecond = (int) (diff % 1000);
        second = (int) (diff / 1000) % 60 ;
        minute = (int) ((diff / (1000*60)) % 60);
        hour   = (int) ((diff / (1000*60*60)) % 24);
    }

    /**
     * calcul du temps écoulé + conversion ms à partir du temps en ms passé en paramtre => h:min:s:ms
     */
    private void setAllTime(long ms){
        milisecond = (int) (ms % 1000);
        second = (int) (ms / 1000) % 60 ;
        minute = (int) ((ms / (1000*60)) % 60);
        hour   = (int) ((ms / (1000*60*60)) % 24);
    }

    /**
     * Getter et setter
     */
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

    public void setState(boolean state) {
        this.state = state;
    }

    public String toString(){
        return hour + "h" + minute + "min" + second + "s" + milisecond + "ms";
    }

    /**
     * Ajout d'un chronométre à un autre
     */
    public void add(Chronometre c){
        long ms = this.milisecond + c.milisecond; //reconstitution du temps en milliseconds
        ms += ((this.second + c.second)*1000);
        ms += ((this.minute + c.minute)*1000*60);
        ms += ((this.hour + c.hour)*1000*60*60);
        setAllTime(ms); //decompostion et stockage du temps en h:min:s:ms
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
    public int compareTo(Chronometre o) {
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
