package com.example.laundrymanagementsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.laundrymanagementsystem.model.Register;

public class DatabaseManager {

    DatabaseHelper databaseHelper;
    private Context context;

    public DatabaseManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public long userRegister(Register register){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.FULL_NAME,register.getFullName());
        contentValues.put(DatabaseHelper.EMAIL,register.getEmail());
        contentValues.put(DatabaseHelper.ADDRESS,register.getAddress());
        contentValues.put(DatabaseHelper.ROLE_NAME,register.getRoleName());
        contentValues.put(DatabaseHelper.PHONE_NUMBER,register.getPhoneNumber());
        contentValues.put(DatabaseHelper.PASSWORD,register.getPassword());

        long insertUserData = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return insertUserData;
    }
}
