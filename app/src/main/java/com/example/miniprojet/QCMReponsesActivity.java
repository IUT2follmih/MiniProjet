package com.example.miniprojet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

    String type;
    int numQuestion = 1;
    int nberror = 0;
    boolean isTerminated = false;

    private ArrayList<String> shuffledQuestions;

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

        type = getIntent().getStringExtra("type");
        Log.d("QCMReponsesActivity", "Type : " + type);
        maBase = DataBaseClient.getInstance(getApplicationContext());
        Log.d("QCMReponsesActivity", "Base de données : " + maBase);

        // On récupère les questions
        if (getNumType() == 0) {
            Toast.makeText(this, "Erreur lors de la récupération des questions", Toast.LENGTH_SHORT).show();
            finish();
        }
        getQuestions();
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
                        if (radioButton.getText().equals(questions.get(numQuestion - 1).getReponseJuste())) {
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

    // TODO : FIX
    private int getNumType() {
        switch (type) {
            case "Français":
                return 1;
            case "Histoire":
                return 2;
            case "Géographie":
                return 3;
            default:
                return 0;
        }
    }

    private void displayQuestion() {
        progression.setText("Question " + (numQuestion) + "/10");
        error.setVisibility(View.GONE);
        if (numQuestion == 10) {
            suivant.setText("Terminer");
            isTerminated = true;
        }
        Questions q = questions.get(numQuestion - 1);
        question.setText(q.getQuestion());
        radioGroup.removeAllViews();
        shuffleQuestions();
        for (String reponse : shuffleQuestions()) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(reponse);
            radioGroup.addView(radioButton);
        }
    }

    public ArrayList<String> shuffleQuestions() {
        ArrayList<String> shuffledQuestions = new ArrayList<>();
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseFausse1());
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseFausse2());
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseFausse3());
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseJuste());
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;
    }

    public void getQuestions() {
        class GetQuestions extends AsyncTask<Void, Void, List<Questions>> {

            @Override
            protected List<Questions> doInBackground(Void... voids) {
                List<Questions> questionsList = maBase.getAppDatabase()
                        .questionsDAO()
                        .getRandomQuestions(getNumType(), 10);
                return questionsList;
            }

            @Override
            protected void onPostExecute(List<Questions> questionsList) {
                super.onPostExecute(questionsList);
                questions = questionsList;
                displayQuestion();
            }
        }

        GetQuestions gq = new GetQuestions();
        gq.execute();
    }
}