package com.projet.Fenetre;

import com.projet.Question.ListeQuestions;
import com.projet.Question.Question;
import com.projet.Themes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI_AfficherQuestion extends JFrame {
    private JComboBox<String> jcbTheme;
    private JButton jbNiveau1, jbNiveau2, jbNiveau3, jbAll;
    private JLabel jlNiveau, jlTheme;
    private ListeQuestions listeQuestions;

    public GUI_AfficherQuestion(){
        listeQuestions = new ListeQuestions();
        setSize(600,400);
        setTitle("Afficher questions");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initiate();
        getContentPane().add(placeAll(), BorderLayout.NORTH);
        setResizable(false);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void initiate(){
        jcbTheme = new JComboBox<>(new Themes().getArrayTheme());
        jlNiveau = new JLabel("Choix du niveau");
        jlTheme = new JLabel("Choix du theme");
        jbNiveau1 = new JButton("1");
        addListenerOnButton(jbNiveau1);
        jbNiveau2 = new JButton("2");
        addListenerOnButton(jbNiveau2);
        jbNiveau3 = new JButton("3");
        addListenerOnButton(jbNiveau3);
        jbAll = new JButton("Tout");
        addListenerOnButton(jbAll);
    }

    private JPanel placeAll(){
        JPanel panel = new JPanel();
        panel.add(jlTheme);
        panel.add(jcbTheme);
        panel.add(jlNiveau);
        panel.add(jbNiveau1);
        panel.add(jbNiveau2);
        panel.add(jbNiveau3);
        panel.add(jbAll);
        return panel;
    }

    private void addListenerOnButton(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String theme = jcbTheme.getSelectedItem().toString();
                if (getContentPane().getComponentCount() > 1){ //suppresion du panneau précédent
                    getContentPane().remove(1);
                }
                //todo pb avec le JScrollPane si appui sur "Tout"
                JScrollPane scrollPane = new JScrollPane(placeList(theme, button.getText()), ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//                scrollPane.setMaximumSize(new Dimension(600,400));
                scrollPane.getVerticalScrollBar().setUnitIncrement(10);
                getContentPane().add(scrollPane, BorderLayout.CENTER);
                pack();
            }
        });
    }

    private JPanel placeList(String theme, String niveau){
        List<Question> list;
        if(niveau.equals("Tout")){
            list = listeQuestions.getQuestionByTheme(theme);
        }else{
            list = listeQuestions.getQuestionByThemeLevel(theme, Integer.parseInt(niveau));
        }

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.insets = new Insets(5,5,5,5);
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.gridx = 0;
        cs.gridy = 0;
        for (Question<?> q: list) {
            JTextArea textArea = new JTextArea(q.toString(), 4,10);
            textArea.setEditable(false);
            textArea.setBackground(panel.getBackground());
            panel.add(textArea, cs);
            cs.gridy++;
        }
        return panel;
    }

}
