package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojet.dataBase.Users;
import com.example.miniprojet.tableMultiplication.Multiplication;
import com.example.miniprojet.tableMultiplication.TableDeMultiplication;

import java.util.ArrayList;

/**
 * Activité permettant de répondre à une table de multiplication
 */
public class TableDeMultiplicationReponsesActivity extends AppCompatActivity {
    // Constantes
    public static Users USER;
    public static String TABLE_KEY = "1";

    // Composants graphiques
    LinearLayout linear;
    Button valider, retour;
    TextView calcul, timer;
    EditText resultat;
    RelativeLayout layout;

    /**
     * Méthode appelée à la création de l'activité
     * @param savedInstanceState état de l'activité sauvegardé
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_de_multiplication_reponses);

        // Récupération des composants graphiques
        linear = findViewById(R.id.Table_mult_res_layout);
        valider = findViewById(R.id.Table_mult_res_btn);
        retour = findViewById(R.id.Table_mult_retour_btn);
        timer = findViewById(R.id.Table_mult_timer);

        layout = findViewById(R.id.Table_mult_layout);

        // Initialisation de la liste des résultats
        ArrayList<EditText> resList = new ArrayList<>();

        // Récupération de la table de multiplication
        int key = getIntent().getIntExtra(TABLE_KEY, 1);
        TableDeMultiplication tableMult = new TableDeMultiplication(key);

        // Clear de la vue
        linear.removeAllViews();

        // Récupération de l'utilisateur
        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));

        /**
         * Création des champs de réponse pour chaque multiplication de la table de multiplication
         */
        for (Multiplication mult : tableMult.getMultiplications()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            calcul = linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(mult.getA() + "x" + mult.getB() + "=");

            resultat = linearTMP.findViewById(R.id.template_resultat);
            resList.add(resultat);
            linear.addView(linearTMP);
        }

        /**
         * Ajout d'un timer de 60 secondes
         * Si le timer arrive à 0, on valide les réponses
         * Sinon, on valide les réponses manuellement
         * On affiche un toast pour indiquer que le temps est écoulé
         */
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Temps restant: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                Toast.makeText(TableDeMultiplicationReponsesActivity.this, "Temps écoulé", Toast.LENGTH_SHORT).show();
                valider.callOnClick();
            }
        }.start();

        /**
         * Action lors du clic sur le bouton valider
         * On récupère les réponses de l'utilisateur
         * On les compare avec les réponses attendues
         * On affiche le nombre d'erreurs
         * On affiche l'activité de résultat
         */
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 9; i++) {
                    if (resList.get(i).getText().toString().isEmpty()) {
                        tableMult.getMultiplications().get(i).setRES(0);
                    } else {
                        tableMult.getMultiplications().get(i).setRES(Integer.parseInt(resList.get(i).getText().toString()));
                    }
                }
                Integer nbErr = tableMult.getNbErreurs();
                Intent intent = new Intent(TableDeMultiplicationReponsesActivity.this, ResultatActivity.class);
                intent.putExtra(String.valueOf(ResultatActivity.USER), user);
                intent.putExtra(ResultatActivity.NOM_EXO, "Table de multiplication");
                intent.putExtra(ResultatActivity.NB_ERROR, nbErr);
                intent.putExtra(ResultatActivity.TABLE_KEY, key);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        /**
         * Action lors du clic sur le layout
         * On cache le clavier
         */
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        retour.setOnClickListener(view -> {
            finish();
        });

    }
}