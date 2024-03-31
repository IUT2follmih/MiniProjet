package com.example.miniprojet.dataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class, Questions.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{
    public abstract UsersDAO usersDao();
    public abstract QuestionsDAO questionsDao();
}
