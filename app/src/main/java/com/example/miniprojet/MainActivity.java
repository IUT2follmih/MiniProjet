package com.example.miniprojet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private DataBaseClient maBase;
    private UserAdaptater adaptater;
    Button btnAno;
    Button btnCrea;
    ListView userList;
    TextView txtListVide;
    boolean isVue = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maBase = DataBaseClient.getInstance(getApplicationContext());

        btnAno = (Button) findViewById(R.id.Main_btn_Ano);
        btnCrea = (Button) findViewById(R.id.Main_btn_crea);
        txtListVide = (TextView) findViewById(R.id.Main_text_list_vide);

        userList = (ListView) findViewById(R.id.Main_list_users);

        adaptater = new UserAdaptater(this, new ArrayList<Users>());
        userList.setAdapter(adaptater);

        userList.setEmptyView(txtListVide);

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

        btnAno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isVue) {
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

        btnCrea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreationDeCompteActivity.class);
                startActivity(intent);
            }
        });
    }

    public void giveUser(Users user, boolean anonyme) {
        Intent intent = new Intent(MainActivity.this, ListeExoActivity.class);
        intent.putExtra(String.valueOf(ListeExoActivity.ANONYME), anonyme);
        if (!anonyme) {
            intent.putExtra(String.valueOf(ListeExoActivity.USER), user);
        }
        startActivity(intent);
    }

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

                // Now, notify the adapter of the change in source
                adaptater.notifyDataSetChanged();
            }
        }

        GetUsers gu = new GetUsers();
        gu.execute();
    }

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
    private void supprUser(int position) {
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

        SupprUser spu = new SupprUser();
        spu.execute();
    }
}