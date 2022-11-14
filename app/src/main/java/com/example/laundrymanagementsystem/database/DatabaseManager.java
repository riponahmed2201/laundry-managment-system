package com.example.laundrymanagementsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.laundrymanagementsystem.model.Login;
import com.example.laundrymanagementsystem.model.Order;
import com.example.laundrymanagementsystem.model.Register;
import com.example.laundrymanagementsystem.model.VendorList;

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

        long insertUserData = sqLiteDatabase.insert(DatabaseHelper.USER_TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();

        return insertUserData;
    }

    public ArrayList<Order> getUserOrderDetails(String email) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        ArrayList<Order> orders = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.ORDER_TABLE_NAME + " WHERE email=\'" + email;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                String orderId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ORDER_ID));
                String vendorEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.VENDOR_EMAIL));
                String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_EMAIL));
                String userPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PHONE_NUMBER));
                String paymentOption = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PAYMENT_OPTION));
                String garmentCategory = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.GARMENT_CATEGORY));
                String garmentQuantity = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.GARMENT_QUANTITY));
                String orderPlacement = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ORDER_PLACEMENT));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.STATUS));

                Order order = new Order(orderId, vendorEmail, userEmail, userPhoneNumber, paymentOption, garmentCategory, garmentQuantity, orderPlacement, status);
                orders.add(order);

            } while (cursor.moveToNext());
        }
        return orders;
    }

    public ArrayList<Login> getUserDetails(String email, String password) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<Login> arrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DatabaseHelper.USER_TABLE_NAME + " WHERE email=\'"
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

    //Create Order Details
    public long createNewOrder(Order order) {

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseHelper.USER_EMAIL, order.getUserEmail());
        contentValues.put(DatabaseHelper.USER_PHONE_NUMBER, order.getUserPhoneNumber());
        contentValues.put(DatabaseHelper.PAYMENT_OPTION, order.getPaymentOption());
        contentValues.put(DatabaseHelper.GARMENT_CATEGORY, order.getGarmentCategory());
        contentValues.put(DatabaseHelper.GARMENT_QUANTITY, order.getGarmentQuantity());
        contentValues.put(DatabaseHelper.ORDER_PLACEMENT, order.getOrderPlacement());
        contentValues.put(DatabaseHelper.STATUS, order.getStatus());

        long orderInfo = sqLiteDatabase.insert(DatabaseHelper.ORDER_TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return orderInfo;
    }

    public ArrayList<Order> getOrderDetails() {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();

        ArrayList<Order> arrayList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DatabaseHelper.ORDER_TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String orderId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ORDER_ID));
                String vendorEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.VENDOR_EMAIL));
                String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_EMAIL));
                String userPhoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.USER_PHONE_NUMBER));
                String paymentOption = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PAYMENT_OPTION));
                String garmentCategory = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.GARMENT_CATEGORY));
                String garmentQuantity = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.GARMENT_QUANTITY));
                String orderPlacement = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ORDER_PLACEMENT));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.STATUS));

                Order order = new Order(orderId, vendorEmail, userEmail, userPhoneNumber, paymentOption, garmentCategory, garmentQuantity, orderPlacement, status);
                arrayList.add(order);

            } while (cursor.moveToNext());
        }
        return arrayList;
    }

//    public ArrayList<VendorList> getVendorsByVendorEmail() {
//        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
//
//        ArrayList<VendorList> arrayList = new ArrayList<>();
//
//        String selectQuery = "SELECT  * FROM " + DatabaseHelper.USER_TABLE_NAME + " WHERE role_name= VENDOR";
//
//        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//
//                String vendorId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.ID));
//                String vendorName = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FULL_NAME));
//                String vendorEmail = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.VENDOR_EMAIL));
//
//                VendorList vendorList = new VendorList(vendorId, vendorName, vendorEmail);
//                arrayList.add(vendorList);
//
//            } while (cursor.moveToNext());
//        }
//        return arrayList;
//    }
}
