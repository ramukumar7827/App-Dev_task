package com.example.ramutrivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TriviaAPI extends AppCompatActivity {
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private TriviaApiManager triviaApiManager;


    TextView takequestion;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;

    RadioButton option4;

    Button Button1;
    private RadioGroup answersRadioGroup;

    public TriviaAPI(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_api);
        triviaApiManager = new TriviaApiManager();
        option1 = findViewById(R.id.option1RadioButton);
        option2 = findViewById(R.id.option2RadioButton);
        option3 = findViewById(R.id.option3RadioButton);
        option4= findViewById(R.id.option4RadioButton);



        takequestion = findViewById(R.id.questionTextView);
        Button1 = findViewById(R.id.nextbutton);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);


        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });

        fetchQuestions();
    }



    private void fetchQuestions(){
        int amount = 10;
        String category = "21";
        String difficulty = "medium";

        triviaApiManager.getQuestions(amount, category, difficulty, new TriviaApiManager.TriviaApiCallback() {
            @Override
            public void onQuestionsReceived(List<Question> questions) {
                for (Question question : questions) {
                    String questionText = question.getQuestionText();
                    String correctAnswer = question.getCorrectAnswer();
                    List<String> incorrectAnswers = question.getIncorrectAnswers();


                }
            }

            @Override
            public void onApiError(String error) {

                Toast.makeText(TriviaAPI.this, "API Error: " + error, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void showNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            takequestion.setText(currentQuestion.getQuestionText());

            List<String> options = currentQuestion.getIncorrectAnswers();
            options.add(currentQuestion.getCorrectAnswer());


            answersRadioGroup.removeAllViews();
            for (String option : options) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(option);
                answersRadioGroup.addView(radioButton);
            }

            currentQuestionIndex++;
        } else {

            Toast.makeText(this, "End of questions", Toast.LENGTH_SHORT).show();
        }
    }
}






















