package com.projet.fenetre.question;

import com.projet.Chronometre;
import com.projet.fenetre.ConfirmExitPage;
import javax.swing.*;
import java.awt.*;

/**
 * DORFFER - DUBOIS - ESTEBAN - GOMEZ CASTELLON - MATHIEN
 */

public class GUI_Question extends JDialog {

    private Chronometre chronometre = new Chronometre();
    private String answer;

    public GUI_Question(Frame parent) {
        super(parent, true); //true pour que la fenetre bloque le programme tant qu'elle est affichée
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(ConfirmExitPage.exitWindow()); //action custom on click de l'action de fermeture de la fenetre
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
