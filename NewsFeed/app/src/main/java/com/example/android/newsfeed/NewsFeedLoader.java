package com.example.android.newsfeed;

import android.support.v4.content.AsyncTaskLoader;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
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


/**
 * Created by mohab on 8/17/2017.
 */

public class NewsFeedLoader extends AsyncTaskLoader<List<News>> {
    static List<News> news;
    String keyword,keyword_url_request,JsonString;
    boolean connected_to_internet;
    NewsAdapter  adapter;


    public NewsFeedLoader(Context context) {
        super(context);

    }

    @Override
    protected void onStartLoading()
    {
        forceLoad();
    }

    @Override
    public List<News> loadInBackground()
    {

        try
        {
            JsonString=makeHttpRequest(createUrl(MainActivity.api_url));
            news = extractFeatureFromJson(JsonString);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return news;
    }







    private static String makeHttpRequest(URL url) throws IOException
    {
        String jsonResponse = "";

        if (url == null)
        {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200)
            {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
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

    private static List<News> extractFeatureFromJson(String NewsJSON)
    {
        if (TextUtils.isEmpty(NewsJSON))
        {
            return null;
        }
        ArrayList<News> News = new ArrayList<>();

        try
        {

            JSONObject jsonResponse = new JSONObject(NewsJSON);
            JSONObject jsonResults = jsonResponse.getJSONObject("response");
            JSONArray NewsArray = jsonResults.getJSONArray("results");

            for (int i = 0; i < NewsArray.length(); i++)
            {

                JSONObject currentnews = NewsArray.getJSONObject(i);
                String author_name;
                JSONArray tagsArray = currentnews.getJSONArray("tags");

                if (tagsArray.length() == 0)
                    author_name = "";
                else
                {
                    JSONObject firstObject = tagsArray.getJSONObject(0);
                    author_name = firstObject.getString("webTitle");
                }

                String publish_date = currentnews.getString("webPublicationDate").substring(0,10);
                String section_name = currentnews.getString("sectionName");
                String article_name = currentnews.getString("webTitle");
                String web_link = currentnews.getString("webUrl");

                News book_data = new News(article_name,section_name,author_name,publish_date,web_link);
                News.add(book_data);

            }



        } catch (JSONException e)
        {
          Log.e("QueryUtils", "Problem while parsing JSON results", e);
        }


        return News;
    }


}






