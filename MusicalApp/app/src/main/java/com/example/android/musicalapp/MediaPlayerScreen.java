package com.example.android.musicalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by mohab on 8/29/2017.
 */

public class MediaPlayerScreen extends AppCompatActivity {

    ListView listView;
    Button player, playlist, albums, main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        player = (Button) findViewById(R.id.play);
        playlist = (Button) findViewById(R.id.playlist);
        albums = (Button) findViewById(R.id.album);
        main = (Button) findViewById(R.id.main);

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
        albums.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAlbums();
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMain();
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

    public void openPlayList() {
        Intent intent = new Intent(this, PlayListScreen.class);
        startActivity(intent);
    }

    public void openAlbums() {
        Intent intent = new Intent(this, AlbumsScreen.class);
        startActivity(intent);
    }

    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
