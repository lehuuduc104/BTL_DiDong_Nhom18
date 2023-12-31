package com.example.finalproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataManager {
    //Phương thức kiểm tra xem email đã tồn tại
    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM " + TABLE_N_AND_A + " WHERE " + TABLE_ROW_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = mDB.rawQuery(query, selectionArgs);

        int count = 0;
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }

        return count > 0;
    }
    // This is the actual database
    private SQLiteDatabase mDB;

    /*
        Next we have a public static final string for
        each row/table that we need to refer to both
        inside and outside this class
    */

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_FIRSTNAME = "first_name";
    public static final String TABLE_ROW_LASTNAME = "last_name";
    public static final String TABLE_ROW_EMAIL = "email";
    public static final String TABLE_ROW_PASSWORD = "password";
    public static final String TABLE_ROW_CONFIRM_PASSWORD = "confirmed_password";

    /*
        Next we have a private static final strings for
        each row/table that we need to refer to just
        inside this class
    */

    private static final String DB_NAME = "user_details";
    private static final int DB_VERSION = 1;
    private static final String TABLE_N_AND_A = "user_basic_details";

    public DataManager(Context context) {
        // Create an instance of our internal CustomSQLiteOpenHelper class
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);

        // Get a writable database
        mDB = helper.getWritableDatabase();
    }


    // Here are all our helper methods

    // Insert a record
    public void insert(String firstname, String lastname, String email,String password,String confirmedpassword){

        // Add all the details to the table
        String query = "INSERT INTO " + TABLE_N_AND_A + " (" +
                TABLE_ROW_FIRSTNAME + ", " +
                TABLE_ROW_LASTNAME + " , " +
                TABLE_ROW_EMAIL + " , " +
                TABLE_ROW_PASSWORD + " , " +
                TABLE_ROW_CONFIRM_PASSWORD +
                ") " +
                "VALUES (" +
                "'" + firstname + "'" + ", " +
                "'" + lastname + "'" + ", " +
                "'" + email + "'" + ", " +
                "'" + password + "'" + ", " +
                "'" + confirmedpassword + "'" +
                ");";

        Log.i("insert() = ", query);

        mDB.execSQL(query);

    }


    // Delete a record
    public void delete(String email){

        // Delete the details from the table if already exists
        String query = "DELETE FROM " + TABLE_N_AND_A +
                " WHERE " + TABLE_ROW_EMAIL +
                " = '" + email + "';";

        Log.i("delete() = ", query);

        mDB.execSQL(query);

    }

    // Get all the records
    public Cursor selectAll() {
        Cursor c = mDB.rawQuery("SELECT *" +" from " +
                TABLE_N_AND_A, null);

        return c;
    }

    // Find a specific record
    public Cursor searchName(String firstname) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_FIRSTNAME +
                ", " + TABLE_ROW_EMAIL +
                " from " +
                TABLE_N_AND_A + " WHERE " +
                TABLE_ROW_FIRSTNAME + " = '" + firstname + "';";

        Log.i("searchName() = ", query);

        Cursor c = mDB.rawQuery(query, null);


        return c;
    }

    // Find a specific record by email and password
    public Cursor searchByEmailAndPassword(String email, String password) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_FIRSTNAME +
                ", " + TABLE_ROW_LASTNAME
                + ", " + TABLE_ROW_EMAIL + ", " +
                TABLE_ROW_PASSWORD + ", " +
                TABLE_ROW_CONFIRM_PASSWORD +
                " FROM " +
                TABLE_N_AND_A + " WHERE " +
                TABLE_ROW_EMAIL + " = ? AND " +
                TABLE_ROW_PASSWORD + " = ?";

        Log.i("searchByEmailAndPassword() = ", query);

        String[] selectionArgs = {email, password};
        Cursor cursor = mDB.rawQuery(query, selectionArgs);

        return cursor;
    }
    //Hàm cập nhật lại dữ liệu
    public void updateUser(int id, String firstname, String lastname, String email, String password, String confirmedpassword) {
        // Tạo câu truy vấn UPDATE để cập nhật thông tin người dùng
        String query = "UPDATE " + TABLE_N_AND_A + " SET " +
                TABLE_ROW_FIRSTNAME + " = '" + firstname + "', " +
                TABLE_ROW_LASTNAME + " = '" + lastname + "', " +
                TABLE_ROW_EMAIL + " = '" + email + "', " +
                TABLE_ROW_PASSWORD + " = '" + password + "', " +
                TABLE_ROW_CONFIRM_PASSWORD + " = '" + confirmedpassword + "' " +
                "WHERE " + TABLE_ROW_ID + " = " + id;

        Log.i("updateUser() = ", query);

        // Thực thi câu truy vấn để cập nhật thông tin người dùng
        mDB.execSQL(query);
    }

    public Cursor searchByEmail(String email) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_FIRSTNAME +
                ", " + TABLE_ROW_LASTNAME +
                " FROM " +
                TABLE_N_AND_A + " WHERE " +
                TABLE_ROW_EMAIL + " = ?";

        Log.i("searchByEmail() = ", query);
        String[] selectionArgs = {email};
        Cursor cursor = mDB.rawQuery(query, selectionArgs);

        return cursor;
    }




    // This class is created when our DataManager is initialized
    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        // This method only runs the first time the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {

            // Create a table for photos and all their details
            String newTableQueryString = "create table "
                    + TABLE_N_AND_A + " ("
                    + TABLE_ROW_ID
                    + " integer primary key autoincrement not null,"
                    + TABLE_ROW_FIRSTNAME
                    + " text not null,"
                    + TABLE_ROW_LASTNAME
                    + " text not null,"
                    + TABLE_ROW_EMAIL
                    + " text not null,"
                    + TABLE_ROW_PASSWORD
                    + " text not null,"
                    + TABLE_ROW_CONFIRM_PASSWORD
                    + " text not null " + ");";

            Log.i("createtable() = ", newTableQueryString);

            db.execSQL(newTableQueryString);

        }

        // This method only runs when we increment DB_VERSION
        // We will look at this in chapter 26
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }

}
