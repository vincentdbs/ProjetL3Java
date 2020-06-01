package com.projet;

import static java.lang.Thread.sleep;

public class Stopwatch {
    private int milisecond = 0, second = 0, minute = 0, hour = 0;
    private boolean state = true;
    private Thread timer;
    public Stopwatch() {
        timer = new Thread();
    }

    public void timerStart(){
        timer = new Thread(new Runnable() {
            @Override
            public void run() {
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
        });
        timer.run();
    }

    public void timerStop(){
        state = false;
        timer.stop();
    }

    public void displayTime(){
        System.out.println(hour + "h" + minute + "min" + second + "s" + milisecond + "ms");
    }

    @Override
    public String toString(){
        return hour + "h" + minute + "min" + second + "s" + milisecond + "ms";
    }
}
