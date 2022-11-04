package com.example.laundrymanagementsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "laundry";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "users";

    public static final String ID = "id";
    public static final String FULL_NAME = "full_name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String ROLE_NAME = "role_name";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String PASSWORD = "password";

    public static final String USER_CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + " integer primary key autoincrement, " + FULL_NAME + " text, " + EMAIL + " text, " + ADDRESS + " text, " + ROLE_NAME + " text, " + PHONE_NUMBER + " text, " + PASSWORD + " text)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(USER_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
