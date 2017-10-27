package com.example.arjunvidyarthi.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.arjunvidyarthi.habittracker.Data.HabitContract;
import com.example.arjunvidyarthi.habittracker.Data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper myHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myHelper = new HabitDbHelper(this);

        InsertHabit("Walked the dog", "24/10/17", 6);
        InsertHabit("Went trekking", "25/10/17", 2);
        InsertHabit("Jogged a mile", "26/10/17", 4);
        DisplayInfo(GetInfo());
    }


    public void InsertHabit(String habit, String date, int repeat){

        SQLiteDatabase db = myHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, habit);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, date);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_REPEAT, repeat);

        long newRowID = db.insert(HabitContract.HabitEntry.TABLE_NAME,null, values);
    }

    public Cursor GetInfo(){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, null, null, null, null, null, null);

        return cursor;
    }

    public void DisplayInfo(Cursor cursor) {
        TextView info = (TextView) findViewById(R.id.info);

        info.setText("Total habits added in the table: " + cursor.getCount());
        info.append("\n\n_id - habit name - date - number of repeats");

        int IDColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
        int NameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
        int DateColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_DATE);
        int RepeatColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_REPEAT);
        try {


            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(IDColumnIndex);
                String currentName = cursor.getString(NameColumnIndex);
                String currentDate = cursor.getString(DateColumnIndex);
                int currentRepeat = cursor.getShort(RepeatColumnIndex);

                info.append("\n" + currentID + " - " + currentName + " - " + currentDate + " - " + currentRepeat);

            }
        }
        finally {
            info.append("\n\n//RE - OPEN APP TO REFRESH THIS LIST//");
            cursor.close();
        }
    }

}
