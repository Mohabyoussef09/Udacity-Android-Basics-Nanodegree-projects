package com.example.android.tourguideapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void egyptRestaurants(View v) {
        Intent screen = new Intent(this, EgyptRestaurants.class);
        startActivity(screen);
    }

    public void egyptMuseums(View v) {
        Intent screen = new Intent(this, EgyptMuseums.class);
        startActivity(screen);
    }

    public void egyptFootballTeams(View v) {
        Intent screen = new Intent(this, EgyptFootballTeams.class);
        startActivity(screen);
    }

    public void egyptUniversities(View v) {
        Intent screen = new Intent(this, EgyptUniversities.class);
        startActivity(screen);
    }

}
