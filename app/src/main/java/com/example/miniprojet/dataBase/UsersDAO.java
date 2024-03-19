package com.example.miniprojet.dataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface UsersDAO {
    @Query("SELECT * FROM users")
    List<Users> getALl();

    @Insert
    long insert(Users user);

    @Delete
    void delete(Users user);

    @Update
    void update(Users user);
}
