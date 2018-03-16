package com.example.android.tourguideapp;

/**
 * Created by mohab on 8/28/2017.
 */

public class Tour {

    private String name;
    private String description;
    private int imageResourceID = -1;


    private static final int NO_IMAGE = -1;

    public Tour(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public Tour(String name, String description, int imageResourceID) {
        this.name = name;
        this.description = description;
        this.imageResourceID = imageResourceID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public boolean hasImage() {
        return imageResourceID != NO_IMAGE;
    }


}
