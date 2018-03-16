package com.example.android.educationalapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mohab on 8/11/2017.
 */

public class QuizScreenPart2 extends AppCompatActivity {

    static int position = 0;
    static int came_from_part2 = 0;
    String[] answers = {"bold", "normal", "italic", "simulator", "emulator", "xml", "java"};
    String question;
    String[][] questions = new String[][]{
            {"Which is the textStyle atrribute values ?", "Bold", "Normal", "Large", "Italic"},
            {"Which of the following  have any UI component and run as a background process?", "Services", "Simulator", "Emulator", "Provider"},
            {"Android layout can be made using which of the following ?", "XML", "Draw layout app", "Java", "intent"}
    };

    TextView question_txt;
    RadioGroup radio_group;
    CheckBox choice1;
    CheckBox choice2;
    CheckBox choice3;
    CheckBox choice4;
    int no_of_questions = 3;
    int question_number = 1;
    List<String> user_answer = new ArrayList<String>();
    int selected_answers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_part2);

        question_txt = (TextView) findViewById(R.id.question_txt);
        radio_group = (RadioGroup) findViewById(R.id.group_id_part1);
        choice1 = (CheckBox) findViewById(R.id.choice1);
        choice2 = (CheckBox) findViewById(R.id.choice2);
        choice3 = (CheckBox) findViewById(R.id.choice3);
        choice4 = (CheckBox) findViewById(R.id.choice4);
        question = "Question" + question_number;

        String question_no = questions[position][0];
        question_txt.setText(question_no);
        choice1.setText(questions[position][1]);
        choice2.setText(questions[position][2]);
        choice3.setText(questions[position][3]);
        choice4.setText(questions[position][4]);

    }

    public void next_btn(View v) {

        if (choice1.isChecked()) {
            user_answer.add(choice1.getText().toString().toLowerCase());
            selected_answers += 1;
        }

        if (choice2.isChecked()) {
            user_answer.add(choice2.getText().toString().toLowerCase());
            selected_answers += 1;
        }

        if (choice3.isChecked()) {
            user_answer.add(choice3.getText().toString().toLowerCase());
            selected_answers += 1;
        }

        if (choice4.isChecked()) {
            user_answer.add(choice4.getText().toString().toLowerCase());
            selected_answers += 1;
        }

        int count = 0;
        for (int i = 0; i < selected_answers; i++) {
            if (Arrays.asList(answers).contains(user_answer.get(i))) {
                count += 1;
            }

        }

        if (count == selected_answers)
            QuizScreenPart1.score += 1;

        selected_answers = 0;
        user_answer.clear();

        clear_checkboxes();
        if (position < no_of_questions - 1) {
            position += 1;
            String question_no = questions[position][0];
            question_txt.setText(question_no);
            choice1.setText(questions[position][1]);
            choice2.setText(questions[position][2]);
            choice3.setText(questions[position][3]);
            choice4.setText(questions[position][4]);

        } else {
            Intent i = new Intent(QuizScreenPart2.this, QuizScreenPart3.class);
            startActivity(i);
        }

    }

    private void clear_checkboxes() {
        choice1.setChecked(false);
        choice2.setChecked(false);
        choice3.setChecked(false);
        choice4.setChecked(false);

    }
}
