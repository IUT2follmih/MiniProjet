package com.example.miniprojet;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.dataBase.Users;

import java.io.Serializable;

/**
 * Activité permettant d'afficher le résultat d'un exercice
 */
public class ResultatActivity extends AppCompatActivity implements Serializable {
    // Variables
    public static Users USER;
    public static String NOM_EXO = "NOMEXO";
    public static String NB_ERROR = "0";
    public static String TABLE_KEY = "1";

    // Composants graphiques
    Button btnReExo, btnReUser, btnRecomecer;
    TextView name, note, result;

    /**
     * Méthode appelée à la création de l'activité
     * @param savedInstanceState état de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        // Récupération des composants graphiques
        btnReExo = findViewById(R.id.Result_btn_retour_exo);
        btnReUser = findViewById(R.id.Result_btn_retour_comptes);
        btnRecomecer = findViewById(R.id.Result_btn_recommencer);

        name = findViewById(R.id.Result_text_name);
        note = findViewById(R.id.Result_text_note);
        result = findViewById(R.id.Result_text_result);

        // Récupération des informations
        String nomExo = getIntent().getStringExtra(NOM_EXO);
        Integer errors = getIntent().getIntExtra(NB_ERROR, 0);

        // Récupération de l'utilisateur
        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));

        name.setText("Resultat de l'exercice : " + nomExo);
        note.setText((10 - errors) + "/10");
        if (errors > 3) {
            note.setTextColor(Color.RED);
            result.setText("Vous n'avez pas réussi l'exercice !");
        } else {
            note.setTextColor(Color.GREEN);
            result.setText("Vous avez réussi l'exercice !!");
        }

        /**
         * Gestion des événements sur les boutons de l'activité :
         * - btnReExo : retour à la liste des exercices
         * - btnReUser : retour à la page de connexion
         * - btnRecomecer : recommencer l'exercice
         */
        btnRecomecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomExo.equals("Table de multiplication")) {
//                    if (errors > 3) {
//                        btnRecomecer.setText("Corriger mes fautes");
//                        // TODO : faire la correction de fautes
//                    } else {
//                        Intent intent = new Intent(ResultatActivity.this, TableDeMultiplicationReponsesActivity.class);
//                        intent.putExtra(TableDeMultiplicationReponsesActivity.TABLE_KEY, getIntent().getIntExtra(TABLE_KEY, 1));
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }

                    Intent intent = new Intent(ResultatActivity.this, TableDeMultiplicationReponsesActivity.class);
                    intent.putExtra(String.valueOf(TableDeMultiplicationReponsesActivity.USER), user);
                    intent.putExtra(TableDeMultiplicationReponsesActivity.TABLE_KEY, getIntent().getIntExtra(TABLE_KEY, 1));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (nomExo.equals("Table d'addition")) {
//                    if (errors > 3) {
//                        btnRecomecer.setText("Corriger mes fautes");
//                        // TODO : faire la correction de fautes
//                    } else {
//                        Intent intent = new Intent(ResultatActivity.this, TableAdditionReponsesActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }

                    Intent intent = new Intent(ResultatActivity.this, TableAdditionReponsesActivity.class);
                    intent.putExtra(String.valueOf(TableAdditionReponsesActivity.USER), user);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (nomExo.equals("QCM")) {
//                    if (errors > 3) {
//                        btnRecomecer.setText("Corriger mes fautes");
//                        // TODO : faire la correction de fautes
//                    } else {
//                        Intent intent = new Intent(ResultatActivity.this, QCMReponsesActivity.class);
//                        int type = getIntent().getIntExtra("type", 4);
//                        intent.putExtra("type", type);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
                    Intent intent = new Intent(ResultatActivity.this, QCMActivity.class);
                    intent.putExtra(String.valueOf(QCMActivity.USER), user);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        btnReExo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, ListeExoActivity.class);
                if (user == null) {
                    intent.putExtra(String.valueOf(ListeExoActivity.ANONYME), true);
                } else {
                    intent.putExtra(String.valueOf(ListeExoActivity.ANONYME), false);
                    intent.putExtra(String.valueOf(ListeExoActivity.USER), user);
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnReUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    /**
     * Méthode appelée lors de l'appui sur le bouton retour du téléphone
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        btnReExo.callOnClick();
    }
}