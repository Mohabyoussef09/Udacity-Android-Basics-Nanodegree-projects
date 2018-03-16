package com.example.android.booklisting;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mohab on 8/15/2017.
 */

public class BookAdapter  extends ArrayAdapter<Book>
{

        public BookAdapter(Activity context, List<Book> keywords) {
            super(context, 0,keywords);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            View listItemView = convertView;
            if(listItemView == null)
                listItemView= LayoutInflater.from(getContext()).inflate(R.layout.activity_book, parent, false);

            Book currentBook= getItem(position);


            TextView author_name = (TextView) listItemView.findViewById(R.id.author_name_txt);
            author_name.setText(currentBook.getAuthorName().substring(2,(currentBook.getAuthorName().length()-2)));

            TextView book_title = (TextView) listItemView.findViewById(R.id.book_title_txt);
            book_title.setText(currentBook.getBookTitle());


            return listItemView;
        }
    }





