package com.example.miniprojet.dataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


public class DataBaseClient {

    private static DataBaseClient instance;
    private AppDatabase appDatabase;

    private DataBaseClient(final Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyUsers").addCallback(roomDatabaseCallback).build();
    }

    public static synchronized DataBaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
