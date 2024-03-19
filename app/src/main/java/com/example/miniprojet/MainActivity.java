package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniprojet.dataBase.DataBaseClient;

public class MainActivity extends AppCompatActivity {

    private DataBaseClient maBase;

    Button btnAno;
    Button btnCrea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maBase = DataBaseClient.getInstance(getApplicationContext());
        setContentView(R.layout.activity_main);

        btnAno = (Button) findViewById(R.id.Main_btn_Ano);
        btnCrea = (Button) findViewById(R.id.Main_btn_crea);

        // TODO : faire la liste des comptes
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
}