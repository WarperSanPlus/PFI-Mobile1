package com.example.pfi.Classes;

public class Client {
    // region Nom
    String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) { this.nom = nom; }
    // endregion
    // region Password
    String mdp ;

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) { this.mdp = mdp; }
    // endregion

    public Client(String nom, String mdp) {
        setNom(nom);
        setMdp(mdp);
    }
}
