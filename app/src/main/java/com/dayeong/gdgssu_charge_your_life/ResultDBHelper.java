package com.dayeong.gdgssu_charge_your_life;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dayeong on 2016-06-24.
 */

public class ResultDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "result_list";

    private static final String RESULT_TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "FIRST INTEGER, " +
                    "SECOND INTEGER, " +
                    "THIRD INTEGER, " +
                    "NAME TEXT" +
                    "ADDRESS TEXT " +
                    "EXPLAIN TEXT " +
                    "IMAGE INTEGER" +
                    ");";

    private static final String RESULT_TABLE_DELETE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public ResultDBHelper(Context context) {
        super(context, "result.db", null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(RESULT_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(RESULT_TABLE_DELETE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
