package com.example.arjunvidyarthi.habittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by Arjun Vidyarthi on 27-Oct-17.
 */

public class HabitContract {
    private HabitContract(){
        //to keep the class from being instantiated.
    }

    public static class HabitEntry implements BaseColumns{
        public static final String TABLE_NAME = "Habits";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_DATE = "date";

    }
}
