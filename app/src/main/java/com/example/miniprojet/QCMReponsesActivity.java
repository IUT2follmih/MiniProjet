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
import com.example.miniprojet.dataBase.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Activité permettant de répondre à un QCM
 * On récupère le type de QCM à afficher
 * On récupère les questions correspondantes
 * On affiche les questions et les réponses possibles
 */
public class QCMReponsesActivity extends AppCompatActivity {
    // Constantes
    public static Users USER;
    String type;
    int numQuestion = 1;
    int nberror = 0;
    boolean isTerminated = false;
    List<Questions> questions;

    // Composants graphiques
    TextView progression, question, error;
    RadioGroup radioGroup;
    Button suivant;

    // Base de données
    DataBaseClient maBase;

    /**
     * Méthode appelée à la création de l'activité
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qcmreponses);

        // Recupération des composants graphiques
        progression = findViewById(R.id.QCM_reponses_progression);
        question = findViewById(R.id.QCM_reponses_question);
        radioGroup = findViewById(R.id.QCM_reponses_radioGroup);
        suivant = findViewById(R.id.QCM_reponses_btn_suivant);
        error = findViewById(R.id.QCM_reponses_error);

        // On récupère le type de QCM et la base de données
        type = getIntent().getStringExtra("type");
        maBase = DataBaseClient.getInstance(getApplicationContext());

        // On récupère l'utilisateur
        Users user = (Users) getIntent().getSerializableExtra(String.valueOf(USER));

        // On récupère les questions
        if (getNumType() == 0) {
            Toast.makeText(this, "Erreur lors de la récupération des questions", Toast.LENGTH_SHORT).show();
            finish();
        }
        getQuestions();

        /**
         * Action lors du clic sur le bouton suivant
         * Si le QCM est terminé, on affiche le résultat
         * Sinon, on vérifie si la réponse est juste
         * Si oui, on passe à la question suivante
         * Sinon, on incrémente le nombre d'erreurs et on passe à la question suivante
         * On affiche la question suivante
         */
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTerminated) {
                    // On termine le QCM
                    Intent intent = new Intent(QCMReponsesActivity.this, ResultatActivity.class);
                    intent.putExtra(String.valueOf(ResultatActivity.USER), user);
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

    /**
     * Méthode permettant de récupérer le type de QCM
     *
     * @return le type de QCM
     */
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

    /**
     * Méthode permettant d'afficher la question
     * On affiche le numéro de la question
     * On affiche la question
     * On affiche les réponses possibles
     * Si c'est la dernière question, on change le texte du bouton suivant
     * On mélange les réponses
     * On affiche les réponses
     */
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

    /**
     * Méthode permettant de mélanger les réponses
     * On ajoute les réponses possibles à une liste
     * On mélange la liste
     * @return la liste mélangée
     */
    public ArrayList<String> shuffleQuestions() {
        ArrayList<String> shuffledQuestions = new ArrayList<>();
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseFausse1());
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseFausse2());
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseFausse3());
        shuffledQuestions.add(questions.get(numQuestion - 1).getReponseJuste());
        Collections.shuffle(shuffledQuestions);
        return shuffledQuestions;
    }

    /**
     * Méthode permettant de récupérer les questions
     * On récupère les questions de la base de données
     * On affiche les questions
     */
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

        // On exécute la tâche asynchrone
        GetQuestions gq = new GetQuestions();
        gq.execute();
    }
}