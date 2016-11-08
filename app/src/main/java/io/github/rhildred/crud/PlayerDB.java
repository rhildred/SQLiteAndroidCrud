package io.github.rhildred.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rhildred on 11/8/2016.
 */
public class PlayerDB {
    // database constants
    public static final String DB_NAME = "tasklist.db";
    public static final int    DB_VERSION = 1;
    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // create tables
            db.execSQL("CREATE TABLE players (id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , name VARCHAR NOT NULL , wins INTEGER NOT NULL  DEFAULT 0, losses INTEGER NOT NULL  DEFAULT 0, ties INTEGER NOT NULL  DEFAULT 0)");
            //insert some players
            db.execSQL("INSERT INTO players(name) VALUES('Rich')");
            db.execSQL("INSERT INTO players(name) VALUES('Steve')");
            db.execSQL("INSERT INTO players(name) VALUES('Tanesha')");
            db.execSQL("INSERT INTO players(name) VALUES('Sulim')");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE \"players\"");
            Log.d("Task list", "Upgrading db from version "
                    + oldVersion + " to " + newVersion);

            onCreate(db);
        }
    }

    // database and database helper objects
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    // constructor
    public PlayerDB(Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        openWriteableDB();
        closeDB();
    }
    // private methods
    private void openReadableDB() {
        db = dbHelper.getReadableDatabase();
    }

    private void openWriteableDB() {
        db = dbHelper.getWritableDatabase();
    }

    private void closeDB() {
        if (db != null)
            db.close();
    }

    ArrayList<HashMap<String, String>> getPlayers(){
        ArrayList<HashMap<String, String>> data =
                new ArrayList<HashMap<String, String>>();
        openReadableDB();
        Cursor cursor = db.rawQuery("SELECT name, wins, losses, ties FROM players",null );
        while (cursor.moveToNext()) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", cursor.getString(0));
            map.put("wins", cursor.getString(1));
            map.put("losses", cursor.getString(2));
            map.put("ties", cursor.getString(3));
            data.add(map);
        }
        if (cursor != null)
            cursor.close();
        closeDB();

        return data;
    }

    void insertPlayer(String sName)throws Exception{

        openWriteableDB();
        ContentValues content = new ContentValues();
        content.put("name", sName);
        long nResult = db.insert("players",null, content);
        if(nResult == -1) throw new Exception("no data");
        closeDB();
    }
}
