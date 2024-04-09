package com.example.miniprojet.tableAddition;

import java.util.ArrayList;

/**
 * Classe TableAddition
 * Cette classe permet de générer une table d'addition
 * Elle contient une liste d'addition
 */
public class TableAddition {
    public ArrayList<Addition> additions = new ArrayList<>();

    /**
     * Constructeur de la classe TableAddition
     */
    public TableAddition() {
        initAddition();
    }

    /**
     * Méthode permettant d'initialiser la table d'addition
     * On génère 10 additions aléatoires
     * Les nombres sont compris entre 0 et 10
     * Les additions sont stockées dans la liste d'addition
     * On utilise la méthode Math.random() pour générer des nombres aléatoires
     */
    private void initAddition() {
        for (int i = 1; i <= 10; i++) {
            // TODO : repasser sur des nombre aleratoire de 100
            int a = (int) (Math.random() * 10);
            int b = (int) (Math.random() * 10);
            additions.add(new Addition(a, b));
        }
    }

    public ArrayList<Addition> getAdditions() {
        return additions;
    }

    public Addition getAddition(int i) {
        return additions.get(i);
    }

    /**
     * Méthode permettant de récupérer le nombre d'additions correctes
     * On parcourt la liste d'addition et on compte le nombre d'additions correctes
     * @return le nombre d'additions correctes
     */
    public int getNbErreurs() {
        int c = 0;
        for (Addition add : additions) {
            if (!add.isOk()) {
                c++;
            }
        }
        return c;
    }


}