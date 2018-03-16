package com.example.android.tourguideapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohab on 8/28/2017.
 */

public class TourAdapter extends ArrayAdapter<Tour> {

    private int mColorResourceId;

    public TourAdapter(Activity context, ArrayList<Tour> tours, int ColorBackground) {
        super(context, 0, tours);
        mColorResourceId = ColorBackground;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Tour currentWord = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.item_text_view);
        miwokTextView.setText(currentWord.getName());


        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.description_text_view);
        defaultTextView.setText(currentWord.getDescription());

        ImageView imageview = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {

            imageview.setImageResource(currentWord.getImageResourceID());
            imageview.setVisibility(View.VISIBLE);

        } else {
            imageview.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);
        return listItemView;
    }
}
