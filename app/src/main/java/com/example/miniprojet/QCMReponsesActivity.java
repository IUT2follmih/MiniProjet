package com.example.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.miniprojet.dataBase.DataBaseClient;
import com.example.miniprojet.dataBase.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QCMReponsesActivity extends AppCompatActivity {
    TextView progression, question, error;
    RadioGroup radioGroup;
    Button suivant;
    List<Questions> questions;

    DataBaseClient maBase;

    int type;
    int numQuestion = 0;
    int nberror = 0;

    boolean isTerminated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcmreponses);

        // On récupère les éléments de la vue
        progression = findViewById(R.id.QCM_reponses_progression);
        question = findViewById(R.id.QCM_reponses_question);
        radioGroup = findViewById(R.id.QCM_reponses_radioGroup);
        suivant = findViewById(R.id.QCM_reponses_btn_suivant);
        error = findViewById(R.id.QCM_reponses_error);

        type = getIntent().getIntExtra("type", 4);

        // On récupère les questions
        if (type > 3) {
            Toast.makeText(this, "Erreur lors de la récupération des questions", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            questions = maBase.getAppDatabase().questionsDAO().getRandomQuestions(type, 10);
        }

        // On affiche la première question
        displayQuestion();

        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTerminated) {
                    // On termine le QCM
                    Intent intent = new Intent(QCMReponsesActivity.this, ResultatActivity.class);
                    intent.putExtra(ResultatActivity.NOM_EXO, "QCM");
                    intent.putExtra(ResultatActivity.NB_ERROR, nberror);
                    intent.putExtra("type", type);
                    startActivity(intent);
                } else {
                    // On vérifie si la réponse est juste
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radioButton = findViewById(selectedId);
                    if (radioButton == null) {
                        error.setVisibility(View.VISIBLE);
                    } else {
                        if (radioButton.getText().equals(questions.get(numQuestion).getReponseJuste())) {
                            numQuestion++;
                            displayQuestion();
                        } else {
                            nberror++;
                            numQuestion++;
                            displayQuestion();
                        }
                    }
                }
            }
        });
    }

    private void displayQuestion() {
        progression.setText("Question " + (numQuestion + 1) + "/10");
        Questions q = questions.get(numQuestion);
        question.setText(q.getQuestion());
        radioGroup.removeAllViews();
        shuffleQuestions();
        for (String reponse : shuffleQuestions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(reponse);
            radioGroup.addView(radioButton);
        }

        if (numQuestion == 9) {
            suivant.setText("Terminer");
            isTerminated = true;
        }
    }

    public ArrayList<String> shuffleQuestions() {
        ArrayList<String> shuffledQuestions = new ArrayList<>();
        shuffledQuestions.add(questions.get(numQuestion).getReponseFausse1());
        shuffledQuestions.add(questions.get(numQuestion).getReponseFausse2());
        shuffledQuestions.add(questions.get(numQuestion).getReponseFausse3());
        shuffledQuestions.add(questions.get(numQuestion).getReponseJuste());
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;
    }
}