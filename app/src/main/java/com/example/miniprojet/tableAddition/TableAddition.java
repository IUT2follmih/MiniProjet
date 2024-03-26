package com.example.miniprojet.tableAddition;

import java.util.ArrayList;

public class TableAddition {
    public ArrayList<Addition> additions = new ArrayList<>();

    public TableAddition(){
        initAddition();
    }

    private void initAddition(){
        for (int i = 1; i<10; i++ ){
            // je veux des addition aléatoire entre 1 et 100
            int a = (int) (Math.random() * 10);
            int b = (int) (Math.random() * 10);
            additions.add(new Addition(a, b));
        }
    }

    public ArrayList<Addition> getAdditions() {
        return additions;
    }

    public Addition getAddition(int i){
        return additions.get(i);
    }

    public int getNbErreurs(){
        int c = 0;
        for (Addition add:additions) {
            if(!add.isOk()){
                c++;
            }
        }
        return c;
    }


}
