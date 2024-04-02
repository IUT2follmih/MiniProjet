package com.example.miniprojet;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class ResultatActivity extends AppCompatActivity implements Serializable {

    public static String NOM_EXO = "NOMEXO";
    public static String NB_ERROR = "0";
    public static String TABLE_KEY = "1";
    Button btnReExo, btnReUser, btnRecomecer;
    TextView name, note, result;

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        btnReExo = (Button) findViewById(R.id.Result_btn_retour_exo);
        btnReUser = (Button) findViewById(R.id.Result_btn_retour_comptes);
        btnRecomecer = (Button) findViewById(R.id.Result_btn_recommencer);

        name = (TextView) findViewById(R.id.Result_text_name);
        note = (TextView) findViewById(R.id.Result_text_note);
        result = (TextView) findViewById(R.id.Result_text_result);

        String nomExo = getIntent().getStringExtra(NOM_EXO);
        Integer errors = getIntent().getIntExtra(NB_ERROR, 0);

        name.setText("Resultat de l'exercice : " + nomExo);
        note.setText((10 - errors) + "/10");
        if (errors > 3) {
            note.setTextColor(Color.RED);
            result.setText("Vous n'avez pas réussi l'exercice !");
        } else {
            note.setTextColor(Color.GREEN);
            result.setText("Vous avez réussi l'exercice !!");
        }
        btnRecomecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomExo.equals("Table de multiplication")) {
//                    if (errors > 3) {
//                        btnRecomecer.setText("Corriger mes fautes");
//                        // TODO : faire la correction de fautes
//                    } else {
//                        Intent intent = new Intent(ResultatActivity.this, TableDeMultiplicationReponsesActivity.class);
//                        intent.putExtra(TableDeMultiplicationReponsesActivity.TABLE_KEY, getIntent().getIntExtra(TABLE_KEY, 1));
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }

                    Intent intent = new Intent(ResultatActivity.this, TableDeMultiplicationReponsesActivity.class);
                    intent.putExtra(TableDeMultiplicationReponsesActivity.TABLE_KEY, getIntent().getIntExtra(TABLE_KEY, 1));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (nomExo.equals("Table d'addition")) {
//                    if (errors > 3) {
//                        btnRecomecer.setText("Corriger mes fautes");
//                        // TODO : faire la correction de fautes
//                    } else {
//                        Intent intent = new Intent(ResultatActivity.this, TableAdditionReponsesActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }

                    Intent intent = new Intent(ResultatActivity.this, TableAdditionReponsesActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else if (nomExo.equals("QCM")) {
//                    if (errors > 3) {
//                        btnRecomecer.setText("Corriger mes fautes");
//                        // TODO : faire la correction de fautes
//                    } else {
//                        Intent intent = new Intent(ResultatActivity.this, QCMReponsesActivity.class);
//                        int type = getIntent().getIntExtra("type", 4);
//                        intent.putExtra("type", type);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                    }
                    Intent intent = new Intent(ResultatActivity.this, QCMActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        btnReExo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, ListeExoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnReUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}