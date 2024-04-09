package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojet.dataBase.Users;
import com.example.miniprojet.tableMultiplication.Multiplication;
import com.example.miniprojet.tableMultiplication.TableDeMultiplication;

import java.util.ArrayList;

public class TableDeMultiplicationReponsesActivity extends AppCompatActivity {
    public static Users USER;
    public static String TABLE_KEY = "1";

    LinearLayout linear;
    Button valider;
    TextView calcul, timer;
    EditText resultat;
    RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_de_multiplication_reponses);

        linear = findViewById(R.id.Table_mult_res_layout);
        valider = findViewById(R.id.Table_mult_res_btn);
        timer = findViewById(R.id.Table_mult_timer);

        ArrayList<EditText> resList = new ArrayList<>();

        layout = findViewById(R.id.Table_mult_layout);

        int key = getIntent().getIntExtra(TABLE_KEY, 1);
        TableDeMultiplication tableMult = new TableDeMultiplication(key);

        linear.removeAllViews();

        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));


        for (Multiplication mult : tableMult.getMultiplications()) {
            LinearLayout linearTMP = (LinearLayout) getLayoutInflater().inflate(R.layout.template_calcul, null);

            calcul = linearTMP.findViewById(R.id.template_calcul);
            calcul.setText(mult.getA() + "x" + mult.getB() + "=");

            resultat = linearTMP.findViewById(R.id.template_resultat);
            resList.add(resultat);
            linear.addView(linearTMP);
        }

        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("Temps restant: " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                Toast.makeText(TableDeMultiplicationReponsesActivity.this, "Temps écoulé", Toast.LENGTH_SHORT).show();
                valider.callOnClick();
            }
        }.start();

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
                Integer nbErr = tableMult.getNbErreurs();
                Intent intent = new Intent(TableDeMultiplicationReponsesActivity.this, ResultatActivity.class);
                intent.putExtra(String.valueOf(ResultatActivity.USER), user);
                intent.putExtra(ResultatActivity.NOM_EXO, "Table de multiplication");
                intent.putExtra(ResultatActivity.NB_ERROR, nbErr);
                intent.putExtra(ResultatActivity.TABLE_KEY, key);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

    }
}