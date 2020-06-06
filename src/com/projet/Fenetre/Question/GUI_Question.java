package com.projet.Fenetre.Question;

import com.projet.Chronometre;

import javax.swing.*;
import java.awt.*;

public class GUI_Question extends JDialog {

    private Chronometre chronometre = new Chronometre();
    private String answer;

    public GUI_Question(Frame parent) {
        super(parent, true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        chronometre.start();
        answer = "";
    }

    public void stopChronometre(){
        chronometre.setState(false);
    }

    public Chronometre getChronometre() {
        return chronometre;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
