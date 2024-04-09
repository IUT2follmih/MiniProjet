package com.example.miniprojet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.dataBase.Users;
import com.example.miniprojet.tableAddition.Addition;
import com.example.miniprojet.tableAddition.TableAddition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Activité permettant de répondre à la table d'addition
 */
public class TableAdditionReponsesActivity extends AppCompatActivity implements Serializable {
    // Variable permettant de récupérer l'utilisateur
    public static Users USER;

    // Composants graphiques
    LinearLayout linear;
    Button valider, retour;
    TextView calcul, timer;
    EditText resultat;
    RelativeLayout layout;

    /**
     * Méthode appelée à la création de l'activité
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_addition_reponses);

        // Récupération des composants graphiques
        linear = findViewById(R.id.Table_add_res_layout);
        valider = findViewById(R.id.Table_add_res_btn);
        timer = findViewById(R.id.Table_add_timer);
        retour = findViewById(R.id.Table_add_retour_btn);

        layout = findViewById(R.id.Table_add_layout);

        // Liste des résultats et initialisation de la table d'addition
        ArrayList<EditText> resList = new ArrayList<>();

        TableAddition tableAdd = new TableAddition();

        // Récupération de l'utilisateur
        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));

        // Création des calculs et des champs de réponses pour chaque addition de la table
        for (Addition add : tableAdd.getAdditions()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            calcul = linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(add.getA() + "+" + add.getB() + "=");

            resultat = linearTMP.findViewById(R.id.template_resultat);

            resList.add(resultat);
            linear.addView(linearTMP);
        }

        // Ajout d'un timer de 60 secondes pour répondre à la table
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Temps restant: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                Toast.makeText(TableAdditionReponsesActivity.this, "Temps écoulé", Toast.LENGTH_SHORT).show();
                valider.callOnClick();
            }
        }.start();

        /**
         * Action lors du clic sur le bouton valider
         * Vérification des réponses et affichage du nombre d'erreurs
         * Redirection vers l'activité de résultat
         * @see ResultatActivity
         */
        valider.setOnClickListener(view -> {
            for (int i = 0; i < 9; i++) {
                if (resList.get(i).getText().toString().isEmpty()) {
                    tableAdd.getAdditions().get(i).setRES(0);
                } else {
                    tableAdd.getAdditions().get(i).setRES(Integer.parseInt(resList.get(i).getText().toString()));
                }
            }
            Integer nbErr = tableAdd.getNbErreurs();
            Intent intent = new Intent(TableAdditionReponsesActivity.this, ResultatActivity.class);
            intent.putExtra(String.valueOf(ResultatActivity.USER), user);
            intent.putExtra(ResultatActivity.NOM_EXO, "Table d'addition");
            intent.putExtra(ResultatActivity.NB_ERROR, nbErr);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        retour.setOnClickListener(view -> {
            finish();
        });

        /**
         * Action lors du clic sur le layout
         * Permet de fermer le clavier
         */
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }
}