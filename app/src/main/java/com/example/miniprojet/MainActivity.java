package com.example.miniprojet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.dataBase.DataBaseClient;
import com.example.miniprojet.dataBase.Users;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Activité principale de l'application
 * Qui permet de lister les utilisateurs
 * et de les supprimer
 */
public class MainActivity extends AppCompatActivity {

    // Constantes
    private DataBaseClient maBase;
    private UserAdaptater adaptater;
    boolean isVue = false;

    // Composants graphiques
    Button btnAno;
    Button btnCrea;
    ListView userList;
    TextView txtListVide;

    /**
     * Méthode appelée à la création de l'activité
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de la base de données
        maBase = DataBaseClient.getInstance(getApplicationContext());

        // Récupération des composants graphiques
        btnAno = findViewById(R.id.Main_btn_Ano);
        btnCrea = findViewById(R.id.Main_btn_crea);
        txtListVide = findViewById(R.id.Main_text_list_vide);

        userList = findViewById(R.id.Main_list_users);

        // Création de l'adapter et association à la liste
        adaptater = new UserAdaptater(this, new ArrayList<Users>());
        userList.setAdapter(adaptater);

        // Gestion de la vue vide
        userList.setEmptyView(txtListVide);

        /**
         * Gestion des événements sur la liste des utilisateurs
         * - clic simple pour sélectionner un utilisateur
         * - clic long pour supprimer un utilisateur
         */
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users user = adaptater.getItem(position);
                giveUser(user, false);
            }
        });

        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // Récupération de la tâche cliquée à l'aide de l'adapter
                Users user = adaptater.getItem(position);

                // Affichage d'une boîte de dialogue pour confirmer la suppression
                new MaterialAlertDialogBuilder(MainActivity.this)
                        .setTitle("Suppression")
                        .setIcon(R.drawable.baseline_info_24)
                        .setMessage("Voulez-vous vraiment supprimer " + user.getNom() + " ?")
                        .setPositiveButton("Supprimer", (dialog, which) -> {
                            supprUser(position);
                            getUsers();
                        })
                        .setNegativeButton("Annuler", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .setCancelable(true)
                        .show();
                return true;
            }
        });

        // Gestion de l'événement sur le bouton "Anonyme"
        btnAno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si l'utilisateur n'a pas encore été invité à choisir entre un compte anonyme et un compte existant
                if (!isVue) {
                    // Affichage d'une boîte de dialogue pour confirmer le choix
                    new MaterialAlertDialogBuilder(MainActivity.this)
                            .setTitle("Voulez vous continuer en anonyme ?")
                            .setIcon(R.drawable.baseline_warning_24)
                            .setMessage("Les resultats ne seront pas sauvegardés !")
                            .setPositiveButton("Continuer", (dialog, which) -> {
                                giveUser(null, true);
                                isVue = true;
                            })
                            .setNeutralButton("Créer un compte", (dialog, which) -> {
                                Intent intent = new Intent(MainActivity.this, CreationDeCompteActivity.class);
                                startActivity(intent);
                            })
                            .setCancelable(true)
                            .show();
                } else {
                    giveUser(null, true);
                }

            }
        });

        // Gestion de l'événement sur le bouton "Créer un compte"
        btnCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreationDeCompteActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Méthode permettant de lancer l'activité ListeExoActivity
     * en passant en paramètre l'utilisateur sélectionné
     * @param user
     * @param anonyme
     */
    public void giveUser(Users user, boolean anonyme) {
        Intent intent = new Intent(MainActivity.this, ListeExoActivity.class);
        intent.putExtra(String.valueOf(ListeExoActivity.ANONYME), anonyme);
        if (!anonyme) {
            intent.putExtra(String.valueOf(ListeExoActivity.USER), user);
        }
        startActivity(intent);
    }

    /**
     * Méthode permettant de récupérer la liste des utilisateurs
     * et de les afficher dans la liste
     */
    private void getUsers() {
        class GetUsers extends AsyncTask<Void, Void, List<Users>> {

            @Override
            protected List<Users> doInBackground(Void... voids) {
                List<Users> usersList = maBase.getAppDatabase()
                        .usersDao()
                        .getALl();
                return usersList;
            }

            @Override
            protected void onPostExecute(List<Users> users) {
                super.onPostExecute(users);

                // Mettre à jour l'adapter avec la liste de taches
                adaptater.clear();
                adaptater.addAll(users);

                // Notifier l'adapter du changement
                adaptater.notifyDataSetChanged();
            }
        }

        // Exécution de la tâche asynchrone
        GetUsers gu = new GetUsers();
        gu.execute();
    }

    /**
     * Méthode appelée à l'affichage de l'activité
     */
    @Override
    protected void onStart() {
        super.onStart();
        getUsers();
        DataBaseClient.getInstance(this).creatioInitQuestions();
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK) {
//
//            // Mise à jour des taches
//            getTasks();
//        }
//    }
//

    /**
     * Méthode permettant de supprimer un utilisateur de la base de données
     * @param position position de l'utilisateur dans la liste
     */
    private void supprUser(int position) {
        // Récupération de l'utilisateur à supprimer
        final Users user = adaptater.getItem(position);
        class SupprUser extends AsyncTask<Void, Void, Users> {
            @Override
            protected Users doInBackground(Void... voids) {
                maBase.getAppDatabase()
                        .usersDao()
                        .delete(user);
                return user;
            }

            @Override
            protected void onPostExecute(Users user) {
                super.onPostExecute(user);
                Toast.makeText(getApplicationContext(), "Compte " + user.getNom() + " supprimé", Toast.LENGTH_LONG).show();
            }
        }

        // Exécution de la tâche asynchrone
        SupprUser spu = new SupprUser();
        spu.execute();
    }
}