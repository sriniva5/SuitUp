package com.example.akila.myapplication.activites;

// Name: Ananya Srinivasan
// Course: CSC 415
// Semester: Fall 2017
// Instructor: Dr. Pulimood
// SuitUp
// Description: An android app for work clothing donations
// Filename: Search
// Description: The search class focuses on the cases where the user is searching for donation items
// Last modified on: 10 November 2017


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.akila.myapplication.R;
import com.example.akila.myapplication.sql.DonationHelper;

public class Search extends AppCompatActivity{

    private DonationHelper donationHelper;
    private EditText search;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        donationHelper = new DonationHelper(this);

        search = ((EditText)findViewById(R.id.search_word));
        textView = ((TextView)findViewById(R.id.search_result));
    }

    public void showResult(View view){
        String searchWord = search.getText().toString();
        textView.setText("Result for " + searchWord + "\n\n");

        Cursor cursor = donationHelper.search(searchWord);
        cursor.moveToFirst();

        if(cursor != null && cursor.getCount() > 0){
            int index;
            String result;

            do{
                index = cursor.getColumnIndex(DonationHelper.COLUMN_DONATION_ITEM);
                result = cursor.getString(index);
                textView.append(result + "\n");
            }while(cursor.moveToNext());
            cursor.close();
        }else{
            textView.append(getString(R.string.no_results));
        }
    }

}
