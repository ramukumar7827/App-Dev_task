package com.example.ramutrivia;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    TextView totalQuestionTextView;
    TextView questionTextView;
    Button ans1, ans2, ans3, ans4;
    Button submit1;
    int score = 0;
    int totalQuestion = queans.question.length;
    int currentQuestionIndex = 0;
    String selectedAns = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionTextView = findViewById(R.id.ettext1);
        questionTextView = findViewById(R.id.ettext2);
        ans1 = findViewById(R.id.ans_A);
        ans2 = findViewById(R.id.ans_B);
        ans3 = findViewById(R.id.ans_C);
        ans4 = findViewById(R.id.ans_D);
        submit1 = findViewById(R.id.Submit_btn);


        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        ans4.setOnClickListener(this);
        submit1.setOnClickListener(this);

        totalQuestionTextView.setText("Total Question =" + totalQuestion);
        loadNewQuestion();

    }

    @Override
    public void onClick(View view) {
        ans4.setBackgroundColor(Color.WHITE);
        ans3.setBackgroundColor(Color.WHITE);
        ans2.setBackgroundColor(Color.WHITE);
        ans1.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.Submit_btn) {

            if (selectedAns.equals(queans.coorectans[currentQuestionIndex])) {

                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        } else {
            selectedAns = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }


    }

    void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;

        }
        questionTextView.setText(queans.question[currentQuestionIndex]);
        ans1.setText(queans.choice[currentQuestionIndex][0]);
        ans2.setText(queans.choice[currentQuestionIndex][1]);
        ans3.setText(queans.choice[currentQuestionIndex][2]);
        ans4.setText(queans.choice[currentQuestionIndex][3]);


    }

    void finishQuiz() {
        String passstaus = "";
        if (score > totalQuestion * 0.60) {
        } else {
            passstaus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passstaus)
                .setMessage("Score is " +  score + " out of " + totalQuestion+ ".")
                .setPositiveButton("Restart",(dialogInterface,i)-> restartQuiz())
                .setCancelable(false)
                .show();


    }
    void restartQuiz(){
        score=0;
        currentQuestionIndex=0;
        loadNewQuestion();
    }



}




