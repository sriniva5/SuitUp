package com.example.akila.myapplication.sql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import com.example.akila.myapplication.model.User;

/**
 * Created by Akila on 11/27/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "UserManager.db";

    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_SURNAME = "user_surname";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_ADDRESS1 = "user_address1";
    private static final String COLUMN_USER_ADDRESS2 = "user_address2";
    private static final String COLUMN_USER_CITY = "user_city";
    private static final String COLUMN_USER_STATE = "user_state";
    private static final String COLUMN_USER_ZIPCODE = "user_zipcode";
    private static final String COLUMN_USER_GENDER = "user_gender";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //create variables for all columns

    private String CREATE_USER_TABLE = "CREATE TABLE" + TABLE_USER + "(" + COLUMN_USER_ID +
            "INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + "TEXT," + COLUMN_USER_SURNAME + "TEXT," +
            COLUMN_USER_EMAIL + "TEXT," + COLUMN_USER_ADDRESS1 + "TEXT," + COLUMN_USER_ADDRESS2 + "TEXT,"
            + COLUMN_USER_CITY + "TEXT," + COLUMN_USER_STATE + "TEXT," + COLUMN_USER_ZIPCODE + "TEXT," +
            COLUMN_USER_GENDER + "TEXT," + COLUMN_USER_PASSWORD + "TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getFirst_name());
        values.put(COLUMN_USER_SURNAME, user.getLast_name());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_ADDRESS1, user.getAdd1());
        values.put(COLUMN_USER_ADDRESS2, user.getAdd2());
        values.put(COLUMN_USER_CITY, user.getCity());
        values.put(COLUMN_USER_STATE, user.getState());
        values.put(COLUMN_USER_ZIPCODE, user.getZipcode());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor c = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = c.getCount();
        c.close();
        db.close();

        if(cursorCount > 0)
            return true;
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email};

        Cursor c = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = c.getCount();
        c.close();
        db.close();

        if(cursorCount > 0)
            return true;
        return false;
    }
}
