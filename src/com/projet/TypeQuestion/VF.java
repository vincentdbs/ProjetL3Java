package com.projet.TypeQuestion;

public class VF {
    private String tetxe;
    private boolean reponse;

    public VF(String tetxe, boolean reponse) {
        this.tetxe = tetxe;
        this.reponse = reponse;
    }

    public String getTetxe() {
        return tetxe;
    }

    public void setTetxe(String tetxe) {
        this.tetxe = tetxe;
    }

    public boolean isReponse() {
        return reponse;
    }

    public void setReponse(boolean reponse) {
        this.reponse = reponse;
    }
}
