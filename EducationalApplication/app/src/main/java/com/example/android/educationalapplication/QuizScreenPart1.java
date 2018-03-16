package com.example.android.educationalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

/**
 * Created by mohab on 8/11/2017.
 */

public class QuizScreenPart1 extends AppCompatActivity {
    static int position = 0;
    static int score = 0, total_no_of_questions = 8;
    String[] answers = {"marchmello", "google", "java"};
    String question;
    String[][] questions = new String[][]{
            {"Which is the latest mobile version of android?", "Marchmello", "Kitkat", "Ice cream", "Lollipop"},
            {"Which company bought android?", "Nokia", "Apple", "No company", "Google"},
            {"Android is based on which language.", "C++", "C", "Java", "Python"}
    };

    TextView question_txt;
    RadioGroup radio_group;
    RadioButton choice1;
    RadioButton choice2;
    RadioButton choice3;
    RadioButton choice4;

    RadioButton checked_radio_btn;
    int checked_radio_id;
    int no_of_questions = 3;

    int question_number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_part1);

        question_txt = (TextView) findViewById(R.id.question_txt);
        radio_group = (RadioGroup) findViewById(R.id.group_id_part1);
        choice1 = (RadioButton) findViewById(R.id.choice1);
        choice2 = (RadioButton) findViewById(R.id.choice2);
        choice3 = (RadioButton) findViewById(R.id.choice3);
        choice4 = (RadioButton) findViewById(R.id.choice4);
        checked_radio_id = radio_group.getCheckedRadioButtonId();
        checked_radio_btn = (RadioButton) findViewById(checked_radio_id);

        if (QuizScreenPart2.came_from_part2 == 1) {
            question = "Question" + no_of_questions;
            position = no_of_questions - 1;
            String question_no = questions[position][0];
            question_txt.setText(question_no);
            choice1.setText(questions[position][1]);
            choice2.setText(questions[position][2]);
            choice3.setText(questions[position][3]);
            choice4.setText(questions[position][4]);
            QuizScreenPart2.came_from_part2 = 0;
        } else {
            question = "Question" + question_number;
            String question_no = questions[position][0];
            question_txt.setText(question_no);
            choice1.setText(questions[position][1]);
            choice2.setText(questions[position][2]);
            choice3.setText(questions[position][3]);
            choice4.setText(questions[position][4]);
        }

    }

    public void next_btn(View v) {

        checked_radio_id = radio_group.getCheckedRadioButtonId();
        checked_radio_btn = (RadioButton) findViewById(checked_radio_id);


        try {
            if (Arrays.asList(answers).contains(checked_radio_btn.getText().toString().toLowerCase())) {
                score += 1;
            }
            if (position < no_of_questions - 1) {
                position += 1;
                String question_no = questions[position][0];
                question_txt.setText(question_no);
                choice1.setText(questions[position][1]);
                choice2.setText(questions[position][2]);
                choice3.setText(questions[position][3]);
                choice4.setText(questions[position][4]);
                radio_group.clearCheck();

            } else {
                Intent i = new Intent(QuizScreenPart1.this, QuizScreenPart2.class);
                startActivity(i);
            }

        } catch (Exception e) {
            Toast.makeText(QuizScreenPart1.this, "choose one answer !!", Toast.LENGTH_LONG).show();

        }

    }


}
