package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mohab on 8/28/2017.
 */

public class EgyptMuseums extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        final ArrayList<Tour> tours = new ArrayList<Tour>();
        tours.add(new Tour(getString(R.string.museum1), getString(R.string.museum1_type)));
        tours.add(new Tour(getString(R.string.museum2), getString(R.string.museum2_type)));
        tours.add(new Tour(getString(R.string.museum3), getString(R.string.museum3_type)));
        tours.add(new Tour(getString(R.string.museum4), getString(R.string.museum4_type)));
        tours.add(new Tour(getString(R.string.museum5), getString(R.string.museum5_type)));
        tours.add(new Tour(getString(R.string.museum6), getString(R.string.museum6_type)));
        tours.add(new Tour(getString(R.string.museum7), getString(R.string.museum7_type)));
        tours.add(new Tour(getString(R.string.museum8), getString(R.string.museum8_type)));

        TourAdapter adapter = new TourAdapter(this, tours, R.color.egyptMusemus);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}