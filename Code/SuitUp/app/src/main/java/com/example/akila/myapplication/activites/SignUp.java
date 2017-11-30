package com.example.akila.myapplication.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import com.example.akila.myapplication.Dashboard;
import com.example.akila.myapplication.R;
import com.example.akila.myapplication.helper.InputValidation;
import com.example.akila.myapplication.sql.DatabaseHelper;
import com.example.akila.myapplication.model.User;

import android.widget.Spinner;

// Name: Ananya Srinivasan
// Course: CSC 415
// Semester: Fall 2017
// Instructor: Dr. Pulimood
// SuitUp
// Description: An android app for work clothing donations
// Filename: SignUp
// Description: Class for handling the signup process for the application
// Last modified on: 10 November 2017

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = SignUp.this;
    private ScrollView scrollView;
    private Button signup;

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextEmail;
    private EditText editTextAdd1;
    private EditText editTextAdd2;
    private EditText editTextCity;
    private EditText editTextZipcode;
    private Spinner state;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    private InputValidation inputValidation;
    private DatabaseHelper dbHelper;
    private User user;

    //Precondition: All of the form items are filled in and the user clicks the button
    //Postcondition: The user is directed to the dashboard page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        scrollView = (ScrollView)findViewById(R.id.scroll_register);

        editTextName = (EditText)findViewById(R.id.username);
        editTextSurname = (EditText)findViewById(R.id.surname);
        editTextAdd1 = (EditText)findViewById(R.id.address1);
        editTextAdd2 = (EditText)findViewById(R.id.address2);
        editTextEmail = (EditText)findViewById(R.id.email);
        editTextCity = (EditText)findViewById(R.id.city);
        editTextZipcode = (EditText)findViewById(R.id.zipcode);
        editTextPassword = (EditText)findViewById(R.id.password1);
        editTextConfirmPassword = (EditText)findViewById(R.id.password2);

        signup = (Button)findViewById(R.id.signupbutton);
    }

    public void initListeners(){
        signup.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation = new InputValidation(activity);
        dbHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onCreate(View v){
        switch(v.getId()){
            case R.id.signupbutton:
                postDatatoSQLite();
                break;
        }
    }

    private void postDatatoSQLite(){
        dbHelper.addUser(user);
    }

    private void emptyEditText(){
        editTextName.setText(null);
        editTextSurname.setText(null);
        editTextAdd1.setText(null);
        editTextAdd2.setText(null);
        editTextZipcode.setText(null);
    }
}

