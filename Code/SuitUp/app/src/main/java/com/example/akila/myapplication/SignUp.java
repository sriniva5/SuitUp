package com.example.akila.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

// Name: Ananya Srinivasan
// Course: CSC 415
// Semester: Fall 2017
// Instructor: Dr. Pulimood
// SuitUp
// Description: An android app for work clothing donations
// Filename: SignUp
// Description: Class for handling the signup process for the application
// Last modified on: 10 November 2017

public class SignUp extends AppCompatActivity {
    Button signup;

    //Precondition: All of the form items are filled in and the user clicks the button
    //Postcondition: The user is directed to the dashboard page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup = (Button)findViewById(R.id.signupbutton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, Dashboard.class);
                startActivity(i);
            }
        });
    }
}

