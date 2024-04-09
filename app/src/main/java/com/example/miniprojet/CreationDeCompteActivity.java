package com.example.miniprojet;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.dataBase.DataBaseClient;
import com.example.miniprojet.dataBase.Users;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Activité permettant de créer un compte
 * L'utilisateur doit saisir son nom et son prénom
 */
public class CreationDeCompteActivity extends AppCompatActivity {

    // Base de données
    private DataBaseClient maBase;

    // Composants graphiques
    Button btnRetour, btnOk;
    TextInputLayout nom, prenom;
    ScrollView layout;

    /**
     * Méthode appelée à la création de l'activité
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_de_compte);

        // Initialisation de la base de données
        maBase = DataBaseClient.getInstance(getApplicationContext());

        // Récupération des composants graphiques
        btnRetour = findViewById(R.id.Creation_button_retour);
        btnOk = findViewById(R.id.Creation_button_ok);

        nom = findViewById(R.id.Creation_input_nom);
        prenom = findViewById(R.id.Creation_input_prenom);
        layout = findViewById(R.id.Creation_layout);

        /**
         * Gestion des événements sur le bouton retour
         */
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        /**
         * Gestion des événements sur le bouton ok
         */
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });

        /**
         * Gestion des événements sur le layout
         * Permet de cacher le clavier virtuel
         * lorsqu'on clique en dehors des champs de saisie
         */
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        // Focus sur le champ nom au démarrage de l'activité
        nom.getEditText().requestFocus();
    }

    /**
     * Méthode permettant de sauvegarder un utilisateur dans la base de données
     * On utilise une tâche asynchrone pour ne pas bloquer l'interface graphique
     */
    private void saveUser() {
        final String sNom = nom.getEditText().getText().toString().trim();
        final String sPrenom = prenom.getEditText().getText().toString().trim();

        if (sNom.isEmpty()) {
            nom.setError("Le nom ne peut être vide !");
            nom.requestFocus();
            return;
        }

        if (sPrenom.isEmpty()) {
            prenom.setError("Le prenom ne peut etre vide !");
            prenom.requestFocus();
            return;
        }

        /**
         * Tâche asynchrone permettant de sauvegarder un utilisateur
         */
        class SaveUser extends AsyncTask<Void, Void, Users> {
            /**
             * Méthode appelée avant l'exécution de la tâche asynchrone
             */
            @Override
            protected Users doInBackground(Void... voids) {
                Users user = new Users();
                user.setNom(sNom);
                user.setPrenom(sPrenom);

                // adding to database
                long id = maBase.getAppDatabase()
                        .usersDao()
                        .insert(user);

                // mettre à jour l'id du user
                user.setId(id);
                return user;
            }

            /**
             * Méthode appelée après l'exécution de la tâche asynchrone
             * @param user The result of the operation computed by {@link #doInBackground}.
             */
            @Override
            protected void onPostExecute(Users user) {
                super.onPostExecute(user);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Compte ajouté", Toast.LENGTH_LONG).show();
            }
        }

        // Exécution de la tâche asynchrone
        SaveUser su = new SaveUser();
        su.execute();
    }
}