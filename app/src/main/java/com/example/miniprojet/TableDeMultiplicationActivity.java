package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.dataBase.Users;

public class TableDeMultiplicationActivity extends AppCompatActivity {
    public static Users USER;

    NumberPicker nbPicker;
    Button btnValider, btnRetour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // On charge le XML pour créer l'arbre graphique
        setContentView(R.layout.activity_table_de_multiplication);

        nbPicker = findViewById(R.id.Table_mult_nbPicker);
        nbPicker.setMaxValue(9);
        nbPicker.setMinValue(1);
        btnValider = findViewById(R.id.Table_mult_btnTable);
        btnRetour = findViewById(R.id.Table_mult_button_retour);

        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));
        Log.d("TableDeMultiplicationActivity", "TableDeMult: " + user);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });


        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TableDeMultiplicationActivity.this, TableDeMultiplicationReponsesActivity.class);
                intent.putExtra(TableDeMultiplicationReponsesActivity.TABLE_KEY, nbPicker.getValue());
                intent.putExtra(String.valueOf(TableDeMultiplicationReponsesActivity.USER), user);
                startActivity(intent);
            }
        });
    }
}
