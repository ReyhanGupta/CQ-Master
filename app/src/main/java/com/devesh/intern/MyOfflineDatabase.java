package com.devesh.intern;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devesh.intern.Tables.SQL_Commands;

/**
 * Created by devesh on 21/8/16.
 */
public class MyOfflineDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "offline_database";
    public static final int DB_VERSION = 1 ;
    private static MyOfflineDatabase myDBOpener = null;

    public MyOfflineDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static SQLiteDatabase openReadableDatabase(Context ctx){

        if(myDBOpener == null){
            myDBOpener = new MyOfflineDatabase(ctx);
        }
        return myDBOpener.getReadableDatabase();
    }

    public static  SQLiteDatabase openWritableDatabase(Context ctx){
        if(myDBOpener == null){
            myDBOpener = new MyOfflineDatabase(ctx);
        }
        return myDBOpener.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_Commands.TABLE_CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
