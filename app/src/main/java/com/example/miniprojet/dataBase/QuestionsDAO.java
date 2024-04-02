package com.example.miniprojet.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionsDAO {
    @Query("SELECT * FROM questions")
    List<Questions> getALl();

    @Query("SELECT * FROM questions WHERE id = :id")
    Questions getOne(long id);

    @Query("SELECT * FROM questions WHERE type = :type ORDER BY RANDOM() LIMIT :number")
    List<Questions> getRandomQuestions(int type, int number);

    @Insert
    long insert(Questions question);

    @Delete
    void delete(Questions question);

    @Update
    void update(Questions question);
}
