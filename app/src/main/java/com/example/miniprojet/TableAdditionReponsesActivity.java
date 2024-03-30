package com.example.miniprojet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojet.tableAddition.Addition;
import com.example.miniprojet.tableAddition.TableAddition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class TableAdditionReponsesActivity extends AppCompatActivity implements Serializable {

    LinearLayout linear;
    Button valider;
    TextView calcul;
    EditText resultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_addition_reponses);

        linear = findViewById(R.id.Table_add_res_layout);
        valider = findViewById(R.id.Table_add_res_btn);

        ArrayList<EditText> resList = new ArrayList<>();

        TableAddition tableAdd = new TableAddition();

        for (Addition add : tableAdd.getAdditions()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(add.getA() + "+" + add.getB() + "=");

            resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);

            resList.add(resultat);
            linear.addView(linearTMP);
        }


        valider.setOnClickListener(view -> {
            for (int i = 0; i < 9; i++) {
                if (resList.get(i).getText().toString().isEmpty()) {
                    tableAdd.getAdditions().get(i).setRES(0);
                } else {
                    tableAdd.getAdditions().get(i).setRES(Integer.parseInt(resList.get(i).getText().toString()));
                }
            }
            Integer nbErr = tableAdd.getNbErreurs();
            Intent intent = new Intent(TableAdditionReponsesActivity.this, ResultatActivity.class);
            intent.putExtra(ResultatActivity.NOM_EXO, "Table d'addition");
            intent.putExtra(ResultatActivity.NB_ERROR, nbErr);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}