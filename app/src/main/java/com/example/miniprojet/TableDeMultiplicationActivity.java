package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.dataBase.Users;

/**
 * Activité permettant de choisir la table de multiplication à réviser
 */
public class TableDeMultiplicationActivity extends AppCompatActivity {
    // Variable permettant de stocker l'utilisateur
    public static Users USER;

    // Composants graphiques
    NumberPicker nbPicker;
    Button btnValider, btnRetour;

    /**
     * Méthode appelée à la création de l'activité
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_de_multiplication);

        // Récupération des composants graphiques
        nbPicker = findViewById(R.id.Table_mult_nbPicker);
        nbPicker.setMaxValue(9);
        nbPicker.setMinValue(1);
        btnValider = findViewById(R.id.Table_mult_btnTable);
        btnRetour = findViewById(R.id.Table_mult_button_retour);

        // Récupération de l'utilisateur
        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));
        //Log.d("TableDeMultiplicationActivity", "TableDeMult: " + user);

        // Gestion du clic sur le bouton de retour
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        // Gestion du clic sur le bouton de validation
        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableDeMultiplicationActivity.this, TableDeMultiplicationReponsesActivity.class);
                intent.putExtra(TableDeMultiplicationReponsesActivity.TABLE_KEY, nbPicker.getValue());
                intent.putExtra(String.valueOf(TableDeMultiplicationReponsesActivity.USER), user);
                startActivity(intent);
            }
        });
    }
}
