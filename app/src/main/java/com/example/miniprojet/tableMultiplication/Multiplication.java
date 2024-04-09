package com.example.miniprojet.tableMultiplication;

/**
 * Classe représentant une multiplication
 */
public class Multiplication {
    private int A;
    private int B;
    private int RES;

    /**
     * Constructeur
     * @param a
     * @param b
     */
    public Multiplication(int a, int b) {
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
     * Retourne le résultat de la multiplication
     * @return
     */
    public int getResult(){
        return A*B;
    }

    /**
     * Vérifie si la réponse donnée est correcte
     * @return
     */
    public boolean isOk() {
        return RES == getResult();
    }
}