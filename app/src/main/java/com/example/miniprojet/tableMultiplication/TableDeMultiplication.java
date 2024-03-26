package com.example.miniprojet.tableMultiplication;

import java.util.ArrayList;

public class TableDeMultiplication {

    public int TABLE = 0;

    public ArrayList<Multiplication> multiplications = new ArrayList<>();

    public TableDeMultiplication(int table){
        TABLE = table;
        initMultiplication();
    }

    private void initMultiplication(){
        for (int i = 1; i<10; i++ ){
            multiplications.add(new Multiplication(i, TABLE));
        }
    }

    public ArrayList<Multiplication> getMultiplications() {
        return multiplications;
    }

    public int getNbErreurs(){
        int c = 0;
        for (Multiplication mult:multiplications) {
            if(!mult.isOk()){
                c++;
            }
        }
        return c;
    }
}