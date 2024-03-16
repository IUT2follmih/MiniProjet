package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreationDeCompteActivity extends AppCompatActivity {
// TODO : faire en sorte que quand le clavier monte les champs aussi
    Button btnRetour, btnOk;
    EditText login, password;
    TextView textError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_de_compte);

        btnRetour = (Button) findViewById(R.id.Creation_button_retour);
        btnOk = (Button) findViewById(R.id.Creation_button_ok);

        login = (EditText) findViewById(R.id.Creation_input_login);
        password = (EditText) findViewById(R.id.Creation_input_password);

        textError = (TextView) findViewById(R.id.Creation_text_error);

        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (infoOk()){
                        // TODO : faire que ca enregistre dans la bd et ouvre la main page
                        Intent intent = new Intent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        finish();
                    }
                }
            });
    }

    public boolean infoOk(){
        if (TextUtils.isEmpty(login.getText()) || TextUtils.isEmpty(password.getText())){
            textError.setText("L'identifiant ou le mot de passe est incorect !");
            return false;
        } else {
            return true;
        }
    }
}