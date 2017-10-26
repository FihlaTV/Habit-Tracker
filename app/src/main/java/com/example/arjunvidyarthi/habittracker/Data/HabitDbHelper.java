package com.example.arjunvidyarthi.habittracker.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Arjun Vidyarthi on 27-Oct-17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_ENTRIES =

            "CREATE TABLE " + HabitContract.HabitEntry.TABLE_NAME + " (" +
                    HabitContract.HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HabitContract.HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL," +
                    HabitContract.HabitEntry.COLUMN_HABIT_DATE + " TEXT NOT NULL);";


    public static final int DATABASE_VERSION  = 1;
    public static final String DATABASE_NAME = "habits.db";

    public HabitDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(sqLiteDatabase);
    }

}
