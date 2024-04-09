package com.example.miniprojet.dataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 *  DataBaseClient
 *  Singleton qui permet de créer une instance de la base de données
 *  et de la retourner
 *  Cette classe permet aussi de peupler la base de données à sa création
 */
public class DataBaseClient {

    private static DataBaseClient instance;
    private AppDatabase appDatabase;

    /**
     * Constructeur
     * @param context
     */
    private DataBaseClient(final Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "AppDatabase").addCallback(roomDatabaseCallback).build();
    }

    /**
     * Méthode statique
     * Retourne l'instance de l'objet DataBaseClient
     * @param context
     * @return
     */
    public static synchronized DataBaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseClient(context);
        }
        return instance;
    }

    /**
     * Retourne l'objet représentant la base de données de votre application
     * @return
     */
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    /**
     * Objet permettant de peupler la base de données à sa création
     */
    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    /**
     * Retourne l'objet représentant la table Questions
     * @return
     */
    public QuestionsDAO getQuestionsDAO() {
        return appDatabase.questionsDAO();
    }

    /**
     * Peuple la base de données à sa création
     * avec les questions et les réponses
     */
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

    /**
     * Tableau des types de questions
     */
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

    /**
     * Tableau des questions
     */
    public static final String[] questions = {
            // Français
            "Que signifie le mot 'époustouflant' ?",
            "Que signifie le mot 'moudre' ?",
            "Que signifie le mot 'Raffiner' ?",
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

            "Quelle est la date de la prise de la Bastille ?",
            "Quelle est la date de la fin de la seconde guerre mondiale ?",
            "Quelle est la date de la mort de Louis XVI ?",
            "Quelle est la date de decouverte de l''Amérique par Christophe Colomb ?",

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

    /**
     * Tableau des réponses
     */
    public static String[] reponseCorrect = {
            // Français
            "Surprenant",
            "Broyer",
            "Delicat",
            "Ecouler",

            "Joli",
            "Moche",
            "Haut",
            "Pas grand",

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
            "La revolution Francaise",
            "La chute du mur de Berlin",
            "Fondation de Rome",

            // Géographie
            "Paris",
            "Berlin",
            "Pékin",
            "Budapest",

            "Russie",
            "Vatican",
            "Inde",
            "Antarctique",

            "Afrique",
            "Etats-Unis",
            "Japon",
            "France",
    };

    /**
     * Tableau des réponses fausses 1
     */
    public static String[] reponseFausse1 = {
            /// Français
            "Banal",
            "Remuer",
            "Brut",
            "Stocker",

            "Abject",
            "Eblouissant",
            "Court",
            "Adulte",

            "Laisser",
            "Baisser",
            "Normal",
            "Accéléré",

            // Histoire
            "Abraham Lincoln",
            "Louis XIV",
            "Napoléon Bonaparte",
            "Richard the Lionheart",

            "19 Julliet 1951",
            "05 Novembre 1800",
            "24 Juin 1836",
            "19 Mar 1811",

            "Proclamation de la République Populaire",
            "Fete de la musique",
            "La marche de l''Est",
            "La coupe du monde",

            // Géographie
            "Lyon",
            "Munich",
            "Shanghai",
            "Prague",

            "Canada",
            "Monaco",
            "Chine",
            "Tuvalu",

            "Arabie Saoudite",
            "Mexique",
            "Corée du Sud",
            "Angleterre",
    };

    /**
     * Tableau des réponses fausses 2
     */
    public static String[] reponseFausse2 = {
            // Français
            "Mediocre",
            "Casser",
            "Grossier",
            "Garder",

            "Déplaisant",
            "Angélique",
            "Bas",
            "Enorme",

            "Abandonner",
            "Replier",
            "Habituel",
            "Instantané",

            // Histoire
            "Thomas Jefferson",
            "Louis XVI",
            "Augustus",
            "Henry VIII",

            "20 Mars 1948",
            "20 Octobre 1760",
            "25 Juillet 1971",
            "23 Juillet 1962",

            "Guerre de 100 ans",
            "Noel",
            "La prise de la Bastille",
            "Katheri Deftera",

            // Géographie
            "Marseille",
            "Hamburg",
            "Guangzhou",
            "Vienna",

            "Etats-Unis",
            "Nauru",
            "Indonésie",
            "Vatican",

            "Australie",
            "Canada",
            "Chine",
            "Espagne",
    };

    /**
     * Tableau des réponses fausses 3
     */
    public static String[] reponseFausse3 = {
            // Français
            "Drole",
            "Melanger",
            "Maladroit",
            "Bloquer",

            "Horrible",
            "Divin",
            "Abime",
            "Démesuré",

            "Debarrasser",
            "Plier",
            "Commun",
            "Soudain",

            // Histoire
            "John Adams",
            "Charles VII",
            "Marcus Aurelius",
            "Edward the Confessor",

            "1 Fevrier 1618",
            "19 Mai 1973",
            "8 Mai 1619",
            "6 Decembre 1665",

            "La guerre d''indépendance",
            "L''assomption",
            "Révolte contre le régime de Slobodan Milošević",
            "Épiphanie",

            // Géographie
            "Toulouse",
            "Frankfurt",
            "Chongqing",
            "Warsaw",

            "Chine",
            "Tuvalu",
            "Pakistan",
            "Palaos",

            "Etats-Unis",
            "Brésil",
            "Vietnam",
            "Grece",
    };
}

