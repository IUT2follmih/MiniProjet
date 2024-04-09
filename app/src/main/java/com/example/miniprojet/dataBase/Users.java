package com.example.miniprojet.dataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

/**
 * Classe représentant une entité utilisateur
 *
 */
@Entity(tableName = "users")
public class Users implements Serializable {
    private String Nom;
    private String Prenom;

    @PrimaryKey(autoGenerate = true)
    private long id;

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
