package com.example.lich.even;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DBOpen extends SQLiteOpenHelper {


    private static final String CREATE_EVENTS_TABLE = "create table " +DBStruct.EVENT_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
            + DBStruct.EVENT+" TEXT, " + DBStruct.TIME+" TEXT," + DBStruct.DATE+" TEXT, " + DBStruct.MONTH+" TEXT, " + DBStruct.YEAR+" TEXT)";

    private static final String DROP_EVENTS_TABLE = " DROP TABLE IF EXISTS "+DBStruct.EVENT_TABLE_NAME;

    public DBOpen(@Nullable Context context) {
        super(context, DBStruct.DB_NAME, null, DBStruct.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_EVENTS_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void SaveEvent(String event, String time, String date, String month, String year, SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBStruct.EVENT,event);
        contentValues.put(DBStruct.TIME,time);
        contentValues.put(DBStruct.DATE,date);
        contentValues.put(DBStruct.MONTH,month);
        contentValues.put(DBStruct.YEAR,year);
        database.insert(DBStruct.EVENT_TABLE_NAME,null,contentValues);

    }
    public Cursor ReadEvents(String date, SQLiteDatabase database)
    {
        String [] Projections = {DBStruct.EVENT,DBStruct.TIME,DBStruct.DATE,DBStruct.MONTH,DBStruct.YEAR};
        String Selection = DBStruct.DATE +"=?";
        String [] SelectionsArgs = {date};

        return database.query(DBStruct.EVENT_TABLE_NAME,Projections,Selection,SelectionsArgs,null,null,null);

    }
    public Cursor ReadEventsperMonth(String month,String year, SQLiteDatabase database)
    {
        String [] Projections = {DBStruct.EVENT,DBStruct.TIME,DBStruct.DATE,DBStruct.MONTH,DBStruct.YEAR};
        String Selection = DBStruct.MONTH +"=? and "+DBStruct.YEAR+"=?";
        String [] SelectionsArgs = {month,year};
        return database.query(DBStruct.EVENT_TABLE_NAME,Projections,Selection,SelectionsArgs,null,null,null);

    }

}
