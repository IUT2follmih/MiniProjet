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

public class QCMActivity extends AppCompatActivity {

    Button btnRetour, btnValider;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcmactivity);

        // On récupère les éléments de la vue
        btnRetour = findViewById(R.id.QCM_btn_retour);
        btnValider = findViewById(R.id.QCM_btn_valider);
        radioGroup = findViewById(R.id.QCM_radioGroup);

        btnRetour.setOnClickListener(v -> {
            finish();
        });

        btnValider.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            if (radioButton == null) {
                Toast.makeText(QCMActivity.this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(QCMActivity.this, QCMReponsesActivity.class);
                intent.putExtra("type", radioButton.getText().toString());
                startActivity(intent);
            }
        });
    }
}