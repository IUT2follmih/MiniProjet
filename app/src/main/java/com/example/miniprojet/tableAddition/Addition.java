package com.example.miniprojet.tableAddition;

public class Addition {
    private int A;
    private int B;
    private int RES;

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

    public int getResult(){
        return A+B;
    }

    public boolean isOk() {
        return RES == getResult();
    }
}
