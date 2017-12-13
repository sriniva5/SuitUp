package com.example.akila.myapplication.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.akila.myapplication.activites.Donation;
import com.example.akila.myapplication.model.Clothing;
import java.util.ArrayList;
import java.util.List;

/**
 * Database helper for donations
 * Implementation inspired by these tutorial: https://www.youtube.com/watch?v=mVIZFV0Ttds
 *                                            https://stackoverflow.com/questions/18868712/android-sqlite-search-by-name
 */

public class DonationHelper extends SQLiteOpenHelper {
    private static final String TAG = DonationHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DonationManager.db";
    private static final String TABLE_DONATION = "donation";

    public static final String COLUMN_DONATION_ID = "donation_id";
    public static final String COLUMN_DONATION_ITEM = "donation_item";
    public static final String COLUMN_DONATION_DESCRIPTION = "donation_description";
   public static final String COLUMN_DONATION_SIZE = "donation_size";

    private static String[] COLUMNS = {COLUMN_DONATION_ID, COLUMN_DONATION_ITEM,COLUMN_DONATION_DESCRIPTION, COLUMN_DONATION_SIZE};

    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;

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
        fillDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void fillDatabase(SQLiteDatabase db){
        String[] items = {"Tie", "Sport coat", "Pants", "Blouse"};
        String[] descriptions = {"Red,silk","TLC,grey,husky","black,dry-clean only", "silk, white"};
        int[] size = {0, 15, 12, 5};

        ContentValues contentValues = new ContentValues();
        for(int i = 0; i < items.length; i++){
            contentValues.put(COLUMN_DONATION_ITEM, items[i]);
            contentValues.put(COLUMN_DONATION_DESCRIPTION, descriptions[i]);
            contentValues.put(COLUMN_DONATION_SIZE, size[i]);

            db.insert(TABLE_DONATION, null, contentValues);
        }
    }

    public long addDonation(Clothing clothing){
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(COLUMN_DONATION_ITEM, clothing.getItem());
        values.put(COLUMN_DONATION_DESCRIPTION, clothing.getDescription());
        values.put(COLUMN_DONATION_SIZE, clothing.getSize());

        if (mWritableDB == null) {
            mWritableDB = getWritableDatabase();
        }

        newId = mWritableDB.insert(TABLE_DONATION, null, values);
        return newId;
    }

    //Search function
    public Cursor search(String searchWord){
        String[] columns = new String[]{COLUMN_DONATION_ITEM};
        String where = COLUMN_DONATION_ITEM + " LIKE ?";
        searchWord = "%" + searchWord + "%";
        String[] whereArgs = new String[]{searchWord};

        Cursor cursor = null;
        try{
            if(mReadableDB == null){
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.query(TABLE_DONATION, columns, where, whereArgs, null, null, null);
        }catch(Exception e){
            Log.d(TAG, "Search Exception"+e);
        }
        return cursor;
    }

    public Clothing query(int pos){
        String query = "SELECT * FROM " + TABLE_DONATION + " ORDER BY " + COLUMN_DONATION_ITEM + " ASC " + " LIMIT " + pos + ",1";
        Cursor cursor = null;
        Clothing c = new Clothing();

        try{
            if(mReadableDB == null){
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            c.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_DONATION_ID)));
            c.setItem(cursor.getString(cursor.getColumnIndex(COLUMN_DONATION_ITEM)));
            c.setItem(cursor.getString(cursor.getColumnIndex(COLUMN_DONATION_DESCRIPTION)));
            c.setSize(cursor.getInt(cursor.getColumnIndex(COLUMN_DONATION_SIZE)));
        }catch (Exception e){
            Log.d(TAG, "Query Exception"+e);
        }finally {
            cursor.close();
            return c;
        }
    }

    public long count() {
        if (mReadableDB == null) {
            mReadableDB = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDB,TABLE_DONATION);
    }
}
