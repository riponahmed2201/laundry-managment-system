package com.example.laundrymanagementsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "laundry";
    public static final int DATABASE_VERSION = 2;

    public static final String USER_TABLE_NAME = "users";
    public static final String ORDER_TABLE_NAME = "orders";

    // User table info
    public static final String ID = "id";
    public static final String FULL_NAME = "full_name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String ROLE_NAME = "role_name";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String PASSWORD = "password";

    //Create Users Table Query
    public static final String USER_CREATE_TABLE_QUERY = "create table " + USER_TABLE_NAME + "(" + ID + " integer primary key autoincrement, " + FULL_NAME + " text, " + EMAIL + " text, " + ADDRESS + " text, " + ROLE_NAME + " text, " + PHONE_NUMBER + " text, " + PASSWORD + " text)";

    private EditText garmentCategoryId;
    private EditText garmentQuantityId;
    private EditText orderPlacementId;

    //Order table info
    public static final String ORDER_ID = "id";
    public static final String VENDOR_EMAIL = "vendor_email";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String PAYMENT_OPTION = "payment_option";
    public static final String GARMENT_CATEGORY = "garment_category";
    public static final String GARMENT_QUANTITY = "garment_quantity";
    public static final String ORDER_PLACEMENT = "order_placement";
    public static final String STATUS = "status";

    //Create Order Table Query
    public static final String CREATE_ORDER_TABLE_QUERY = "create table " + ORDER_TABLE_NAME + "(" + ORDER_ID + " integer primary key autoincrement, " + VENDOR_EMAIL + " text, " + USER_EMAIL + " text, " + USER_PHONE_NUMBER + "text" + PAYMENT_OPTION + " text, " + GARMENT_CATEGORY + " text, " + GARMENT_QUANTITY + " text, " + ORDER_PLACEMENT + " text, " + STATUS + " text )";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE_QUERY);
        sqLiteDatabase.execSQL(USER_CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
