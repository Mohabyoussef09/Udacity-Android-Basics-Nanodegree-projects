package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.android.habittracker.Contract.Medicine.medicine_name;

/**
 * Created by mohab on 8/25/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Medicine";
    private static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE;
        SQL_CREATE_TABLE = "CREATE TABLE " + Contract.Medicine.TABLE_NAME + " (" +
                medicine_name + " TEXT,  " +
                Contract.Medicine.no_of_tablet_per_day + " INTEGER);";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }


    public void saveToLocalDb(String medicine_name, int tablet_no, SQLiteDatabase database){
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(Contract.Medicine.medicine_name,medicine_name);
        contentvalues.put(Contract.Medicine.no_of_tablet_per_day,tablet_no);
        database.insert(Contract.Medicine.TABLE_NAME,null,contentvalues);
    }


    public Cursor readFromLocalDb(SQLiteDatabase db){
        String[] projection ={Contract.Medicine.medicine_name, Contract.Medicine.no_of_tablet_per_day};
        return (db.query(Contract.Medicine.TABLE_NAME,projection,null,null,null,null,null));
    }
}