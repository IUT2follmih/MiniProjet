package com.example.miniprojet.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Interface UsersDAO
 * Cette interface permet de définir les méthodes d'accès à la base de données pour la table users
 */
@Dao
public interface UsersDAO {
    /**
     * Méthode permettant de récupérer tous les utilisateurs
     * @return la liste de tous les utilisateurs
     */
    @Query("SELECT * FROM users")
    List<Users> getALl();

    /**
     * Méthode permettant d'ajouter un utilisateur
     * @param user l'utilisateur à ajouter
     * @return l'id de l'utilisateur ajouté
     */
    @Insert
    long insert(Users user);

    /**
     * Méthode permettant de supprimer un utilisateur
     * @param user l'utilisateur à supprimer
     */
    @Delete
    void delete(Users user);

    /**
     * Méthode permettant de mettre à jour un utilisateur
     * @param user l'utilisateur à mettre à jour
     */
    @Update
    void update(Users user);
}
