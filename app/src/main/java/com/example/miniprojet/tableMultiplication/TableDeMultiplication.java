package com.example.miniprojet.tableMultiplication;

import java.util.ArrayList;

/**
 * Classe représentant une table de multiplication
 */
public class TableDeMultiplication {

    public int TABLE = 0;

    public ArrayList<Multiplication> multiplications = new ArrayList<>();

    /**
     * Constructeur
     * @param table la table de multiplication
     */
    public TableDeMultiplication(int table) {
        TABLE = table;
        initMultiplication();
    }

    /**
     * Initialisation des multiplications
     * On initialise les multiplications de 1 à 10
     */
    private void initMultiplication() {
        for (int i = 1; i <= 10; i++) {
            multiplications.add(new Multiplication(i, TABLE));
        }
    }

    public ArrayList<Multiplication> getMultiplications() {
        return multiplications;
    }

    /**
     * Méthode permettant de récupérer le nombre de multiplication correctes
     * On parcourt la liste de multiplication et on compte le nombre de multiplication correctes
     * @return le nombre de multiplication correctes
     */
    public int getNbErreurs() {
        int c = 0;
        for (Multiplication mult : multiplications) {
            if (!mult.isOk()) {
                c++;
            }
        }
        return c;
    }
}