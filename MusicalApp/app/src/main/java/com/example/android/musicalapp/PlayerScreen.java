package com.example.android.musicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by mohab on 8/25/2017.
 */

public class PlayerScreen extends AppCompatActivity {

    Button main, playlist, albums, payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        main = (Button) findViewById(R.id.main);
        playlist = (Button) findViewById(R.id.playlist);
        albums = (Button) findViewById(R.id.album);
        payment = (Button) findViewById(R.id.payment);

        main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
            }
        });
        playlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPlayList();
            }
        });
        albums.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAlbums();
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPayment();
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openPlayList() {
        Intent intent = new Intent(this, PlayListScreen.class);
        startActivity(intent);
    }

    public void openAlbums() {
        Intent intent = new Intent(this, AlbumsScreen.class);
        startActivity(intent);
    }

    public void openPayment() {
        Intent intent = new Intent(this, PaymentScreen.class);
        startActivity(intent);
    }
}