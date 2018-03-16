package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by mohab on 8/28/2017.
 */

public class EgyptUniversities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tour_list);

        final ArrayList<Tour> tours = new ArrayList<Tour>();
        tours.add(new Tour(getString(R.string.university1), getString(R.string.university1_date), R.drawable.cairo));
        tours.add(new Tour(getString(R.string.university2), getString(R.string.university2_date), R.drawable.helwan));
        tours.add(new Tour(getString(R.string.university3), getString(R.string.university3_date), R.drawable.alex));
        tours.add(new Tour(getString(R.string.university4), getString(R.string.university4_date), R.drawable.portsaid));
        tours.add(new Tour(getString(R.string.university5), getString(R.string.university5_date), R.drawable.ainshams));
        tours.add(new Tour(getString(R.string.university6), getString(R.string.university6_date), R.drawable.nu));
        tours.add(new Tour(getString(R.string.university7), getString(R.string.university7_date), R.drawable.msa));
        tours.add(new Tour(getString(R.string.university8), getString(R.string.university8_date), R.drawable.auc));

        TourAdapter adapter = new TourAdapter(this, tours, R.color.egyptUniversities);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}