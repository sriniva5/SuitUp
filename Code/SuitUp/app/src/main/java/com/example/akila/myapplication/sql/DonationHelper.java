package com.example.akila.myapplication.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.akila.myapplication.model.Clothing;

/**
 * Database helper for donations
 * Implementation inspired by this tutorial: https://www.youtube.com/watch?v=mVIZFV0Ttds
 */

public class DonationHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DonationManager.db";
    private static final String TABLE_DONATION = "donation";

    private static final String COLUMN_DONATION_ID = "donation_id";
    private static final String COLUMN_DONATION_ITEM = "donation_item";
    private static final String COLUMN_DONATION_DESCRIPTION = "donation_description";
    private static final String COLUMN_DONATION_SIZE = "donation_size";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_DONATION + "("
            + COLUMN_DONATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DONATION_ITEM
            + " TEXT," + COLUMN_DONATION_DESCRIPTION + " TEXT," + COLUMN_DONATION_SIZE + " INTEGER"
            + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_DONATION;

    public DonationHelper(Context context){
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
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

    public void addDonation(Clothing clothing){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DONATION_ITEM, clothing.getItem());
        values.put(COLUMN_DONATION_DESCRIPTION, clothing.getDescription());
        values.put(COLUMN_DONATION_SIZE, clothing.getSize());

        db.insert(TABLE_DONATION, null, values);
        db.close();
    }
}
