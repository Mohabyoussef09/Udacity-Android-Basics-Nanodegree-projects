package com.example.android.newsfeed;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mohab on 8/17/2017.
 */

public class NewsAdapter  extends ArrayAdapter<News>
{

    public NewsAdapter(Activity context, List<News> keywords)
    {
        super(context, 0,keywords);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        View listItemView = convertView;
        if(listItemView == null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.activity_news, parent, false);
        }


        News currentNews= getItem(position);

        TextView author_name = (TextView) listItemView.findViewById(R.id.author);
        author_name.setText(currentNews.getAuthorName());

        TextView section_name = (TextView) listItemView.findViewById(R.id.section);
        section_name.setText(currentNews.getSectionName());

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentNews.getArticleName());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentNews.getDate());

        return listItemView;
    }
}





