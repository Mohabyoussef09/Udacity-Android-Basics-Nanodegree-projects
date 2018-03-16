package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mohab on 8/28/2017.
 */

public class EgyptFootballTeams extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        final ArrayList<Tour> tours = new ArrayList<Tour>();
        tours.add(new Tour(getString(R.string.zamalek_team), getString(R.string.zamalek_year), R.drawable.zamalek));
        tours.add(new Tour(getString(R.string.ahly_team), getString(R.string.ahly_year), R.drawable.ahly));
        tours.add(new Tour(getString(R.string.ismaily_team), getString(R.string.ismaily_year), R.drawable.ismaily));
        tours.add(new Tour(getString(R.string.mokawleen_team), getString(R.string.mokawleen_year), R.drawable.mokawlen));
        tours.add(new Tour(getString(R.string.ghazlmahala_team), getString(R.string.ghazlmahala_year), R.drawable.mahala));
        tours.add(new Tour(getString(R.string.smoha_team), getString(R.string.smoha_year), R.drawable.smoha));
        tours.add(new Tour(getString(R.string.gona_team), getString(R.string.gona_year), R.drawable.gona));
        tours.add(new Tour(getString(R.string.petrojet_team), getString(R.string.petrojet_year), R.drawable.petrojet));
        tours.add(new Tour(getString(R.string.wadiDegla_team), getString(R.string.wadiDegla_year), R.drawable.degla));

        TourAdapter adapter = new TourAdapter(this, tours, R.color.egyptFootballTeams);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}