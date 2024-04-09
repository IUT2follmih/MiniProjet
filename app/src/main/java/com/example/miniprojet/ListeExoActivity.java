package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniprojet.dataBase.Users;

/**
 * Activité permettant de choisir le type d'exercice à réaliser
 * Possibilité de choisir entre les tables de multiplication, les tables d'addition et un QCM
 */
public class ListeExoActivity extends AppCompatActivity {
    // Constantes
    public static Users USER;
    public static boolean ANONYME = false;

    // Composants graphiques
    Button multi, add, qcm, retour;
    TextView userName;

    /**
     * Méthode appelée à la création de l'activité
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_exo);

        // Récupération des composants graphiques
        multi = findViewById(R.id.List_btn_multiplication);
        add = findViewById(R.id.List_btn_addition);
        qcm = findViewById(R.id.List_btn_qcm);
        retour = findViewById(R.id.List_btn_retour);

        userName = findViewById(R.id.Liste_text_user);

        // Récupération de l'utilisateur
        Users user = null;

        boolean isAno = getIntent().getBooleanExtra(String.valueOf(ANONYME), false);
        if (isAno) {
            userName.setText("Anonyme");
        } else {
            user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));
            userName.setText("Bonjour " + user.getNom() + " " + user.getPrenom() + " !");
        }

        Users finalUser = user;

        // Gestions des clics sur le bouton de l'exercice multiplcation
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, TableDeMultiplicationActivity.class);
                if (!isAno) {
                    intent.putExtra(String.valueOf(TableDeMultiplicationActivity.USER), finalUser);
                }
                startActivity(intent);
            }
        });

        // Gestions des clics sur le bouton de l'exercice d'addition
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, TableAdditionReponsesActivity.class);
                if (!isAno) {
                    intent.putExtra(String.valueOf(TableAdditionReponsesActivity.USER), finalUser);
                }
                startActivity(intent);
            }
        });

        // Gestions des clics sur le bouton de l'exercice QCM
        qcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, QCMActivity.class);
                if (!isAno) {
                    intent.putExtra(String.valueOf(QCMActivity.USER), finalUser);
                }
                startActivity(intent);
            }
        });

        // Gestions des clics sur le bouton de retour
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On vide l'utilisateur
                USER = null;
                finish();
            }
        });
    }
}