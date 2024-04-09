package com.example.miniprojet.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Interface QuestionsDAO
 * Cette interface permet de définir les méthodes d'accès à la base de données pour la table questions
 */
@Dao
public interface QuestionsDAO {
    /**
     * Méthode permettant de récupérer toutes les questions
     * @return la liste de toutes les questions
     */
    @Query("SELECT * FROM questions")
    List<Questions> getALl();

    /**
     * Méthode permettant de récupérer une question en fonction de son id
     * @param id l'id de la question
     * @return la question
     */
    @Query("SELECT * FROM questions WHERE id=:id")
    Questions getOne(long id);

    /**
     * Méthode permettant de récupérer une question en fonction de son type
     * @param type le type de la question
     * @return la question
     */
    @Query("SELECT * FROM questions WHERE type=:type ORDER BY RANDOM() LIMIT :number")
    List<Questions> getRandomQuestions(int type, int number);

    /**
     * Méthode permettant d'ajouter une question
     * @param question la question à ajouter
     * @return l'id de la question ajoutée
     */
    @Insert
    long insert(Questions question);

    /**
     * Méthode permettant de supprimer une question
     * @param question la question à supprimer
     */
    @Delete
    void delete(Questions question);

    /**
     * Méthode permettant de mettre à jour une question
     * @param question la question à mettre à jour
     */
    @Update
    void update(Questions question);
}
