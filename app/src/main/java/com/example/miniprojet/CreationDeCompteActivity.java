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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.dataBase.DataBaseClient;
import com.example.miniprojet.dataBase.Users;

public class CreationDeCompteActivity extends AppCompatActivity {
// TODO : faire en sorte que quand le clavier monte les champs aussi

    private DataBaseClient maBase;

    Button btnRetour, btnOk;
    EditText nom, prenom;

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_de_compte);

        maBase = DataBaseClient.getInstance(getApplicationContext());

        btnRetour = (Button) findViewById(R.id.Creation_button_retour);
        btnOk = (Button) findViewById(R.id.Creation_button_ok);

        nom = (EditText) findViewById(R.id.Creation_input_nom);
        prenom = (EditText) findViewById(R.id.Creation_input_prenom);
        layout = (RelativeLayout) findViewById(R.id.Creation_layout);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
            }
        });

        // If keybord up, and we touch the screen, the keybord will go down
        nom.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    nom.setHint("");
                } else {
                    nom.setHint("Nom");
                }
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
    }

    private void saveUser() {
        final String sNom = nom.getText().toString().trim();
        final String sPrenom = prenom.getText().toString().trim();


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

        class SaveUser extends AsyncTask<Void, Void, Users> {
            @Override
            protected Users doInBackground(Void... voids) {

                // creating a user
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

            @Override
            protected void onPostExecute(Users user) {
                super.onPostExecute(user);

                // Quand la tache est créée, on arrête l'activité AddTaskActivity (on l'enleve de la pile d'activités)
                setResult(RESULT_OK);
                finish();
                Toast.makeText(getApplicationContext(), "Compte ajouté", Toast.LENGTH_LONG).show();
            }
        }

        SaveUser su = new SaveUser();
        su.execute();
    }
}