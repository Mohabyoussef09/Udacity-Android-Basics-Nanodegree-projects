package com.example.android.scorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int goal_t1=0;
    int foul_t1=0;
    int corner_t1=0;

    int goal_t2=0;
    int foul_t2=0;
    int corner_t2=0;

    TextView goal_team1;
    TextView foul_team1;
    TextView corner_team1;

    TextView goal_team2;
    TextView foul_team2;
    TextView corner_team2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         goal_team1=(TextView) findViewById(R.id.team1_goals_txt);
         foul_team1=(TextView) findViewById(R.id.team1_fouls_txt);
         corner_team1=(TextView) findViewById(R.id.team1_corners_txt);

         goal_team2=(TextView) findViewById(R.id.team2_goals_txt);
         foul_team2=(TextView) findViewById(R.id.team2_fouls_txt);
         corner_team2=(TextView) findViewById(R.id.team2_corners_txt);

    }


    //Team 1 Buttons
    public void team1_goals_btn(View v)
    {
        goal_t1 = goal_t1 + 1;
        goal_team1.setText(String.valueOf(goal_t1));
    }

    public void team1_fouls_btn(View v)
    {
        foul_t1 = foul_t1 + 1;
        foul_team1.setText(String.valueOf(foul_t1));
    }

    public void team1_corners_btn(View v)
    {
        corner_t1 = corner_t1 + 1;
        corner_team1.setText(String.valueOf(corner_t1));
    }


    //Team 2 Buttons
    public void team2_goals_btn(View v)
    {
        goal_t2 = goal_t2 + 1;
        goal_team2.setText(String.valueOf(goal_t2));
    }

    public void team2_fouls_btn(View v)
    {
        foul_t2 = foul_t2 + 1;
        foul_team2.setText(String.valueOf(foul_t2));
    }

    public void team2_corners_btn(View v)
    {
        corner_t2 = corner_t2 + 1;
        corner_team2.setText(String.valueOf(corner_t2));
    }


    //Reset button
    public void reset_btn(View v)
    {
        goal_team1.setText("0");
        goal_team2.setText("0");
        foul_team1.setText("0");
        foul_team2.setText("0");
        corner_team1.setText("0");
        corner_team2.setText("0");

        goal_t1=0;
        foul_t1=0;
        corner_t1=0;
        goal_t2=0;
        foul_t2=0;
        corner_t2=0;
    }
}
