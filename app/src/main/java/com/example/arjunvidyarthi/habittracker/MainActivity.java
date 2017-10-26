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

        InsertHabit("Walked the dog", "24/10/17");
        InsertHabit("Went trekking", "25/10/17");
        InsertHabit("Jogged a mile", "26/10/17");
        DisplayInfo();
    }


    public void InsertHabit(String habit, String date){

        SQLiteDatabase db = myHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_NAME, habit);
        values.put(HabitContract.HabitEntry.COLUMN_HABIT_DATE, date);

        long newRowID = db.insert(HabitContract.HabitEntry.TABLE_NAME,null, values);
    }

    public void DisplayInfo(){
        SQLiteDatabase db = myHelper.getReadableDatabase();
        Cursor cursor = db.query(HabitContract.HabitEntry.TABLE_NAME, null, null, null, null, null, null);
        TextView info = (TextView) findViewById(R.id.info);
        try{

            info.setText("Total habits added in the table: "+cursor.getCount());

            info.append("\n\n_id - habit name - date");

            int IDColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int NameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_NAME);
            int DateColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_HABIT_DATE);

            while(cursor.moveToNext()){
                int currentID = cursor.getInt(IDColumnIndex);
                String currentName = cursor.getString(NameColumnIndex);
                String currentDate = cursor.getString(DateColumnIndex);

                info.append("\n"+currentID+" - "+currentName+" - "+currentDate);

            }
        }

        finally {
            info.append("\n\n//RE - OPEN APP TO REFRESH THIS LIST//");
            cursor.close();
        }
    }

}
