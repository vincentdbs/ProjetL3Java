package com.projet;

public class Chronometre extends  Thread {
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

    public int[] getTime(){
        return new int[]{milisecond, second, minute, hour};
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String toString(){
        return hour + "h" + minute + "min" + second + "s" + milisecond + "ms";
    }
}
