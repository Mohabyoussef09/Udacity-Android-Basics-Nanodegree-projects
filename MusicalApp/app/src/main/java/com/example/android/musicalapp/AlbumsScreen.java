package com.example.android.musicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by mohab on 8/25/2017.
 */

public class AlbumsScreen extends AppCompatActivity {

    ListView listView;
    Button player, playlist, main, payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        listView = (ListView) findViewById(R.id.list);
        ArrayList val = new ArrayList();
        for (int i = 1; i <= 20; i++)
            val.add("Album " + String.valueOf(i));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, val);
        listView.setAdapter(adapter);

        player = (Button) findViewById(R.id.play);
        playlist = (Button) findViewById(R.id.playlist);
        main = (Button) findViewById(R.id.main);
        payment = (Button) findViewById(R.id.payment);

        player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPlayer();
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPlayList();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPayment();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void openPlayer() {
        Intent intent = new Intent(AlbumsScreen.this, PlayerScreen.class);
        startActivity(intent);

    }

    public void openPlayList() {
        Intent intent = new Intent(AlbumsScreen.this, PlayListScreen.class);
        startActivity(intent);
    }

    public void openMain() {
        Intent intent = new Intent(AlbumsScreen.this, MainActivity.class);
        startActivity(intent);
    }

    public void openPayment() {
        Intent intent = new Intent(AlbumsScreen.this, PaymentScreen.class);
        startActivity(intent);
    }
}