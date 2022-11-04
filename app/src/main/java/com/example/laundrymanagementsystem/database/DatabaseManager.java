package com.example.laundrymanagementsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.laundrymanagementsystem.model.Login;
import com.example.laundrymanagementsystem.model.Register;

import java.util.ArrayList;

public class DatabaseManager {

    DatabaseHelper databaseHelper;
    private Context context;

    public DatabaseManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public long userRegister(Register register) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.FULL_NAME, register.getFullName());
        contentValues.put(DatabaseHelper.EMAIL, register.getEmail());
        contentValues.put(DatabaseHelper.ADDRESS, register.getAddress());
        contentValues.put(DatabaseHelper.ROLE_NAME, register.getRoleName());
        contentValues.put(DatabaseHelper.PHONE_NUMBER, register.getPhoneNumber());
        contentValues.put(DatabaseHelper.PASSWORD, register.getPassword());

        long insertUserData = sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return insertUserData;
    }

    public ArrayList<Login> getUserDetails(String email, String password) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<Login> arrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME + " WHERE email=\'"
                + email + "\'" + " AND password=\'" + password + "\'";

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String getEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.EMAIL));
                String fullName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FULL_NAME));
                String address = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ADDRESS));
                String roleName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ROLE_NAME));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PHONE_NUMBER));

                Login login = new Login(fullName, getEmail, address, roleName, phoneNumber);
                arrayList.add(login);

            } while (cursor.moveToNext());
        }
        return arrayList;
    }
}
