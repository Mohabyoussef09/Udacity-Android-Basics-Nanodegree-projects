package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mohab on 8/28/2017.
 */

public class EgyptRestaurants extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        final ArrayList<Tour> tours = new ArrayList<Tour>();

        tours.add(new Tour(getString(R.string.restaurant1), getString(R.string.restaurant1_place)));
        tours.add(new Tour(getString(R.string.restaurant2), getString(R.string.restaurant2_place)));
        tours.add(new Tour(getString(R.string.restaurant3), getString(R.string.restaurant3_place)));
        tours.add(new Tour(getString(R.string.restaurant4), getString(R.string.restaurant4_place)));
        tours.add(new Tour(getString(R.string.restaurant5), getString(R.string.restaurant5_place)));
        tours.add(new Tour(getString(R.string.restaurant6), getString(R.string.restaurant6_place)));
        tours.add(new Tour(getString(R.string.restaurant7), getString(R.string.restaurant7_place)));
        tours.add(new Tour(getString(R.string.restaurant8), getString(R.string.restaurant8_place)));

        TourAdapter adapter = new TourAdapter(this, tours, R.color.egegyptRestaurants);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}