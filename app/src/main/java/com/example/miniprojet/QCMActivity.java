package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniprojet.dataBase.Users;

/**
 * Activité permettant de choisir le type de QCM
 */
public class QCMActivity extends AppCompatActivity {
    // Constante
    public static Users USER;

    // Composants graphiques
    Button btnRetour, btnValider;
    RadioGroup radioGroup;

    /**
     * Méthode appelée à la création de l'activité
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcmactivity);

        // Récupération des composants graphiques
        btnRetour = findViewById(R.id.QCM_btn_retour);
        btnValider = findViewById(R.id.QCM_btn_valider);
        radioGroup = findViewById(R.id.QCM_radioGroup);

        // Récupération de l'utilisateur
        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));

        btnRetour.setOnClickListener(v -> {
            finish();
        });

        /**
         * Lorsque l'utilisateur clique sur le bouton valider, on récupère la réponse sélectionnée
         * et on lance l'activité QCMReponsesActivity
         * @see QCMReponsesActivity
         */
        btnValider.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            if (radioButton == null) {
                Toast.makeText(QCMActivity.this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(QCMActivity.this, QCMReponsesActivity.class);
                intent.putExtra(String.valueOf(QCMReponsesActivity.USER), user);
                intent.putExtra("type", radioButton.getText().toString());
                startActivity(intent);
            }
        });
    }
}