package com.example.android.newsfeed;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{

    static ArrayList<News> news,new_news;
    EditText search_txt;
    static String api_url;
    boolean connected_to_internet;
    NewsAdapter  adapter;
    ListView NewsListView;
    private static int LOADER_ID = 0;
    int scroll_position=0;
    static boolean search_btn_not_pressed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_txt=(EditText)findViewById(R.id.search_txt);


        NewsListView = (ListView)findViewById(R.id.list);
        if (savedInstanceState != null && search_btn_not_pressed)
        {
            new_news = new ArrayList<>(news);
            adapter = new NewsAdapter(MainActivity.this, new_news);
            NewsListView.setAdapter(adapter);

        }

        NewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                News news = adapter.getItem(i);
                String url = news.getWebLink();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(search_btn_not_pressed)
          scroll_position = NewsListView.getFirstVisiblePosition();

    }

    public void search_btn(View v)
    {
        search_btn_not_pressed=true;
        connected_to_internet=check_wifi_connection();
        if(connected_to_internet)
        {
            if(search_txt.getText().toString().equals(""))
            {
                Toast.makeText(MainActivity.this,"Please Enter News Title",Toast.LENGTH_SHORT).show();
            }
            else
            {
                api_url = "http://content.guardianapis.com/search?order-by=newest&show-references=author&show-tags=contributor&q=" + search_txt.getText().toString() + "&api-key=test";
                getSupportLoaderManager().initLoader(LOADER_ID, null, this);
                LOADER_ID++;
            }

        }
        else
        {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle)
    {
        return new NewsFeedLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data)
    {
        news=(ArrayList)data;
        NewsListView = (ListView)findViewById(R.id.list);
        adapter = new NewsAdapter(MainActivity.this, data);
        NewsListView.setAdapter(adapter);
     }


    @Override
    public void onLoaderReset(Loader<List<News>> loader) {

    }


    public boolean check_wifi_connection()
    {
        ConnectivityManager cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }



}
