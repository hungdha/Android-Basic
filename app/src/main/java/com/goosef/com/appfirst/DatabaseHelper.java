package com.goosef.com.appfirst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.sql.SQLException;

/**
 * Created by nam on 10/12/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "appfirst.db";
    private static final String TBL_USERS = "users";
    private static DatabaseHelper instance;
    private static Context context;
    private static SQLiteDatabase db;
    private static final String CREATE_TBL_USERS = "CREATE TABLE users" +
            "( id INTEGER PRIMARY KEY," +
            " username TEXT," +
            " password TEXT," +
            " email TEXT )";

    public static synchronized DatabaseHelper getInstance( Context context ){
        if(instance == null){
            instance = new DatabaseHelper(context, DB_NAME, null , DB_VERSION );
            db = instance.getWritableDatabase();
            Toast.makeText(context, "Connect Database", Toast.LENGTH_SHORT).show();
        }
        return instance;
    }
    public DatabaseHelper(Context context , String name, SQLiteDatabase.CursorFactory factory , int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("DatabaseHelper", "before creation");
        db.execSQL(CREATE_TBL_USERS);
        Log.v("DatabaseHelper", "after creation");
    }
    // closing database
    public synchronized void close() {
        if( instance  != null ){
           instance.getReadableDatabase().close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USERS);
        // create new tables
        onCreate(db);
    }
}
