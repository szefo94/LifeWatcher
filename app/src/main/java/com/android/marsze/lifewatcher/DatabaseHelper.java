package com.android.marsze.lifewatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "daily_activities";
    private static final String COL1 = "ID";
    private static final String COL2 = "day";
    private static final String COL3 = "month";
    private static final String COL4 = "year";
    private static final String COL5 = "activity_name";
    private static final String COL6 = "details";
    private static final String COL7 = "bar_value";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" INT,"+
                COL3 +" INT,"+
                COL4 +" INT,"+
                COL5 +" TEXT,"+
                COL6 +" TEXT,"+
                COL7 +" INT"+
                ")" ;
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    public boolean addData(int day, int month, int year, String actName, String details, int barValue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, day);
        contentValues.put(COL3, month);
        contentValues.put(COL4, year);
        contentValues.put(COL5, actName);
        contentValues.put(COL6, details);
        contentValues.put(COL7, barValue);

        Log.d(TAG, "addData: Adding " + day + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public void deleteTab(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE "+ TABLE_NAME);

    }
    public Cursor wipeData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        db.execSQL("DELETE FROM "+ TABLE_NAME);
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor doQuery(String queryGiven){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = queryGiven;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
