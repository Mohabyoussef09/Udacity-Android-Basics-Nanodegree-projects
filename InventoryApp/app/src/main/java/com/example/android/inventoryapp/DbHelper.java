package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Inventory";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE;
        SQL_CREATE_TABLE = "CREATE TABLE " + Contract.inventory.TABLE_NAME + " (" +
                Contract.inventory.PROD_NAME + " TEXT,  " +
                Contract.inventory.QUANTITY + " INTEGER,  " +
                Contract.inventory.PRICE + " INTEGER,  " +
                Contract.inventory.IMAGE + " Text);";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void saveToLocalDb(String prod_name, int quantity, int price, String image, SQLiteDatabase database) {
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(Contract.inventory.PROD_NAME, prod_name);
        contentvalues.put(Contract.inventory.QUANTITY, quantity);
        contentvalues.put(Contract.inventory.PRICE, price);
        contentvalues.put(Contract.inventory.IMAGE, image);
        database.insert(Contract.inventory.TABLE_NAME, null, contentvalues);
    }

    public Cursor readFromLocalDb(SQLiteDatabase db) {
        String[] projection = {Contract.inventory.PROD_NAME, Contract.inventory.QUANTITY, Contract.inventory.PRICE, Contract.inventory.IMAGE};
        return (db.query(Contract.inventory.TABLE_NAME, projection, null, null, null, null, null));
    }
}