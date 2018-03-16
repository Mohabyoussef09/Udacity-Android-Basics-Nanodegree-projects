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

public class PlayListScreen extends AppCompatActivity {

    ListView listView;
    Button player, main, albums, payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        listView = (ListView) findViewById(R.id.list);
        ArrayList val = new ArrayList();
        for (int i = 1; i <= 20; i++)
            val.add("PlayList" + String.valueOf(i));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, val);
        listView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        player = (Button) findViewById(R.id.play);
        main = (Button) findViewById(R.id.main);
        albums = (Button) findViewById(R.id.album);
        payment = (Button) findViewById(R.id.payment);

        player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openPlayer();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
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

    public void openPlayer() {
        Intent intent = new Intent(this, PlayerScreen.class);
        startActivity(intent);
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
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