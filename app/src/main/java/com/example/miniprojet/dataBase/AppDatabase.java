package com.example.miniprojet.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * AppDatabase classe
 * Cette classe permet de créer la base de données de l'application
 * Elle permet de définir les entités de la base de données et la version de la base de données
 * Elle permet aussi de définir les DAOs de l'application
 */
@Database(entities = {Users.class, Questions.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UsersDAO usersDao();
    public abstract QuestionsDAO questionsDAO();
}
