package com.projet;

import java.util.Objects;

public class Chronometre extends  Thread implements Comparable {
    private int milisecond = 0, second = 0, minute =0, hour =0;
    private boolean state = true;

    public Chronometre(){
        super();
    }

    public void run(){
        while (state){
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            milisecond++;
            if(milisecond > 999){
                milisecond = 0;
                second++;
                if(second > 59){
                    second = 0;
                    minute++;
                    if(minute > 59){
                        minute = 0;
                        hour++;
                    }
                }
            }

        }
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
