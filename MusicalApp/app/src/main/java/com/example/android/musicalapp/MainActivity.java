package com.example.android.musicalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button player, playlist, albums, payment, media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = (Button) findViewById(R.id.player);
        playlist = (Button) findViewById(R.id.playlist);
        albums = (Button) findViewById(R.id.albums);
        payment = (Button) findViewById(R.id.payment);
        media = (Button) findViewById(R.id.mediaplayer);

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
        media.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openMediaPlayer();
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
        actionBar.setTitle("Main Screen");

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

    public void openPayment() {
        Intent intent = new Intent(this, PaymentScreen.class);
        startActivity(intent);
    }

    public void openMediaPlayer() {
        Intent intent = new Intent(this, MediaPlayerScreen.class);
        startActivity(intent);
    }
}
