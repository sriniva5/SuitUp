package com.example.akila.myapplication;

// Name: Ananya Srinivasan
// Course: CSC 415
// Semester: Fall 2017
// Instructor: Dr. Pulimood
// SuitUp
// Description: An android app for work clothing donations
// Filename: Dashboard
// Description: Class for handling the options users have to interact with the app (ie: donation and donation search)
// Last modified on: 10 November 2017


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    Button search, donate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        search = (Button)findViewById(R.id.searchbutton);
        donate = (Button)findViewById(R.id.donatebutton);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        //Preconditions: The user is on the dashboard and clicks the search button to find donations
        //Postcondition: The user is redirected to the search page
        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Dashboard.this, Search.class);
                startActivity(intent);
            }
        });

        //Preconditions: The user is on the dashboard and clicks the donate button to donate items
        //Postcondition: The user is redirected to the donation page
        donate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Dashboard.this, Donation.this);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() { }
}
