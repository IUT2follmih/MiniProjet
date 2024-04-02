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
        // TODO : fix double database creation
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
        }
    };

    public QuestionsDAO getQuestionsDAO() {
        return appDatabase.questionsDAO();
    }

    public void creatioInitQuestions() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (appDatabase.questionsDAO().getALl().isEmpty()) {
                    for (int i = 0; i < questions.length; i++) {
                        Questions question = new Questions(types[i], questions[i], reponseCorrect[i], reponseFausse1[i], reponseFausse2[i], reponseFausse3[i]);
                        appDatabase.questionsDAO().insert(question);
                    }
                }
            }
        }).start();
    }

    public static final String[] types = {
            // 1 = Français
            // 2 = Histoire
            // 3 = Géographie
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "1",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "2",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
            "3",
    };

    public static final String[] questions = {
            // Français
            "Que signifie le mot 'époustouflant' ?",
            "Que signifie le mot 'moudre' ?",
            "Que signifie le mot 'embrasement' ?",
            "Que signifie le mot 'débiter' ?",

            "Quel est le synonyme de 'beau' ?",
            "Quel est le synonyme de 'laid' ?",
            "Quel est le synonyme de 'grand' ?",
            "Quel est le synonyme de 'petit' ?",

            "Quel est le contraire de 'jeter' ?",
            "Quel est le contraire de 'accroupi' ?",
            "Quel est le contraire de 'courant' ?",
            "Quel est le contraire de 'Rapide' ?",

            // Histoire
            "Quel est le nom du premier président des Etats-Unis ?",
            "Quel est le nom du premier roi de France ?",
            "Quel est le nom du premier empereur romain ?",
            "Quel est le nom du premier roi d'Angleterre ?",

            "Quel est la date de la prise de la Bastille ?",
            "Quel est la date de la fin de la seconde guerre mondiale ?",
            "Quel est la date de la mort de Louis XVI ?",
            "Quel est la date de decouverte de l''Amérique par Christophe Colomb ?",

            "Quel est l''evenement le plus emblématique du japon ?",
            "Quel est l''evenement le plus emblématique de la France ?",
            "Quel est l''evenement le plus emblématique de l''Allemagne ?",
            "Quel est l''evenement le plus emblématique de l''Italie ?",

            // Géographie
            "Quel est le nom de la capitale de la France ?",
            "Quel est le nom de la capitale de l''Allemagne ?",
            "Quel est le nom de la capitale de la Chine ?",
            "Quel est le nom de la capitale de la Hongrie ?",

            "Quel est le plus grand pays du monde ?",
            "Quel est le plus petit pays du monde ?",
            "Quel est le pays le plus peuplé du monde ?",
            "Quel est le pays le moins peuplé du monde ?",

            "Ou se trouve le plus grand desert du monde ?",
            "Ou se trouve la ville de New York ?",
            "Ou se trouve la ville de Tokyo ?",
            "Ou se trouve la ville de Paris ?",
    };

    public static String[] reponseCorrect = {
            // Français
            "Surprenant",
            "Broyer",
            "Feu",
            "Parler",

            "Joli",
            "Moche",
            "Haut",
            "Petit",

            "Ramasser",
            "Droit",
            "Rare",
            "Lent",

            // Histoire
            "George Washington",
            "Clovis",
            "Jules César",
            "Guillaume le Conquérant",

            "14 juillet 1789",
            "8 mai 1945",
            "21 janvier 1793",
            "12 octobre 1492",

            "La bombe atomique",
            "La revolution",
            "La chute du mur de Berlin",
            "La coupe du monde",

            // Géographie
            "Paris",
            "Berlin",
            "Pékin",
            "Budapest",

            "Russie",
            "Vatican",
            "Chine",
            "Tuvalu",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",
    };

    public static String[] reponseFausse1 = {
            // Français
            "Surprenant",
            "Broyer",
            "Feu",
            "Parler",

            "Joli",
            "Moche",
            "Haut",
            "Petit",

            "Ramasser",
            "Droit",
            "Rare",
            "Lent",

            // Histoire
            "George Washington",
            "Clovis",
            "Jules César",
            "Guillaume le Conquérant",

            "14 juillet 1789",
            "8 mai 1945",
            "21 janvier 1793",
            "12 octobre 1492",

            "La bombe atomique",
            "La revolution",
            "La chute du mur de Berlin",
            "La coupe du monde",

            // Géographie
            "Paris",
            "Berlin",
            "Pékin",
            "Budapest",

            "Russie",
            "Vatican",
            "Chine",
            "Tuvalu",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",
    };

    public static String[] reponseFausse2 = {
            // Français
            "Surprenant",
            "Broyer",
            "Feu",
            "Parler",

            "Joli",
            "Moche",
            "Haut",
            "Petit",

            "Ramasser",
            "Droit",
            "Rare",
            "Lent",

            // Histoire
            "George Washington",
            "Clovis",
            "Jules César",
            "Guillaume le Conquérant",

            "14 juillet 1789",
            "8 mai 1945",
            "21 janvier 1793",
            "12 octobre 1492",

            "La bombe atomique",
            "La revolution",
            "La chute du mur de Berlin",
            "La coupe du monde",

            // Géographie
            "Paris",
            "Berlin",
            "Pékin",
            "Budapest",

            "Russie",
            "Vatican",
            "Chine",
            "Tuvalu",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",
    };

    public static String[] reponseFausse3 = {
            // Français
            "Surprenant",
            "Broyer",
            "Feu",
            "Parler",

            "Joli",
            "Moche",
            "Haut",
            "Petit",

            "Ramasser",
            "Droit",
            "Rare",
            "Lent",

            // Histoire
            "George Washington",
            "Clovis",
            "Jules César",
            "Guillaume le Conquérant",

            "14 juillet 1789",
            "8 mai 1945",
            "21 janvier 1793",
            "12 octobre 1492",

            "La bombe atomique",
            "La revolution",
            "La chute du mur de Berlin",
            "La coupe du monde",

            // Géographie
            "Paris",
            "Berlin",
            "Pékin",
            "Budapest",

            "Russie",
            "Vatican",
            "Chine",
            "Tuvalu",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",
    };
}

