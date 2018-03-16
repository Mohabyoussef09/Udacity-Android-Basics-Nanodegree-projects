package com.example.android.booklisting;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.name;
import static android.R.attr.orientation;
import static android.R.attr.y;

public class MainActivity extends AppCompatActivity
{

    EditText search_txt;
    static ArrayList<Book> books,new_book;
    int scroll_position=0;
    String keyword,keyword_url_request,JsonString;
    boolean connected_to_internet;
    BookAdapter  adapter;
    ListView bookListView;
    static boolean search_btn_not_pressed=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_txt=(EditText)findViewById(R.id.book_keyword);


        if (savedInstanceState != null && search_btn_not_pressed)
        {
                new_book = new ArrayList<>(books);
                bookListView = (ListView)findViewById(R.id.list);
                adapter = new BookAdapter(MainActivity.this, new_book);
                bookListView.setAdapter(adapter);

            }

    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if(search_btn_not_pressed)
          scroll_position = bookListView.getFirstVisiblePosition();
    }

    public void search_btn(View v)
    {
        search_btn_not_pressed=true;
        connected_to_internet=check_wifi_connection();
        if(connected_to_internet)
        {
            keyword=search_txt.getText().toString();
            if(keyword.equals(""))
            {
                Toast.makeText(MainActivity.this,"Please enter book keyword",Toast.LENGTH_SHORT).show();
            }
            else
            {
                keyword_url_request ="https://www.googleapis.com/books/v1/volumes?q="+keyword;
                BookAsyncTask task = new BookAsyncTask();
                task.execute(keyword_url_request);

            }


        }
        else
        {
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }


    }


    private class BookAsyncTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls)
        {

            if (urls.length < 1 || urls[0] == null)
            {
                return null;
            }
            else
            {
                try {
                    JsonString=makeHttpRequest(createUrl(keyword_url_request));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return JsonString;
        }



        @Override
        protected void onPostExecute(String data)
        {
            books = extractFeatureFromJson(JsonString);
            bookListView = (ListView)findViewById(R.id.list);
            adapter = new BookAdapter(MainActivity.this, books);
            if (books!=null)
                bookListView.setAdapter(adapter);
            else
            {
                bookListView.setAdapter(null);
                View empty = getLayoutInflater().inflate(R.layout.empty_list_view, null, false);
                addContentView(empty, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                bookListView.setEmptyView(empty);

            }





        }

    }




    private static String makeHttpRequest(URL url) throws IOException
    {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try
        {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {

            }
        } catch (IOException e)
        {

        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException
    {
        StringBuilder output = new StringBuilder();
        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null)
            {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private static URL createUrl(String stringUrl)
    {
        URL url = null;
        try
        {
            url = new URL(stringUrl);
        } catch (MalformedURLException e)
        {
          return null;
        }
        return url;
    }


    private static ArrayList<Book> extractFeatureFromJson(String BookJSON)
    {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(BookJSON))
        {
            return null;
        }

        ArrayList<Book> books = new ArrayList<>();

        try
        {

            JSONObject baseJsonResponse = new JSONObject(BookJSON);
            int x=baseJsonResponse.getInt("totalItems");
            if(baseJsonResponse.getInt("totalItems")==0)
            {
                return null;
            }

            JSONArray bookArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < bookArray.length(); i++)
            {
                JSONObject currentBook = bookArray.getJSONObject(i);
                JSONObject properties = currentBook.getJSONObject("volumeInfo");
                String author_name = properties.getString("authors");
                String book_title= properties.getString("title");

                Book book_data = new Book(author_name,book_title);
                books.add(book_data);


            }

        } catch (JSONException e)
        {
            Log.e("QueryUtils", "Problem while parsing JSON results", e);
        }

        return books;
    }


    public boolean check_wifi_connection()
    {
        ConnectivityManager cm =(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
