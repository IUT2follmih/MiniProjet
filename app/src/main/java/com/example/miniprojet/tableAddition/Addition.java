package com.example.miniprojet.tableAddition;

/**
 * Classe Addition
 * Représente une addition à deux opérandes A et B et un résultat RES
 */
public class Addition {
    private int A;
    private int B;
    private int RES;

    /**
     * Constructeur
     * @param a opérande A
     * @param b opérande B
     */
    public Addition(int a, int b) {
        this.A = a;
        this.B = b;
    }

    public void setRES(int reponse) {
        this.RES = reponse;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    /**
     * Retourne le résultat de l'addition
     * @return
     */
    public int getResult(){
        return A+B;
    }

    /**
     * Vérifie si la réponse donnée est correcte
     * @return
     */
    public boolean isOk() {
        return RES == getResult();
    }
}
