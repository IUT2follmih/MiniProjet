package com.example.miniprojet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniprojet.dataBase.DataBaseClient;
import com.example.miniprojet.dataBase.Users;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DataBaseClient maBase;
    private UserAdaptater adaptater;
    Button btnAno;
    Button btnCrea;
    ListView userList;
    TextView txtListVide;

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

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Users user = adaptater.getItem(position);

                Toast.makeText(MainActivity.this, "Click : " + user.getPrenom(), Toast.LENGTH_SHORT).show();

            }
        });

        userList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // Récupération de la tâche cliquée à l'aide de l'adapter
                Users user = adaptater.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Suppression");
                builder.setIcon(R.drawable.baseline_info_24);
                builder.setMessage("Voulez-vous vraiment supprimer " + user.getNom() + " ?");
                builder.setPositiveButton("Supprimer", (dialog, which) -> {
                    supprUser(position);
                    getUsers();
                });
                builder.setNegativeButton("Annumer", (dialog, which) -> {
                    dialog.dismiss();
                });

                builder.show();
                return false;
            }
        });

        btnAno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListeExoActivity.class);
                startActivity(intent);
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
        if (userList != null) {
            getUsers();
        } else {
            txtListVide.setVisibility(View.VISIBLE);
        }
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