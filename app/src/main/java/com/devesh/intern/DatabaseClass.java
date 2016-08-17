package com.devesh.intern;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Devesh on 08-07-2016.
 */
public class DatabaseClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "story.db";
    public static final String TABLE_NAME = "info";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "GENRE";
    public static final String COL_4 = "STORY";
    public static final String COL_5 = "SYNOPSIS";
    public static final String COL_6 = "DATE";
    public static final String COL_7 = "TIME";

    public DatabaseClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,GENRE TEXT,STORY TEXT,SYNOPSIS TEXT,DATE,TEXT,TIME INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String genre,String synopsis,String story,String date,Integer time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,name);
        values.put(COL_3,genre);
        values.put(COL_4,story);
        values.put(COL_5,synopsis);
        values.put(COL_6,date);
        values.put(COL_7,time);
        long result = db.insert(TABLE_NAME,null,values);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }
}
