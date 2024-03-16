package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListeExoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_exo);

    }

    public void onExoTableDeMult(View view) {
        // TODO
    }

    public void onExoTableDadd(View view) {
        // TODO
    }

    public void onExoQCM(View view) {
        // TODO
    }

    public void btnRetour(View view) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
    }
}