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
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyQuestions").addCallback(roomDatabaseCallback).build();
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

            //Ajout des questions
            //Francais
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le synonyme de beau ?', 'Joli')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le synonyme de laid ?', 'Moche')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le synonyme de grand ?', 'Immense')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le synonyme de petit ?', 'Minuscule')");

            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Que signifie le mot ''percussion'' ?', 'Impact')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Que signifie le mot ''moudre'' ?', 'Réduire en poudre')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Que signifie le mot ''embrasement'' ?', 'Enflammer')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Que signifie le mot ''débiter'' ?', 'Couper en morceaux')");

            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le contraire de ''jeter'' ?', 'Ramasser')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le contraire de ''accroupi'' ?', 'Debout')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le contraire de ''courant'' ?', 'Rare')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Francais', 'Quel est le contraire de ''Rapide'' ?', 'Lent')");

            //Histoire
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est le nom du premier président des Etats-Unis ?', 'George Washington')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est le nom du premier roi de France ?', 'Clovis Ier')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est le nom du premier empereur romain ?', 'Auguste César')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est le nom du premier roi d''Angleterre ?', 'Guillaume le Conquérant')");

            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est la date de la prise de la Bastille ?', '14 juillet 1789')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est la date de la fin de la seconde guerre mondiale ?', '8 mai 1945')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est la date de la mort de Louis XVI ?', '21 janvier 1793')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est la date de decouverte de l''Amérique par Christophe Colomb ?', '12 octobre 1492')");

            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est l''evenement le plus emblématique du japon', 'Hiroshima')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est l''evenement le plus emblématique de la France', 'La revolution francaise')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est l''evenement le plus emblématique de l''Allemagne', 'La chute du mur de Berlin')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Histoire', 'Quel est l''evenement le plus emblématique de l''Italie', 'La chute de l''empire romain')");

            //Geographie
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Quel est le plus grand pays du monde ?', 'Russie')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Quel est le plus petit pays du monde ?', 'Vatican')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Quel est le pays le plus peuplé du monde ?', 'Chine')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Quel est le pays le moins peuplé du monde ?', 'Vatican')");

            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Ou se trouve le plus grand desert du monde ?', 'Afrique')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Ou se trouve la ville de New York ?', 'Etats-Unis')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Ou se trouve la ville de Tokyo ?', 'Japon')");
            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'Ou se trouve la ville de Paris ?', 'France')");

            db.execSQL("INSERT INTO questions (type, question, reponse) VALUES ('Geographie', 'La quelle est une capitale ?', 'Paris')");
            // TODO : Ajouter des questions
        }
    };
}
