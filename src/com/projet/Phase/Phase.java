package com.projet.Phase;

import com.projet.Joueur.Joueur;

public interface Phase {
    //todo à completer + changer type de retour en fonction des consignes
    void selectionnerJoueur();
    void phaseDeJeu(Joueur... joueurs);
    void phaseDeJeu();
}
