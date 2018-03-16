package com.example.android.educationalapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by mohab on 8/11/2017.
 */

public class QuizScreenPart3 extends AppCompatActivity {
    static int position = 0;
    EditText user_answer;
    String[] answers = {"android package kit", "google cloud messaging"};
    String[][] questions = new String[][]{
            {"what is APK stands for?"},
            {"what is GCM stands for?"}
    };

    TextView question_txt;
    int no_of_questions = 2;
    Button next_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_part3);

        question_txt = (TextView) findViewById(R.id.question_txt);
        user_answer = (EditText) findViewById(R.id.answer_txt);
        next_btn = (Button) findViewById(R.id.next_btn);
        String question_no = questions[position][0];
        question_txt.setText(question_no);

    }

    public void next_btn(View v) {

        if (Arrays.asList(answers).contains(user_answer.getText().toString().toLowerCase())) {
            QuizScreenPart1.score += 1;
        }

        if (position < no_of_questions - 1) {
            position += 1;
            String question_no = questions[position][0];
            question_txt.setText(question_no);
            user_answer.setText("");

            if (position == no_of_questions - 1)
                next_btn.setText("Finish");

            }else {
            Toast.makeText(QuizScreenPart3.this, "Your score is " + QuizScreenPart1.score + " out of " + QuizScreenPart1.total_no_of_questions,
                    Toast.LENGTH_LONG).show();
            QuizScreenPart1.score = 0;

        }


    }

}
