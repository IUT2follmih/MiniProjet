package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    public static Users USER;

    Button multi, add, qcm, retour;
    TextView userName;
    public static boolean ANONYME = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_exo);

        multi = findViewById(R.id.List_btn_multiplication);
        add = findViewById(R.id.List_btn_addition);
        qcm = findViewById(R.id.List_btn_qcm);
        retour = findViewById(R.id.List_btn_retour);

        userName = findViewById(R.id.Liste_text_user);
        Users user = null;

        boolean isAno = getIntent().getBooleanExtra(String.valueOf(ANONYME), false);
        if (isAno) {
            userName.setText("Anonyme");
        } else {
            user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));
            userName.setText("Bonjour " + user.getNom() + " " + user.getPrenom() + " !");
        }

        Users finalUser = user;
        multi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, TableDeMultiplicationActivity.class);
                if (!isAno) {
                    intent.putExtra(String.valueOf(TableDeMultiplicationActivity.USER), finalUser);
                }
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, TableAdditionReponsesActivity.class);
                if (!isAno) {
                    intent.putExtra(String.valueOf(TableAdditionReponsesActivity.USER), finalUser);
                }
                startActivity(intent);
            }
        });

        qcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListeExoActivity.this, QCMActivity.class);
                if (!isAno) {
                    intent.putExtra(String.valueOf(QCMActivity.USER), finalUser);
                }
                startActivity(intent);
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USER = null;
                finish();
            }
        });
    }
}