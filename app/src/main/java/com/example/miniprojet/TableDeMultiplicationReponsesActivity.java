package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojet.tableMultiplication.Multiplication;
import com.example.miniprojet.tableMultiplication.TableDeMultiplication;

import java.util.ArrayList;

public class TableDeMultiplicationReponsesActivity extends AppCompatActivity {
    public static String TABLE_KEY = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_de_multiplication_reponses);

        LinearLayout linear = findViewById(R.id.Table_mult_res_layout);
        Button valider = findViewById(R.id.Table_mult_res_btn);

        ArrayList<EditText> resList = new ArrayList<>();

        int key = getIntent().getIntExtra(TABLE_KEY, 1);
        //Toast.makeText(TableMultiplicationActivity.this,TABLE_KEY, Toast.LENGTH_SHORT).show();
        TableDeMultiplication tableMult = new TableDeMultiplication(key);

        //linear.removeAllViews();


        for (Multiplication mult : tableMult.getMultiplications()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            TextView calcul = (TextView) linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(mult.getA() + "x" + mult.getB() + "=");

            EditText resultat = (EditText) linearTMP.findViewById(R.id.template_resultat);
            //resultat.setText(Integer.toString(mult.getA() * mult.getB()));
            resList.add(resultat);
            linear.addView(linearTMP);
        }

        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 9; i++) {
                    if (resList.get(i).getText().toString().isEmpty()) {
                        tableMult.getMultiplications().get(i).setRES(0);
                    } else {
                        tableMult.getMultiplications().get(i).setRES(Integer.parseInt(resList.get(i).getText().toString()));
                    }
                }
                Integer res = tableMult.getNbErreurs();
                Toast.makeText(TableDeMultiplicationReponsesActivity.this, "Bon!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TableDeMultiplicationReponsesActivity.this, Exercice5ActivityResult.class);
                intent.putExtra(Exercice5ActivityResult.NB_ERROR, res);
                startActivity(intent);
            }
        });

    }
}