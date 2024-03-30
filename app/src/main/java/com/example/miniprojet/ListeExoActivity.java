package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniprojet.dataBase.Users;

public class ListeExoActivity extends AppCompatActivity {

    Button multi, add, qcm, retour;
    TextView userName;
    public static boolean ANONYME = false;
    public static Users USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_exo);

        multi = (Button) findViewById(R.id.List_btn_multiplication);
        add = (Button) findViewById(R.id.List_btn_addition);
        qcm = (Button) findViewById(R.id.List_btn_qcm);
        retour = (Button) findViewById(R.id.List_btn_retour);

        userName = (TextView) findViewById(R.id.Liste_text_user);
        Users user;

        boolean isAno = getIntent().getBooleanExtra(String.valueOf(ANONYME), false);
        if (isAno) {
            userName.setText("Anonyme");
        } else {
            user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));
            userName.setText("Bonjour " + user.getNom() + " " + user.getPrenom() + " !");
        }

        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, TableDeMultiplicationActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, TableAdditionReponsesActivity.class);
                startActivity(intent);
            }
        });

        qcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}