package com.example.miniprojet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultatActivity extends AppCompatActivity {

    public static String NOM_EXO = "NOMEXO";
    public static String NB_ERROR = "0";
    public static String TABLE_KEY = "1";
    Button btnReExo, btnReUser, btnRecomecer;
    TextView title, name, nbOk, nbError;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        btnReExo = (Button) findViewById(R.id.Result_btn_retour_exo);
        btnReUser = (Button) findViewById(R.id.Result_btn_retour_comptes);
        btnRecomecer = (Button) findViewById(R.id.Result_btn_recommencer);

        title = (TextView) findViewById(R.id.Result_text_title);
        name = (TextView) findViewById(R.id.Result_text_name);
        nbOk = (TextView) findViewById(R.id.Result_text_nbOk);
        nbError = (TextView) findViewById(R.id.Result_text_nbError);

        String nomExo = getIntent().getStringExtra(NOM_EXO);
        Integer errors = getIntent().getIntExtra(NB_ERROR, 0);
        Integer juste = 10 - errors;

        if (errors > 0) {
            title.setText("ERREURS !");
            title.setTextColor(Color.RED);
            btnRecomecer.setVisibility(View.VISIBLE);
        } else {
            title.setText("FELICITATION !");
            title.setTextColor(Color.GREEN);
        }

        name.setText(nomExo);
        nbOk.setText("Nombre de bonnes réponses : " + juste);
        nbError.setText("Nombre d'erreurs : " + errors);

        btnRecomecer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultatActivity.this, TableDeMultiplicationReponsesActivity.class);
                intent.putExtra(TableDeMultiplicationReponsesActivity.TABLE_KEY, getIntent().getIntExtra(TABLE_KEY, 1));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
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